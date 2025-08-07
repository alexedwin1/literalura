package com.digitaltelcom.literalura.repository;

import com.digitaltelcom.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTitulo(String titulo);

    @Query("SELECT l FROM Libro l WHERE l.idiomas = :idiomas")
    List<Libro> findByIdioma(@Param("idiomas") String idiomas);

    @Query("SELECT l FROM Libro l ORDER BY l.numeroDeDescargas DESC")
    List<Libro> findAllOrderByDownloadsDesc();

    boolean existsByTitulo(String titulo);
}