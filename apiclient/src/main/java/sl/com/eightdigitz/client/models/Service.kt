/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: iQuote API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package sl.com.eightdigitz.client.models

import com.squareup.moshi.Json

/**
 * 
 * @property id 
 * @property name 
 */
data class Service (
        @Json(name = "id") @field:Json(name = "id") var id: Int? = null,
        @Json(name = "name") @field:Json(name = "name") var name: String? = null
)

