package com.rodrigo.literalurarodrigo.model;

import jakarta.persistence.*;

import java.lang.Integer;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    //@Column(unique = true)
    private String nombre;
    private Integer anioDeNacimiento;
    private Integer anioDeMuerte;
    @ManyToOne
    private Libro libro;

    public Autor(){}

    public Autor(Autor autor) {

    }

    public Autor(String nombre, Integer anioDeNacimiento, Integer anioDeMuerte, Libro libro) {
        this.nombre = nombre;
        this.anioDeNacimiento = anioDeNacimiento;
        this.anioDeMuerte = anioDeMuerte;
        this.libro = libro;
    }

    @Override
    public String toString() {
        return
                ", Nombre= '" + nombre + '\'' +"\n"+
                ", Año De Nacimiento= " + anioDeNacimiento +"\n"+
                ", Año De Muerte= " + anioDeMuerte+"\n";
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getanioDeNacimiento() {
        return anioDeNacimiento;
    }

    public void setanioDeNacimiento(Integer anioDeNacimiento) {
        this.anioDeNacimiento = anioDeNacimiento;
    }
    public Integer getanioDeMuerte() {
        return anioDeMuerte;
    }

    public void setanioDeMuerte(Integer anioDeMuerte) {
        this.anioDeMuerte = anioDeMuerte;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
