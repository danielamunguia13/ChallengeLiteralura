
# Literalura

Este proyecto es una aplicación de búsqueda de libros que utiliza la API de Gutenberg para obtener información sobre libros y autores.

## Índice

1. [Descripción](#descripción)
2. [Funciones](#funciones)
3. [Tecnologías Utilizadas](#tecnologías-utilizadas)
4. [Instrucciones de Uso](#instrucciones-de-uso)
5. [Configuración](#configuración)
6. [Contacto](#contacto)

## Descripción

Literalura es una aplicación desarrollada con Spring Boot que permite a los usuarios buscar libros por título, listar todos los libros disponibles y encontrar autores que estuvieron vivos en un año específico. Utiliza la base de datos PostgreSQL para almacenar información sobre libros y autores.

## Funciones

- **Buscar libro por título**: Permite buscar un libro por su título utilizando la API de Gutenberg.
- **Listar todos los libros**: Muestra una lista de todos los libros almacenados en la base de datos.
- **Listar autores vivos en un año específico**: Busca y muestra los autores que estuvieron vivos en un año determinado.

## Tecnologías Utilizadas

- Java 22
- Spring Boot 3.3.1
- PostgreSQL
- Hibernate
- Jackson (para el manejo de JSON)
- HttpClient (para las solicitudes HTTP)

## Instrucciones de Uso

1. Clona el repositorio: `git clone https://github.com/danielamunguia13/ChallengeLiteralura.git`
2. Configura la base de datos PostgreSQL según las instrucciones en `application.properties`.
3. Ejecuta la aplicación con Spring Boot desde tu IDE o con Maven: `mvn spring-boot:run`.

## Configuración

Asegúrate de configurar las siguientes propiedades en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=secreto1322
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## Contacto

Para cualquier consulta o sugerencia, no dudes en contactarme:

- Nombre: Daniela Munguía Alfaro
- LinkedIn: [Perfil de LinkedIn](https://www.linkedin.com/in/danielamunguiaalfaro)
