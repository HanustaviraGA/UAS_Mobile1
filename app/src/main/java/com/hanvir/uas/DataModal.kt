package com.hanvir.uas

class DataModal {
    var name: String? = null
    var imgUrl: String? = null
    var status: String? = null

    constructor() {
        // empty constructor required for firebase.
    }

    constructor(name: String?, imgUrl: String?, status: String?) {
        this.name = name
        this.imgUrl = imgUrl
        this.status = status
    }
}