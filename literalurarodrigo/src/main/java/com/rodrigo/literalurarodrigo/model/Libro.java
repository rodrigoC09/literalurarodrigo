package com.rodrigo.literalurarodrigo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String titulo;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<Autor> autores;//clave foranea de la tabla autores
    @Enumerated(EnumType.STRING)
    private Idiomas idiomas;
    private Double descargas;

    public Libro(){}

    public Libro(List<DatosLibro> resultados) {
    }

    public Libro(String titulo, List<DatosAutor> autores, List<String> idiomas, Double descargas) {
        this.titulo = titulo;
        this.idiomas = Idiomas.fromString(idiomas.get(0));
        this.descargas = descargas;
        this.autores = new ArrayList<>();
        for (DatosAutor infoAutor : autores) {
            Autor autor= new Autor(infoAutor.nombre(),infoAutor.anioDeNacimiento(), infoAutor.anioDeMuerte(),this);
            this.autores.add(autor);
        }


    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        autores.forEach(e->e.setLibro(this));
        this.autores = autores;
    }

    public Idiomas getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idiomas idiomas) {
        this.idiomas = idiomas;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }
    @Override
    public String toString() {
        return  "################   Datos Libro   #################"+"\n"+
                ", Titulo = '" + titulo + '\'' +"\n"+
                        ", Autores = '" + autores + '\'' +"\n"+
                        ", Idiomas = '" + idiomas + '\''+"\n"+
                        ",Descargas = '" +  descargas+'\''+"\n";

    }

}
