package fr.raouf.verra.data

data class User(
    var name: String,
    var email: String,
    var phone: String,
    var password: String
) {
    var id: Int = -1
    constructor(
        id: Int,
        name: String,
        email: String,
        phone: String,
        password: String
    ): this(
        name,
        email,
        phone,
        password
    ) {
        this.id = id
    }
}
