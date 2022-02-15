package models

import java.util.*

data class KotlinPiePointsWithDate(
        var initiativeName: String?,
        var totalpoints: Int?,
        var username: String?,
        var curretDate: String? = null
)
{
    constructor() : this(null, null, null,  null)

}