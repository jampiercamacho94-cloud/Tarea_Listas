import java.util.Scanner;

class CancionNodo {
    String nombre;
    CancionNodo siguiente;

    public CancionNodo(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
    }
}

public class Ejercicio5_PlaylistCircular {

    static CancionNodo cabeza;
    static CancionNodo actual; // cancion que se esta "reproduciendo"

    // Retorna true si la playlist no tiene canciones
    public static boolean estaVacia() {
        return cabeza == null;
    }

    // Agrega la cancion al inicio y la convierte en la nueva cabeza
    public static void agregarInicio(String nombre) {
        CancionNodo nueva = new CancionNodo(nombre);
        if (estaVacia()) {
            cabeza = nueva;
            nueva.siguiente = cabeza;
            actual = cabeza;
            return;
        }
        CancionNodo ultimo = cabeza;
        while (ultimo.siguiente != cabeza) {
            ultimo = ultimo.siguiente;
        }
        nueva.siguiente = cabeza;
        ultimo.siguiente = nueva;
        cabeza = nueva;
    }

    // Agrega la cancion al final, sin cambiar la cabeza
    public static void agregarFinal(String nombre) {
        CancionNodo nueva = new CancionNodo(nombre);
        if (estaVacia()) {
            cabeza = nueva;
            nueva.siguiente = cabeza;
            actual = cabeza;
            return;
        }
        CancionNodo ultimo = cabeza;
        while (ultimo.siguiente != cabeza) {
            ultimo = ultimo.siguiente;
        }
        ultimo.siguiente = nueva;
        nueva.siguiente = cabeza;
    }

    // Recorre la playlist marcando cual cancion se esta reproduciendo
    public static void mostrarPlaylist() {
        if (estaVacia()) {
            System.out.println("Playlist vacia");
            return;
        }
        CancionNodo nodo = cabeza;
        do {
            String marca = (nodo == actual) ? " (reproduciendo)" : "";
            System.out.print(nodo.nombre + marca + " -> ");
            nodo = nodo.siguiente;
        } while (nodo != cabeza);
        System.out.println("(vuelve a " + cabeza.nombre + ")");
    }

    // Al llegar al final, "siguiente" apunta de nuevo a cabeza automaticamente
    public static void reproducirSiguiente() {
        if (estaVacia()) {
            System.out.println("Playlist vacia, no hay nada que reproducir");
            return;
        }
        actual = actual.siguiente;
        System.out.println("Reproduciendo ahora: " + actual.nombre);
    }

    // Busca la cancion por nombre y la desconecta de la lista
    public static void eliminarPorNombre(String nombre) {
        if (estaVacia()) {
            System.out.println("Playlist vacia");
            return;
        }

        if (cabeza.siguiente == cabeza) {
            if (cabeza.nombre.equals(nombre)) {
                cabeza = null;
                actual = null;
            } else {
                System.out.println("Cancion no encontrada");
            }
            return;
        }

        CancionNodo nodo = cabeza;
        CancionNodo anterior = null;

        do {
            if (nodo.nombre.equals(nombre)) {
                if (nodo == actual) {
                    actual = nodo.siguiente; // si se elimina la actual, pasa a la siguiente
                }
                if (nodo == cabeza) {
                    CancionNodo ultimo = cabeza;
                    while (ultimo.siguiente != cabeza) {
                        ultimo = ultimo.siguiente;
                    }
                    cabeza = cabeza.siguiente;
                    ultimo.siguiente = cabeza;
                } else {
                    anterior.siguiente = nodo.siguiente;
                }
                System.out.println(nombre + " eliminada de la playlist");
                return;
            }
            anterior = nodo;
            nodo = nodo.siguiente;
        } while (nodo != cabeza);

        System.out.println("Cancion no encontrada");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n------------------------------");
            System.out.println("     PLAYLIST CIRCULAR");
            System.out.println("------------------------------");
            System.out.println("1. Agregar cancion al inicio");
            System.out.println("2. Agregar cancion al final");
            System.out.println("3. Mostrar playlist completa");
            System.out.println("4. Reproducir siguiente cancion");
            System.out.println("5. Eliminar cancion por nombre");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre de la cancion: ");
                    agregarInicio(sc.next());
                    break;
                case 2:
                    System.out.print("Nombre de la cancion: ");
                    agregarFinal(sc.next());
                    break;
                case 3:
                    mostrarPlaylist();
                    break;
                case 4:
                    reproducirSiguiente();
                    break;
                case 5:
                    System.out.print("Nombre de la cancion a eliminar: ");
                    eliminarPorNombre(sc.next());
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 6);

        sc.close();
    }
}
