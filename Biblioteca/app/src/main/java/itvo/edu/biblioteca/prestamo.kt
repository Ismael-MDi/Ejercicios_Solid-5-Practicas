package itvo.edu.biblioteca

data class Prestamo(
    val libro: Libro,
    val usuario: Usuario,
    val fechaPrestamo: String,
    var fechaDevolucion: String? = null
)