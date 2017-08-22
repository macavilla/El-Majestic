
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author AULA1
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String codigo, quiereSalir;
        boolean salir = false;
        int contador = 0;
        
        String[] peliculas;
        peliculas = new String[5];
        String[] horario;
        horario = new String[5];
        
        int[] capacidad;
        capacidad = new int[5];
        
        for (int i = 0; i < 5; i++) { //CAPACIDAD DE TODAS LAS PELÍCULAS
            capacidad[i] = 20;
        }

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Bienvenido al Sistema Cine Majestic");
            System.out.println("Ingrese Código de operación: ap - lp - mp - vt - fin");
            codigo = sc.nextLine();

            switch (codigo) {
                case "ap":
                    System.out.println("Agrego pelicula");
                    contador = agregarPelicula(contador, peliculas, horario);
                    //agregar pelicula
                    break;
                case "lp":
                    System.out.println("Listo Peliculas");
                    listarPeliculas(peliculas, horario, contador);
                    break;
                case "mp":
                    System.out.println("Modificacion de Pelicula");
                    modificarPeliculas(contador, peliculas, horario);
                    break;
                case "vt":
                    System.out.println("Venta de Ticket");
                    venderTickets(peliculas, contador, capacidad);
                    break;
                case "fin":
                    System.out.println("Esta seguro de terminar? S/N");
                    quiereSalir = sc.nextLine();
                    if (quiereSalir.equals("s")) {
                        salir = true;
                    }
                    break;
                default:
                    System.out.println("Operación no válida.");
                    break;
            }
            borrarPantallaYEsperar();

        } while (salir == false);
    }//fin del metodo main

    public static void borrarPantallaYEsperar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("*\n*\nPresione una tecla para continuar");
        sc.nextLine();
        System.out.println("*\n*\n*");
    }

    public static int agregarPelicula(int contador, String[] peliculas, String[] horario) {

        String peliculaIngresada;
        String horarioIngresado;

        System.out.println("Ingrese el nombre de la película:");
        Scanner ingPeli = new Scanner(System.in);
        peliculaIngresada = ingPeli.nextLine();

        if (peliculaSeRepite(contador, peliculaIngresada, peliculas) == false) {
            if (contador <=4) {
                peliculas[contador] = peliculaIngresada;
            System.out.println("La película " + contador + " se ingresó correctamente.");

            System.out.println("Ingrese el horario para la película " + peliculas[contador]);
            System.out.println("Formato (HH:MM)");
            Scanner ingHorario = new Scanner(System.in);
            horarioIngresado = ingHorario.nextLine();
            horario[contador] = horarioIngresado;

            contador++;
            } else {
                System.out.println("Ya hay 5 películas en cartelera.");
            }
            
        }else{
            System.out.println("La película que ingresó ya está en cartelera.");
        }
        /* do {
            peliculas[indice] = peliculaIngresada;
            System.out.println("La película se ingresó correctamente.");

        System.out.println("Ingrese el horario para la película " + peliculas[indice]);
        Scanner ingHorario = new Scanner(System.in);
        horarioIngresado = ingHorario.nextLine();
        horario[indice] = horarioIngresado;
        } while (peliculaRepetida(indice, peliculaIngresada, peliculas) == false);
         */
        return contador;
    }

    public static boolean peliculaSeRepite(int indice, String peliculaIngresada, String[] peliculas) {
        boolean repetida = false;
        for (int i = 0; i < indice; i++) {
            if (peliculaIngresada.equals(peliculas[i])) {
                repetida = true;
            }
        }
        return repetida;
    }

    public static void listarPeliculas(String[] peliculas, String[] horario, int contador) {
        System.out.println("Listado de películas:");
        for (int i = 0; i < contador; i++) {
            System.out.println("Título: " + peliculas[i]);
            System.out.println("Horario: " + horario[i] + " horas.");
            System.out.println("-----------------------------------");
        }
    }

    public static void modificarPeliculas(int indice, String[] peliculas, String[] horario) {
        String peliculaModif;
        System.out.println("Ingrese el título de la película a modificar:");
        Scanner ingresoTeclado = new Scanner(System.in);
        peliculaModif = ingresoTeclado.nextLine();

        if (peliculaSeRepite(indice, peliculaModif, peliculas)) {
            for (int i = 0; i < indice; i++) {
                if (peliculaModif.equals(peliculas[i])) {
                    System.out.println("Modificar película: " + peliculas[i]);
                    System.out.println("Horario: " + horario[i]);
                    System.out.println("-----------------------------------");
                    System.out.println("Ingrese el nuevo título:");

                    Scanner modifPelicula = new Scanner(System.in);
                    peliculas[i] = modifPelicula.nextLine();
                    System.out.println("Ingrese el nuevo horario de " + peliculas[i]);
                    Scanner modifHorario = new Scanner(System.in);
                    horario[i] = modifHorario.nextLine();
                }
            }
        } else {
            System.out.println("No se encontró el título " + peliculaModif + " en la base de datos. Intente nuevamente.");
        }
    }
    
    public static void venderTickets(String [] peliculas, int contador, int [] capacidad){
        
        int tickets;
        String peliculaIngresada;
        
        System.out.println("Ingrese película: ");
        Scanner ingPelicula = new Scanner(System.in);
        peliculaIngresada = ingPelicula.nextLine();
        
        if (peliculaSeRepite(contador, peliculaIngresada, peliculas)) { //CONTROLA SI LA PELICULA ESTÁ EN CARTELERA
            for (int i = 0; i < contador; i++) {
                if (peliculaIngresada.equals(peliculas[i])) { //UBICA LA PELICULA EN EL ARRAY
                    System.out.println("Ingrese los tickets vendidos para " + peliculas[i]);
                    Scanner ingTickets = new Scanner(System.in);
                    tickets = ingTickets.nextInt();
                    if (tickets <= capacidad[i]) { //CONTROLA QUE LOS TICKETS VENDIDOS NO SUPEREN LA CAPACIDAD
                        capacidad[i] = capacidad[i] - tickets;
                    }
                }
            }
        } else {
            System.out.println("La película" + peliculaIngresada +" no se encuentra en la cartelera. Intente nuevamente.");
        }
        //FALTA CALCULAR PRECIO DE ENTRADAS, $70 CADA UNA!!!!!!!!!
    }

} // fin clase
