package models

import java.util.*

data class KotlinPiePointsWithDate(
        var initiativeName: String?,
        var totalpoints: Int?,
        var username: String?
)
{
    constructor() : this(null, null, null)

}