


### EditTextExtentions

You can validate the EditText like

`
 et_email.validate("Valid email address required"){ s -> s.isValidEmail() }
`

To check minimum and maximum length

`
et_email.validate("Minimum length is 6"){ s-> s.length>=6 }
`


### ButtonExtention

To disable button

`
disableButton()
`

To enable button, which added alpha value to show as disabled

`
enableButton()
`


### ImageExtension

To load image from URL, this is using Glide library

`
ImageView.loadImage(url: String)
`

To load image as Rounded Image, this is using Glide library

`
ImageView.loadImageRound(url: String)
`


### KeyboardExtensions

`
Activity.hideKeyboard()
`


`
View.showKeyboard()
`


`
View.hideKeyboard()
`

### StringExtension

To check whether the String is an email address

`
String.isValidEmail()
`


### ContextExtension
This contain context related extensions such as Network Availability, Screen Dimensions and Heights etc.

To check whether that the NetworkIs Available

`
Context.isNetworkStatusAvailable()
`

To execute block of code if network is available or not

```java
Context.withNetwork(
    {
         // Do stuff here when the network available
    },
    {
        // Network is not available handle ME :) !
    }
)
```