servicio Crud Alumnos:

- Alta de alumnos a partir de JSON recibido en el cuerpo
- Recuperación de todos los alumnos
- Busqueda de alumno por id
- Eliminación de alumno por id
- Actualización de alumno. Cambiar el curso del alumno cuyo id se recibe
- recuperación de la lista de curso

Crear un microservicio de productos, que accediendo a la base de datos de tienda, exponga 
los siguientes recursos:
- Alta de productos a partir de los datos recibidos en el cuerpo
- Eliminación de producto por su id
- Recuperación de todos los productos
- Recuperación de productos por sección

Creación microservicio estudiantes

Se trata de crear un microservicio, que interaccione con el microservicio de alumnos.
Utiliza el dato Student: nombre, curso, puntuacion.
Recursos:
- A partir de una puntuación recibida como parametro o variable URL, devuelve los estudiantes
Que tengan como mínimo dicha puntuación
- Dar de alta nuevo estudiantes

Creación del microservicio de paises https://restcountries.com/v2/all
Se trata de crear un microservicio que interaccione con el servicio remoto de paises
Definiremos un modelo de datos Pais con los siguientes atributos: nombre,capital,continente,poblacion y bandera
Recursos:
- Lista de continentes
- Paises por continente. Recibe una petición get con el nombre de un continente, y devuelve los paises
del mismo
- Población continente. Recibe una peticion get con el nombre de un continente, y devuelve el total de
habitantes de dicho continente
