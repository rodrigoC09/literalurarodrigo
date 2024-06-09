package com.rodrigo.literalurarodrigo.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> tClass);
}
