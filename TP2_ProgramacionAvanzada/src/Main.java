import Biblioteca.Libro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Libro> libros = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);  // Crear un solo scanner para todo el programa
        menuBiblioteca(libros, scanner);
    }

    public static void menuBiblioteca(List<Libro> libros, Scanner scanner) {
        int opc = 0;
        while(opc != 5) {
            System.out.println("Bienvenido a la Biblioteca");
            System.out.println("1. Listar Libros");
            System.out.println("2. Buscar Libro");
            System.out.println("3. Ingresar Libro");
            System.out.println("4. Eliminar Libro");
            System.out.println("5. Salir");
            opc = scanner.nextInt(); // Leer la opción del menú

            // Limpiar el buffer del scanner
            scanner.nextLine();

            switch (opc) {
                case 1:
                    System.out.println("Libros de la biblioteca");
                    mostrarLibros(libros);
                    break;
                case 2:
                    System.out.print("Elije un libro para buscar: ");
                    String buscarLibro = scanner.nextLine();
                    buscarLibro(libros, buscarLibro);
                    break;
                case 3:
                    System.out.print("Dicta el titulo: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Dicta el autor: ");
                    String autor = scanner.nextLine();

                    System.out.print("Dicta el tipo: ");
                    String tipo = scanner.nextLine();

                    System.out.print("Dicta el año: ");
                    int año = scanner.nextInt();

                    // Limpiar el buffer después de leer el año
                    scanner.nextLine();

                    Libro libroIngresado = new Libro(titulo, autor, tipo, año);
                    ingresarLibro(libros, libroIngresado);
                    break;
                case 4:
                    System.out.print("Elije un libro para eliminar: ");
                    String eliminarLibro = scanner.nextLine();
                    eliminarLibro(libros, eliminarLibro);
                    break;
                case 5:
                    System.out.println("Gracias por usar la biblioteca.");
                    break;
                default:
                    System.out.println("La opción elegida no existe.");
                    break;
            }
        }
    }

    public static void mostrarLibros(List<Libro> libros) {
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la biblioteca.");
        } else {
            for (Libro libro : libros) {
                System.out.println("Título: " + libro.getTitulo() +
                        "\nAutor: " + libro.getAutor() +
                        "\nTipo: " + libro.getTipo() +
                        "\nAño: " + libro.getAño() + "\n");
            }
        }
    }

    public static void ingresarLibro(List<Libro> libros, Libro libroIngresado) {
        libros.add(libroIngresado);
        System.out.println("Libro ingresado con éxito.");
    }

    public static void buscarLibro(List<Libro> libros, String libroBuscado) {
        boolean encontrado = false;
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(libroBuscado)) {
                System.out.println(libro.getTitulo() + " está en la biblioteca.");
                encontrado = true;
                break;  // Salir del bucle si se encuentra el libro
            }
        }
        if (!encontrado) {
            System.out.println(libroBuscado + " no está en la biblioteca.");
        }
    }

    public static void eliminarLibro(List<Libro> libros, String eliminarLibro) {
        boolean eliminado = false;
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(eliminarLibro)) {
                libros.remove(libro);
                System.out.println("Libro " + eliminarLibro + " eliminado con éxito.");
                eliminado = true;
                break;  // Salir del bucle después de eliminar el libro
            }
        }
        if (!eliminado) {
            System.out.println(eliminarLibro + " no está en la biblioteca.");
        }
    }
}