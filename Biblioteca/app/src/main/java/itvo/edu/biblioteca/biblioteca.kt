package itvo.edu.biblioteca

class Biblioteca(
    val libros: MutableList<Libro> = mutableListOf(),
    val usuarios: MutableList<Usuario> = mutableListOf(),
    val prestamos: MutableList<Prestamo> = mutableListOf()
)