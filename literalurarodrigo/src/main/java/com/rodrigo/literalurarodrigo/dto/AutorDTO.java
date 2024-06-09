package com.rodrigo.literalurarodrigo.dto;

import java.time.LocalDate;

public record AutorDTO(
         String nombre,
         LocalDate fechaDeNacimiento,
         LocalDate fechaDeMuerte
) {
}
