package models

data class BaerkraftDataModel(
    var title: String?,
    var beskrivelse: String?,
    //var imgPath: String?,
    //IMG path most be int for some reason?
    var imgPath: Int?,
    var counter: Int?
    )

{
    constructor() : this(null, null, null, null)
}

