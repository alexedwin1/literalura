<h1 align="center">Literalura</h1>

<p align="center">Esta es una aplicación en la cual se realiza consumo de datos de un service API y se almacena en una base de datos PosgreSQL. Luego se pueden realizar diferentes consultas, en la la propia base de datos.</p>

<p align="center">Esta imagen muestra como se vé el menú inicial.</p>

<p align="center"><img width="395" height="197" alt="Menu Inicial" src="https://github.com/user-attachments/assets/ef320940-a80b-408b-99c5-b558e441455d" /></p>

<hr>

![Static Badge](https://img.shields.io/badge/lenguaje_de_programación-Java-blue)
![Static Badge](https://img.shields.io/badge/version_de_JKD-21-yellow)
![Static Badge](https://img.shields.io/badge/version_de_PostgreSQL-15-blue)
![Static Badge](https://img.shields.io/badge/version_de_SpringBoot-3.5.4-yellow)
  
## Instalación y ejecución.

1.   **Clonar el repositorio**  
	Para hacer esto, ir al repositorio y copiar el link http

```
https://github.com/alexedwin1/literalura.git

```

2.   **En una carpeta que elijamos en nuestra computadora, abri Git Bash y usar el siguiente comando seguido del link de arriba**  
```
git clone <link>
```

3.   **Abrir la carpeta clonada con IntelliJ u otro IDE (por defecto usamos IntelliJ)**
  
4.   **Antes de ejecutar la aplicación se debe crear una base de datos con nombre `literalura` en PostgreSQL**
- [Link descarga PostgreSQL](https://www.postgresql.org/download/windows/).


5.  **Configuración de datos de la base de datos en IntelliJ**
- Una vez dentro de IntelliJ y abierto el proyecto en `src | main | Resources` abrir `application.propierties` y configurar de la siguiente forma:
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

El funcionamiento es simple.  Aparece un menú con 6 opciones.

Estas son las diferentes funcionalidades con cada opción:

- Opción #1, busca un libro en el servicio API, si está disponible se obtendrán los datos de dicho libro y se almacenará en la base de datos. **Importante:** es necesario una conexión a Internet para el funcionamiento de esta opción. Las demás opciones ya no necesitan de conexión a Internet.
  <p align="center"><img width="1245" height="162" alt="Image" src="https://github.com/user-attachments/assets/79c3a392-8c7a-4a1f-80b5-42d833b4de25" /></p>

- Opción #2, lista los libros registrados en la base de datos. Es decir los datos descargados con la opción #1.
  <p align="center"><img width="514" height="520" alt="Image" src="https://github.com/user-attachments/assets/3cc87b4a-f4e3-499c-887c-92221a483c1c" /</p>
 
- Opción #3, lista el nombre de los autores registrados en la base de datos.  Datos descargador con opción #1.
  <p align="center"><img width="508" height="494" alt="Image" src="https://github.com/user-attachments/assets/5361581d-0ae2-4e72-8214-f20ce203c42f" /></p>

- Opción #4, lista el nombre de autores vivos en un determinado año.  Se ingresa un año específico y verifica si aún vive, por la fecha de su nacimiento.
  <p align="center"><img width="486" height="262" alt="Image" src="https://github.com/user-attachments/assets/34e9406d-20a0-40db-b53a-e9e53e8cb3ca" /></p>

- Opción #5, lista los libros que se hayan almacenados por idiomas. Indica cuantos libros hay en determinado idioma y se pueden verificar dichos libros eligiendo un idioma.
  <p align="center"><img width="520" height="479" alt="Image" src="https://github.com/user-attachments/assets/194fa6a7-24f6-4f55-9873-67890015478e" /></p>

- Opción #6, salir de la aplicación.



---
```
Release date: 06-August-2025
```
---
