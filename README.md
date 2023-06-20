# 🦷 Proyecto Integrador: Clínica Odontológica
## 🎯 Objetivo

El objetivo de este proyecto es crear un sistema que permita:
1. Guardar un paciente y un domicilio en una base de datos.
2. Guardar un odontólogo en una base de datos.
3. Guardar un turno en una base de datos usando los ID del paciente y del odontólogo.
4. Realizar todas las operaciones CRUD por cada entidad.

Este proyecto también incluye el desarrollo de la interfaz gráfica con HTML, CSS y JavaScript; la meta es poder conectarse a los endpoints de cada controlador y obtener la información para mostrarla y modificarla.

Junto con lo anterior, el sistema debe mostrar los estados del servidor en cada solicitud y respuesta.

## 🔨 Dependencias
Este proyecto hace uso de estas dependencias:
1. **Spring Boot**: es el Framework de trabajo.
   1. **Spring Boot Web**: para crear API RESTful.
   2. **Spring Data JPA**: para persistir en bases de datos implementando la API de Persistencia de Java junto con Hibernate.
2. **Hibernate Validator**: para poder validar entidades con anotaciones.
3. **Javax Validation**: para poder validar Beans.
4. **Jackson**: para _parsear_ objetos Java en JSON y viceversa.
   1. **Jackson Core**: es la biblioteca principal.
   2. **Jackson Databind**: para poder convertir POJO en JSON.
   3. **Jackson Annotations**: son anotaciones filtrar entidades de acuerdo con nuestras necesidades.
5. **Logback**: para obtener Logs de todo tipo.
6. **H2 Database**: para persistir datos en tablas con SQL.
7. **JUnit**: para realizar y probar los tests unitarios.

## 🙌 ¡Vamos por el siguiente reto! 

## Integrantes
**Tomás Mataloni** (_@Tomasm1000_) & **Juan David García** (_@DavidGMont_)