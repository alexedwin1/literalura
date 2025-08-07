package com.digitaltelcom.literalura.model;

import com.digitaltelcom.literalura.DTO.DatosAutor;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String nombre;
    @Column(name = "fecha_nacimiento")
    private Integer fechaDeNacimiento;
    @Column(name = "fecha_fallecimiento")
    private Integer fechaDeFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor() {}

    public Autor(DatosAutor datosAutor) {
        this.nombre = String.valueOf(datosAutor.nombre());
        this.fechaDeNacimiento = Integer.valueOf(datosAutor.fechaDeNacimiento());
        this.fechaDeFallecimiento = Integer.valueOf(datosAutor.fechaDeFallecimiento());
    }

    @Override
    public String toString() {
        return
                " nombre='" + nombre + '\'' +
                        ", Fecha de nacimiento=" + fechaDeFallecimiento +
                        ", Fecha de fallecimiento=" + fechaDeFallecimiento;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}

