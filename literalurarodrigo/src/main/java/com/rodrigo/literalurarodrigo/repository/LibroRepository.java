package com.rodrigo.literalurarodrigo.repository;

import com.rodrigo.literalurarodrigo.model.Autor;
import com.rodrigo.literalurarodrigo.model.Idiomas;
import com.rodrigo.literalurarodrigo.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query(value = "Select a from Libro l join l.autores a")
    List<Autor> obtenerInformacionDeAutores();

    @Query(value = "SELECT a FROM Libro l JOIN l.autores a WHERE anioDeNacimiento>:anio ORDER BY a.anioDeNacimiento DESC")
    List<Autor> obtenerAutoresVivosEnUnAnio(Integer anio);

    @Query(value = "SELECT l FROM Libro l WHERE idiomas =:idioma")
    List<Libro> buscarLibrosPorIdioma(Idiomas idioma);

    @Query(value = "SELECT l FROM Libro l ORDER BY l.descargas DESC LIMIT 5")
    List<Libro> top5LibrosMasDescargados();


    //Optional<Libro> findByTituloContainsIgnoreCase(String nombreSerie);
}
