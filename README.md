Este es el repositorio del proyecto que se debe entregar en la materia Programación Avanzada de Bases de Datos.

Primer Objetivo: ABM sobre la base de datos Northwind.

Elegimos para este apartado trabajar sobre la tabla customers. Para ellos debemos de crear una pequeña aplicación que permita el alta, baja y modificación de los registros de la misma.
Se debe trabajar en 3 apartados importantes:

  * Interfaz grafica
  * Conexion
  * Control de funciones para ejecutar la que corresponda.
    
Las funciones de la base de datos estaran definidas con PL/pgsql y solo se las debera llamar con los parametros correspondientes para se ejecución no se debera de armar ya sea el
update, insert o delete completos en la aplicación sino simplemente llamar las funciones que corresponda.

El proyecto se creo como un maven java proyect para que se encargue automaticamente del tema de las dependencias. Se lo configura una vez (ya lo hice) y listo, al importarlo deberia de descargar
lo necesario para funcionar.

Para clonar el proyecto simplemente se usa el enlace que figura en el repositorio o se descarga el zip.
Desde eclipse, que es el IDE que estoy usando se puede importar un maven proyect (que es el formato que tiene este proyecto) y simplemente
descargar el driver correspondiente, de esta forma evitamos andar trabajándolo manualmente.
