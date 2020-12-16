### Getting Started


1. Update `swagger.json` 
2. Call the :generateSwagger gradle task, that will run the code generation with the configuration you provided.

### Requirements

In order to run this gradle plugin you need to fulfill the following requirements:

* Gradle 5.x - This plugin uses Gradle 5 features, and you will need to setup your Gradle wrapper to use 5.0 or more.
* Java 8+


### Configuration

To configure the generator, please use the `generateSwagger { }` block. Here an example of this block with all the properties.

```kotlin
generateSwagger {
    platform = "kotlin"
    packageName = "sl.com.eightdigitz.client"
    specName = "integration"
    specVersion = "1.0.0"
    inputFile = file("../sample_specs.json")
    outputDir = file("./src/main/java/")
    features {
        headersToRemove = ["Accept-Language"]
    }
}
```

And here a table with all the properties and their default values:

| Property | Description | Default |
| -------- | ----------- | ------- |
| `inputFile` | Defines the path to the Swagger spec file | **REQUIRED** |
| `platform` | Defines the platform/templates that will be used. See [Supported platforms](#supported-platforms) for a list of them. | `"kotlin"` |
| `packageName` | Defines the fully qualified package name that will be used as root when generating the code. | `"com.codegen.default"` |
| `specName` | Defines the name of the service that is going to be built. | `"defaultname"` |
| `specVersion` | Defines the version of the spec that is going to be used. | If not provided, the version will be read from the specfile. If version is missing will default to `"0.0.0"` |
| `outputDir` | Defines the output root folder for the generated files. | `$buildDir/gen` |
| `extraFiles` | Defines a folder with extra files that will be copied over after the generation (e.g. util classes or overrides). | not set by default |

Please note that all those properties can be configured with **command line flags** that mirrors 1:1 the property name. E.g.:

```bash
./gradlew generateSwagger --inputFile=./sample/specs.json
```


### Initialise APIs

Add APIs to the Koin module declare in ApiModule.kt 

```kotlin
val apiModule: Module = module {
    single { tokenAuthenticator }

    single { authApi }
    single { forgotPasswordApi }
}

// Authentication API
private val authApi: AuthApi = retrofit.create(AuthApi::class.java)
// Forgot Password API
private val forgotPasswordApi: ForgotPasswordApi = retrofit.create(ForgotPasswordApi::class.java)
```