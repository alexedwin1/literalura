<h1 align="center">Literalura</h1>

<p align="center">Esta es una aplicación en la cual se realiza consumo de datos de un service API y se almacena en una base de datos PosgreSQL. Luego se pueden realizar diferentes consultas, en la la propia base de datos.</p>

<p align="center">Esta imagen muestra como se vé el menú inicial.</p>

<p align="center"><img src="" alt="menu-inical"></p>

<hr>

![Static Badge](https://img.shields.io/badge/lenguaje_de_programación-Java-blue)
![Static Badge](https://img.shields.io/badge/version_de_JKD-21-yellow)
![Static Badge](https://img.shields.io/badge/version_de_PostgreSQL-15-yellow)
  
## Instalación y ejecución.

1.   **Clonar el repositorio**  
	Para hacer esto, ir al repositorio y copiar el link http

```
https://github.com/alexedwin1/conversor-de-moneda.git

```

2.   **En una carpeta que elijamos en nuestra computadora, abri Git Bash y usar el siguiente comando seguido del link de arriba**  
```
git clone <link>
```

3.   **Abrir la carpeta clonada con IntelliJ u otro IDE (por defecto usamos IntelliJ)**
  
4.   **Antes de ejecutar la aplicación se debe crear una base de datos con nombre `literalura` en postgreSQL**
- [Link descarga PostgreSQL](https://www.postgresql.org/download/windows/).


5.  **Configuración de datos de la base de datos**
- Una vez dentro de IntelliJ y abierto el proyecto en src | main | Resources abrir application.propierties y configurar de la siguiente forma:

```
spring.datasource.url=jdbc:postgresql://localhost/literalura
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```
`DB_USER` y `DB_PASSWORD` son variables de entorno que tienen que ser configurado en la maquina en la cual será ejecutado la aplicación.  Tambien se puede sustituir `${DB_USER}` y `${DB_PASSWORD}` por el usuario y la contraseña de PosgreSQL, creados cuando se instaló.
- Guardar.
- Todas las dependencias están en el archivo pom.xml, es probable que sea necesario actualizar o recargar estas dependencias presionando el botón `Maven` en la barra lateral derecha y luego presionando `Sync/Reload All Maven Projects`.

6. **Listo, para ejecutar la aplicación abrimos LiteraluraAplicattion.java, el cuál da la opción de Run.  Esta se encuentra en Literalura | src | main | java**

---

## Cómo funciona?

El funcionamiento es simple.  Aparece un menú con 6 opciones. Con la opción #1 se puede buscar un libro en el servicio API, si está disponible regresarán los datos de dicho libro y se almacenará en la base de datos.

Con la opción #2 se puede listar los libros registrados en la base de datos.

Con la opción #3 se puede listar el nombre de Autores registrados en la base dde datos.

Con la opción #4 se puede listar el nombre de Autores vivos en un determinado año.

Con la opción #5 se listan los libros que se hayan almacenados por idiomas.

La última opción es para salir.



---
```
Release date: 06-August-2025
```
---
