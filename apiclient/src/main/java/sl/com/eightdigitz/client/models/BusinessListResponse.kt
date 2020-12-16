/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: iQuote API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package sl.com.eightdigitz.client.models

import com.squareup.moshi.Json

/**
 * 
 * @property message 
 * @property result 
 * @property payload 
 * @property paginator 
 */
data class BusinessListResponse (
        @Json(name = "message") @field:Json(name = "message") var message: String? = null,
        @Json(name = "result") @field:Json(name = "result") var result: Boolean? = null,
        @Json(name = "payload") @field:Json(name = "payload") var payload: List<Business>? = null,
        @Json(name = "paginator") @field:Json(name = "paginator") var paginator: Paginator? = null
)

