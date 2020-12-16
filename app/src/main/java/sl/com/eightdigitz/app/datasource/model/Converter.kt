package sl.com.eightdigitz.app.datasource.model

import sl.com.eightdigitz.models.Success

fun sl.com.eightdigitz.client.models.SuccessResponse.mapToDataSource(): Success =
    Success(message, result, payload)
