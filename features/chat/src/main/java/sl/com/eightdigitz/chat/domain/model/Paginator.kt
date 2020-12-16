package sl.com.eightdigitz.chat.domain.model

import java.math.BigDecimal


data class Paginator(
    var currentPage: Int? = null,
    var perPage: Int? = null,
    var to: Int? = null
)