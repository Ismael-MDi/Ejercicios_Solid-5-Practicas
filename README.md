Este repositorio contiene la implementacion de 5 ejercicios practicos desarrollados en Kotlin. El objetivo de este proyecto es demostrar la correcta aplicacion de los principios SOLID y la arquitectura de separacion de responsabilidades. 

Para lograr esto, el codigo se estructuro en cuatro capas principales:
1. **Entidades:** Clases de datos puras sin logica de negocio.
2. **Interfaces:** Contratos que definen el comportamiento de los repositorios.
3. **Repositorios:** Estructuras de almacenamiento en memoria que inician completamente vacias. Se evito estrictamente el uso de datos predefinidos dentro de las clases.
4. **Logica:** Clases que procesan las reglas de negocio utilizando inyeccion de dependencias. Todos los datos de prueba se inyectan unicamente desde la clase `Main` de cada ejercicio.

Ademas, se implementaron clases intermedias para manejar relaciones complejas (como historiales y disponibilidad por fechas) respetando el Principio de Responsabilidad Unica (SRP).

---

## Descripcion de los Ejercicios y Pruebas Unitarias

A continuacion se explica la logica desarrollada para cada ejercicio y el enfoque de sus respectivas pruebas unitarias realizadas con JUnit. En las pruebas se aplico la regla de verificar matematicamente el estado de los objetos y repositorios, en lugar de solo validar mensajes de texto.

### Ejercicio 1: Sistema de Biblioteca
* **Logica del Proyecto:** Simula un sistema de prestamos de libros. El sistema valida dos reglas de negocio principales: comprueba que el libro solicitado este marcado como disponible en el inventario y verifica que el usuario no exceda el limite de 3 libros prestados simultaneamente.
* **Pruebas Unitarias:** Se evalua la logica de prestamos simulando escenarios de exito y fracaso. Las pruebas extraen la informacion de los repositorios para confirmar que el estado del libro cambia a "no disponible" tras un prestamo exitoso y que el sistema bloquea transacciones cuando se alcanza el limite del usuario.

### Ejercicio 2: Gestion de Cursos
* **Logica del Proyecto:** Sistema para la inscripcion de estudiantes a materias. Se implementaron validaciones para evitar la inscripcion duplicada de un mismo estudiante en un curso y para asegurar que no se exceda el cupo maximo permitido (30 estudiantes por curso).
* **Pruebas Unitarias:** Los test verifican la logica de conteo e insercion. Se comprueba que, al llegar al limite de cupo o al intentar registrar un identificador duplicado, las listas internas del curso no incrementen su tamano, asegurando la integridad de los datos.

### Ejercicio 3: Gestion de Ventas de Tienda
* **Logica del Proyecto:** Sistema de carrito de compras y facturacion. Antes de procesar una orden, el sistema itera sobre los productos del carrito, los busca en el inventario real y verifica que exista stock mayor a cero. Posteriormente, calcula el subtotal, aplica los impuestos correspondientes, descuenta el stock y guarda el registro en el historial del cliente.
* **Pruebas Unitarias:** Se validan los calculos matematicos del total de la compra. Ademas, las pruebas consultan directamente el repositorio de productos despues de una transaccion para garantizar que el stock se haya restado correctamente o que la compra se detenga si no hay existencias.

### Ejercicio 4: Sistema de Reservas de Hotel (Desarrollado en Ingles)
* **Logica del Proyecto:** Plataforma de reservaciones. Para evitar el error de diseno de usar un simple booleano de disponibilidad, se creo una clase independiente llamada `Availability`. Esto permite reservar una misma habitacion en diferentes fechas. El sistema cruza las fechas solicitadas con los registros existentes para evitar reservas dobles y libera las fechas al procesar una cancelacion.
* **Pruebas Unitarias:** Se configuro un entorno de pruebas (`@Before`) para aislar los datos. Las pruebas validan que al crear una reserva se genere correctamente un registro de disponibilidad en el repositorio, y que al ejecutar la funcion de cancelar, dicho registro se elimine del sistema.

### Ejercicio 5: Gestion de Eventos (Desarrollado en Ingles)
* **Logica del Proyecto:** Sistema para el control de asistencia a talleres. Utiliza una clase intermedia llamada `Registration` para conectar asistentes con actividades. El sistema valida el cupo maximo del salon y ejecuta una verificacion matematica de horarios (hora de inicio y hora de fin) para impedir que un asistente se inscriba a dos actividades que se empalman.
* **Pruebas Unitarias:** Las pruebas aseguran la correcta generacion de los registros. Se evaluan escenarios de solapamiento de horarios extrayendo las listas de actividades inscritas del asistente; si hay un choque de horarios, el test comprueba que el tamano de la lista del asistente permanezca intacto.
