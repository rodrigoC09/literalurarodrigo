package com.rodrigo.literalurarodrigo.principal;

import com.rodrigo.literalurarodrigo.model.*;
//import com.rodrigo.literalurarodrigo.repository.LibroRepository;
import com.rodrigo.literalurarodrigo.repository.LibroRepository;
import com.rodrigo.literalurarodrigo.service.ConsumoAPI;
import com.rodrigo.literalurarodrigo.service.ConvierteDatos;
import com.rodrigo.literalurarodrigo.model.Idiomas;


import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE="http://gutendex.com/books/";
    private final String URL_BUSCAR_TITULO = "?search=%20";
    private Optional<Libro> libroBuscado;
    private ConvierteDatos conversor =new ConvierteDatos();
    private List<DatosLibro> datosLibros= new ArrayList<>();
    private String libroSeleccionado;
    //
    private List<Libro> libros;

    private LibroRepository repositorio;
    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }

//    public Principal(LibroRepository repository) {
//        this.repositorio = repository;
//    }


    public void mostrarMenu(){
        var opcion =-1;
        while (opcion!=0){
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Top 5 libros con mas descargas
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarInformacionLibro();
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
                case 6:
                    top5LibrosMasDescargados();
                    break;
                case 0:
                    System.out.println("Cerrando la Aplicacion...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }



    private String capturarInformacionUsuario(){
        System.out.println("Escriba el nombdel libro que sea buscar");
        libroSeleccionado = teclado.nextLine();
        return libroSeleccionado;
    }
    //obtener datos de la API
    private Info obtenerDatosAPI(String tituloLibro){
        var json = consumoApi.obtenerDatos(URL_BASE+URL_BUSCAR_TITULO+tituloLibro.replace(" ", "+"));
        var datos = conversor.obtenerDatos(json, Info.class);
        return datos;
    }

    private DatosLibro getDatosLibro(){
        System.out.println("Escribe el nombre del libro");
        var nombreLibro= teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+URL_BUSCAR_TITULO+nombreLibro.replace(" ", "+"));
        System.out.println(json);
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        return datos;
    }


    private void buscarLibrosPorTitulo() {


        String titulo = capturarInformacionUsuario();
        Info datos = obtenerDatosAPI(titulo);
        Libro libro = new Libro(datos.resultados());
        repositorio.save(libro);
        System.out.println(libro);

//        DatosLibro datos = getDatosLibro();
//        Libro libro = new Libro(datos);
//        repositorio.save(libro);
//        System.out.println(datos);



    }
    ////////////////           1           ////////////////////////////
    private Optional<Libro> buscarInformacionLibro(){
        String tituloLibro =capturarInformacionUsuario();
        Info libroInfo = obtenerDatosAPI(tituloLibro);
        Optional<Libro> libro= obtenerInfoLibro(libroInfo, tituloLibro);

        if (libro.isPresent()) {
            var l = libro.get();
            repositorio.save(l);
            System.out.println(l);
        }else{
            System.out.println("\nLibro no encontrado\n");
        }
        //listarLibrosRegistrados();
        return libro;

    }

    private Optional<Libro> obtenerInfoLibro(Info infoLibro, String tituloLibro) {

        Optional<Libro> libros = infoLibro.resultados().stream()
                .filter(l -> l.titulo().toLowerCase().contains(tituloLibro.toLowerCase()))
                .map(b -> new Libro(b.titulo(), b.autores(),b.idiomas(), b.descargas()))
                .findFirst();
        return libros;
    }


    //////////////       2        ///////////////////
    private void listarLibrosRegistrados() {
        libros = repositorio.findAll();
        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }
    ///////////// OPCION 3
    private void listarAutoresRegistrados() {
        List<Autor> autores = repositorio.obtenerInformacionDeAutores();

        autores.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(a-> System.out.printf("Autor: %s Año de nacimiento: %s Año de muerte: %s\n",
                        a.getNombre(), a.getanioDeNacimiento(), a.getanioDeMuerte()));
    }
    //////////// OPCION 4 /////////////
    private void listarAutoresVivosEnUnDeterminadoAnio() {
        int anio;
        String anioS;
        do {
            System.out.printf("Ingrese un año para saber que autores estaban vivos\n");

            anioS = teclado.nextLine();
            //teclado.nextLine();


        } while (!esEntero(anioS));

        anio = Integer.parseInt(anioS);
        List<Autor> autoresVivos = repositorio.obtenerAutoresVivosEnUnAnio(anio);

        autoresVivos.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(a -> System.out.printf("Autor: %s Año de nacimiento: %s Año de muerte: %s\n",
                        a.getNombre(), a.getanioDeNacimiento(), a.getanioDeMuerte()));
    }
    //////////// OPCION 5 ///////////////
    private void listarLibrosPorIdioma() {
        String texto;

        System.out.println("""
                Escoja un Idioma para para la busqueda de libros
                (####  es -- Españpl  ####)
                (####  en -- Inglés  ####)
                (####  pt -- Portugues  ####)
                (####  fr -- Francés  ####)
                (####  it -- Italiano  ####)
                """);
        texto = teclado.nextLine();

        var idioma = Idiomas.fromString(texto);
        System.out.println("El idioma es:  "+idioma);
        List<Libro> idiomaLibro = repositorio.buscarLibrosPorIdioma(idioma);

        idiomaLibro.stream()
//                .filter(i-> Objects.equals(i, idioma))
                //.anyMatch(i-> i==)
                .forEach(System.out::println);
    }
    //////////// Opcion 6 /////////////
    private void top5LibrosMasDescargados() {
        List<Libro> top5Libros = repositorio.top5LibrosMasDescargados();
        top5Libros.forEach(l->
                System.out.println("######################################\n"+"Libro: "+l.getTitulo()+" Descargas: "+l.getDescargas()));
    }
    public static boolean esEntero(String text) {
        int v;
        try {
            v = Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;

        }
    }

    private static boolean contiene(List<Libro> idiomas, String text) {
        for (Libro e : idiomas) {
            if (Objects.equals(e, text)) {
                return true;
            }
        }
        return false;
    }
}
