package com.rodrigo.literalurarodrigo.model;

public enum Idiomas {
    ESPAÑOL("es"),
    INGLES("en"),
    PORTUGUES("pt"),
    FRANCES("fr"),
    ITALIANO("it");

    private String lenguagesBiblio;

    Idiomas (String lenguagesBiblio){this.lenguagesBiblio = lenguagesBiblio;}

    public static Idiomas fromString(String text) {
        for (Idiomas idiomasB : Idiomas.values())
            if (idiomasB.lenguagesBiblio.equalsIgnoreCase(text)) {
                return idiomasB;
            }

        throw new IllegalArgumentException("Ningun idioma encontrado: "+ text);

    }
}
