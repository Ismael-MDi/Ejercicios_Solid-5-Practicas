package itvo.edu.biblioteca

    data class Libro(
    val titulo: String,
    val autor: String,
    val isbn: String,
    var disponible: Boolean = true
)