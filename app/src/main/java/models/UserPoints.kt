package models

import java.util.*

data class UserPoints(
        var username: String,
        var date: Date,
        var goals: InitiativeDbGoals,
        var totalUserPoints: Int
)
