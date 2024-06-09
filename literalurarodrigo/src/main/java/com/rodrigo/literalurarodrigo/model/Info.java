package com.rodrigo.literalurarodrigo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Info(
        @JsonAlias("results") List<DatosLibro> resultados
) {

}
