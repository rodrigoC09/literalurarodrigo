package com.rodrigo.literalurarodrigo.principal;

import com.rodrigo.literalurarodrigo.model.DatosLibro;
import com.rodrigo.literalurarodrigo.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE="http://gutendex.com/books?";
    private final String URL_BUSCAR_TITULO = "search=";


    public void mostrarMenu(){
        var opcion =-1;
        while (opcion!=0){
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibrosPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnUnDeterminadoAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la Aplicacion...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

    private void buscarLibrosPorTitulo() {
        //DatosLibro datos =getDatosLibr();
    }
    private void listarLibrosRegistrados() {
    }
    private void listarAutoresRegistrados() {
    }

    private void listarAutoresVivosEnUnDeterminadoAnio() {
    }

    private void listarLibrosPorIdioma() {
    }
}
