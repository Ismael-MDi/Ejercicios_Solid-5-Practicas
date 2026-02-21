package itvo.edu.biblioteca
class sisBiblioteca( val biblioteca: Biblioteca) {

    fun prestarLibro(usuario: Usuario, libro: Libro, fecha: String) {
        if (!libro.disponible) {
            println("❌ El libro '${libro.titulo}' ya está prestado.")
            return
        }
        if (usuario.librosPrestados.size >= 3) {
            println("❌ El usuario ${usuario.nombre} ya tiene 3 libros prestados.")
            return
        }
        libro.disponible = false
        usuario.librosPrestados.add(libro)
        biblioteca.prestamos.add(Prestamo(libro, usuario, fecha))
        println("✅ Libro '${libro.titulo}' prestado a ${usuario.nombre}.")
    }
    fun devolverLibro(usuario: Usuario, libro: Libro, fecha: String) {
        val prestamo = biblioteca.prestamos.find {
            it.libro == libro && it.usuario == usuario && it.fechaDevolucion == null
        }
        if (prestamo != null) {
            libro.disponible = true
            usuario.librosPrestados.remove(libro)
            prestamo.fechaDevolucion = fecha
            println("📖 El libro '${libro.titulo}' fue devuelto por ${usuario.nombre}.")
        } else {
            println("❌ No se encontró un préstamo activo de ese libro para ${usuario.nombre}.")
        }
    }

    fun mostrarLibrosDisponibles() {
        println("📚 Libros disponibles:")
        biblioteca.libros.filter { it.disponible }.forEach {
            println(" - ${it.titulo} de ${it.autor}")
        }
    }

    fun mostrarLibrosPrestados() {
        println("📕 Libros prestados:")
        biblioteca.libros.filter { !it.disponible }.forEach {
            println(" - ${it.titulo} de ${it.autor}")
        }
    }
    }