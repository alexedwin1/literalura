package com.digitaltelcom.literalura.principal;

import com.digitaltelcom.literalura.DTO.Datos;
import com.digitaltelcom.literalura.DTO.DatosAutor;
import com.digitaltelcom.literalura.DTO.DatosLibro;
import com.digitaltelcom.literalura.model.Autor;
import com.digitaltelcom.literalura.model.Libro;
import com.digitaltelcom.literalura.repository.AutorRepository;
import com.digitaltelcom.literalura.repository.LibroRepository;
import com.digitaltelcom.literalura.service.ConsumoAPI;
import com.digitaltelcom.literalura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    @Autowired
    private final LibroRepository libroRepository;
    @Autowired
    private final AutorRepository autorRepository;

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private String json;

    private String menu = """
            -----------------------------------------
            Escoje una opción, por medio de un número:
            1. Buscar libro por título
            2. Listar libros registrados
            3. Listar Autores Registrados
            4. Listar Autores Vivos en un determinado año
            5. Listar libros por idiomas
            0. Salir
            -----------------------------------------
            """;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            json = consumoApi.obtenerDatos(URL_BASE);
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosEnDeterminadoAnio();
                case 5 -> listarLibrosPorIdiomas();
                case 0 -> System.out.println("Hasta pronto!");
                default -> System.out.println("Opción no válida");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        DatosLibro datosLibro = recibirDatosDelLibro();
        if(datosLibro != null){
            Libro libro;
            DatosAutor datosAutor = datosLibro.autor().get(0);
            Autor autorExistente = autorRepository.findByNombre(datosAutor.nombre());
            if(autorExistente != null) {
                libro = new Libro(datosLibro, autorExistente);
            }else {
                Autor nuevoAutor = new Autor(datosAutor);
                libro = new Libro(datosLibro, nuevoAutor);
                autorRepository.save(nuevoAutor);
            }
            try {
                libroRepository.save(libro);
                System.out.println(libro);
            } catch (Exception e) {
                System.out.println("El libro que intentas registrar, ya existe");
            }
        } else {
            System.out.println("Libro no existe en la API");
        }
    }

    private DatosLibro recibirDatosDelLibro() {
        System.out.println("Ingrese el nombre del libro a buscar: ");
        var nombreLibro = teclado.nextLine();
        json = consumoApi.obtenerDatos(URL_BASE +
                "?search=" +
                nombreLibro.replace(" ", "+" ));
        Datos datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .filter(libro -> libro.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();
        return libroBuscado.orElse(null);
    }

    private void listarLibrosRegistrados() {
        try {
            List<Libro> librosRegistrados = libroRepository.findAll();

            if (librosRegistrados.isEmpty()) {
                System.out.println("No hay libros registrados en la base de datos");
            } else {
                System.out.println("\n---------- Libros Registrados ----------");
                System.out.println("Total de libros: " + librosRegistrados.size());
                System.out.println("=========================================\n");

                librosRegistrados.stream()
                        .forEach(libro -> {
                            System.out.println("Título: " + libro.getTitulo());
                            System.out.println("Autor: " + libro.getAutor().getNombre());
                            System.out.println("Idioma: " + libro.getIdiomas());
                            System.out.println("Descargas: " + libro.getNumeroDeDescargas());
                            System.out.println("-----------------------------------------");
                        });
            }
        } catch (Exception e) {
            System.out.println("Error al consultar los libros: " + e.getMessage());
        }
    }

    private void listarAutoresRegistrados() {
        try {
            List<Autor> autoresRegistrados = autorRepository.findAll();

            if (autoresRegistrados.isEmpty()) {
                System.out.println("No hay autores registrados en la base de datos.");
            } else {
                System.out.println("\n---------- Autores Registrados ----------");
                System.out.println("Total de autores: " + autoresRegistrados.size());
                System.out.println("==========================================\n");

                autoresRegistrados.stream()
                        .sorted((a1, a2) -> a1.getNombre().compareToIgnoreCase(a2.getNombre()))
                        .forEach(autor -> {
                            System.out.println("Autor: " + autor.getNombre());

                            // Mostrar fecha de nacimiento
                            if (autor.getFechaDeNacimiento() != null) {
                                System.out.println("Nacimiento: " + autor.getFechaDeNacimiento());
                            }

                            // Mostrar fecha de fallecimiento o si está vivo
                            if (autor.getFechaDeFallecimiento() != null) {
                                System.out.println("Fallecimiento: " + autor.getFechaDeFallecimiento());
                            } else {
                                System.out.println("Estado: Vivo");
                            }

                            System.out.println("-----------------------------------------");
                        });
            }
        } catch (Exception e) {
            System.out.println("Error al consultar los autores: " + e.getMessage());
        }
    }

    private void listarAutoresVivosEnDeterminadoAnio() {
        try {
            System.out.print("Ingrese el año para consultar autores vivos: ");
            int anio = teclado.nextInt();
            teclado.nextLine(); // Consumir el salto de línea

            // Consulta optimizada en la base de datos usando el método del repositorio
            List<Autor> autoresVivos = autorRepository.findAutoresVivosEnAnio(anio);

            if (autoresVivos.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año " + anio + ".");
            } else {
                System.out.println("\n---------- AUTORES VIVOS EN " + anio + " ----------");
                System.out.println("Total de autores encontrados: " + autoresVivos.size());
                System.out.println("==================================================\n");

                autoresVivos.stream()
                        .sorted((a1, a2) -> a1.getNombre().compareToIgnoreCase(a2.getNombre()))
                        .forEach(autor -> {
                            System.out.println("Autor: " + autor.getNombre());

                            // Mostrar fechas de nacimiento y fallecimiento
                            if (autor.getFechaDeNacimiento() != null) {
                                System.out.println("Nacimiento: " + autor.getFechaDeNacimiento());
                            }

                            if (autor.getFechaDeFallecimiento() != null) {
                                System.out.println("Fallecimiento: " + autor.getFechaDeFallecimiento());
                            } else {
                                System.out.println("Estado: Vivo");
                            }

                            System.out.println("-----------------------------------------");
                        });
            }
        } catch (Exception e) {
            System.out.println("Error al consultar autores vivos: " + e.getMessage());
            teclado.nextLine(); // Limpiar buffer en caso de error
        }
    }

    private void listarLibrosPorIdiomas() {
        try {
            // Primero mostrar los idiomas disponibles
            mostrarIdiomasDisponibles();

            System.out.print("Ingrese el código del idioma (ej: es, en, fr): ");
            String idioma = teclado.nextLine().toLowerCase().trim();

            // Consulta optimizada en la base de datos usando el método del repositorio
            List<Libro> librosPorIdioma = libroRepository.findByIdioma(idioma);

            if (librosPorIdioma.isEmpty()) {
                System.out.println("No se encontraron libros en el idioma: " + idioma);
            } else {
                System.out.println("\n========== LIBROS EN IDIOMA: " + idioma.toUpperCase() + " ==========");
                System.out.println("Total de libros encontrados: " + librosPorIdioma.size());
                System.out.println("=========================================================\n");

                librosPorIdioma.stream()
                        .sorted((l1, l2) -> l1.getTitulo().compareToIgnoreCase(l2.getTitulo()))
                        .forEach(libro -> {
                            System.out.println("Título: " + libro.getTitulo());
                            System.out.println("Autor: " + libro.getAutor().getNombre());
                            System.out.println("Idioma: " + libro.getIdiomas());
                            System.out.println("Descargas: " + libro.getNumeroDeDescargas());
                            System.out.println("-----------------------------------------");
                        });
            }
        } catch (Exception e) {
            System.out.println("Error al consultar libros por idioma: " + e.getMessage());
        }
    }

    private void mostrarIdiomasDisponibles() {
        try {
            List<Libro> todosLosLibros = libroRepository.findAll();

            if (!todosLosLibros.isEmpty()) {
                System.out.println("\n--- Idiomas Disponibles ---");

                // Obtener idiomas únicos usando stream
                List<String> idiomasDisponibles = todosLosLibros.stream()
                        .map(Libro::getIdiomas)
                        .filter(idioma -> idioma != null && !idioma.trim().isEmpty())
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList());

                idiomasDisponibles.forEach(idioma -> {
                    long cantidadLibros = todosLosLibros.stream()
                            .filter(libro -> idioma.equals(libro.getIdiomas()))
                            .count();

                    String nombreIdioma = obtenerNombreIdioma(idioma);
                    System.out.println("• " + idioma + " - " + nombreIdioma + " (" + cantidadLibros + " libros)");
                });

                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error al obtener idiomas disponibles: " + e.getMessage());
        }
    }

    private String obtenerNombreIdioma(String codigo) {
        return switch (codigo.toLowerCase()) {
            case "es" -> "Español";
            case "en" -> "Inglés";
            case "fr" -> "Francés";
            default -> "Idioma desconocido";
        };
    }


}
