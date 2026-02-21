package itvo.edu.biblioteca

data class Usuario(
    val nombre: String,
    val id: Int,
    val librosPrestados: MutableList<Libro> = mutableListOf()
)