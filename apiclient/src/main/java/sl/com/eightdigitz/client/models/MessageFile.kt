/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Oxygen Sandbox Backend API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package sl.com.eightdigitz.client.models

import com.squareup.moshi.Json

/**
 * 
 * @property uuid 
 * @property url 
 * @property thumbnailUrl 
 * @property type 
 */
data class MessageFile (
        @Json(name = "uuid") @field:Json(name = "uuid") var uuid: String? = null,
        @Json(name = "url") @field:Json(name = "url") var url: String? = null,
        @Json(name = "thumbnail_url") @field:Json(name = "thumbnail_url") var thumbnailUrl: String? = null,
        @Json(name = "type") @field:Json(name = "type") var type: String? = null
)

