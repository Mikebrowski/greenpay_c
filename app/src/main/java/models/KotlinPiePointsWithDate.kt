package models

import java.util.*

data class KotlinPiePointsWithDate(
        val name: String?,
        val totalpoints: Int?
) {

    constructor() : this(null, null)

}