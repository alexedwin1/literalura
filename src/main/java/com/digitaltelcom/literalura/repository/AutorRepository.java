package com.digitaltelcom.literalura.repository;

import com.digitaltelcom.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findByNombre(String nombre);

    Optional<Autor> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :año AND (a.fechaDeFallecimiento IS NULL OR a.fechaDeFallecimiento >= :año)")
    List<Autor> findAutoresVivosEnAnio(@Param("año") Integer año);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeFallecimiento IS NULL")
    List<Autor> findAutoresVivos();

    List<Autor> findByFechaDeNacimientoLessThanEqual(Integer año);

    boolean existsByNombre(String nombre);
}