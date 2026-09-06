import java.util.Scanner;

class Nodo2 {
    int dato;
    Nodo2 siguiente;

    public Nodo2(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

public class Ejercicio2_InsercionEliminacion {

    static Nodo2 cabeza;

    // Retorna true si la lista no tiene nodos
    public static boolean estaVacia() {
        return cabeza == null;
    }

    // Recorre e imprime la lista hasta volver a la cabeza
    public static void mostrarLista() {
        if (estaVacia()) {
            System.out.println("Lista vacia");
            return;
        }
        Nodo2 actual = cabeza;
        do {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        } while (actual != cabeza);
        System.out.println("(vuelve a " + cabeza.dato + ")");
    }

    // Insertar en una posicion especifica (0 = inicio)
    public static void insertarEnPosicion(int valor, int posicion) {
        Nodo2 nuevo = new Nodo2(valor);

        if (estaVacia() || posicion == 0) {
            if (estaVacia()) {
                cabeza = nuevo;
                nuevo.siguiente = cabeza;
            } else {
                Nodo2 ultimo = cabeza;
                while (ultimo.siguiente != cabeza) {
                    ultimo = ultimo.siguiente;
                }
                nuevo.siguiente = cabeza;
                ultimo.siguiente = nuevo;
                cabeza = nuevo;
            }
            return;
        }

        Nodo2 actual = cabeza;
        int i = 0;
        // se ubica en el nodo ANTERIOR a la posicion pedida
        while (i < posicion - 1 && actual.siguiente != cabeza) {
            actual = actual.siguiente;
            i++;
        }
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
    }

    // Elimina el nodo en la posicion dada (0 = primer nodo); reconecta al vecino
    public static void eliminarPorPosicion(int posicion) {
        if (estaVacia()) {
            System.out.println("No se puede eliminar, la lista esta vacia");
            return;
        }

        // caso: un solo nodo
        if (cabeza.siguiente == cabeza) {
            cabeza = null;
            return;
        }

        if (posicion == 0) {
            Nodo2 ultimo = cabeza;
            while (ultimo.siguiente != cabeza) {
                ultimo = ultimo.siguiente;
            }
            cabeza = cabeza.siguiente;
            ultimo.siguiente = cabeza;
            return;
        }

        Nodo2 anterior = cabeza;
        int i = 0;
        while (i < posicion - 1 && anterior.siguiente != cabeza) {
            anterior = anterior.siguiente;
            i++;
        }
        Nodo2 aEliminar = anterior.siguiente;
        anterior.siguiente = aEliminar.siguiente;
    }

    // Busca el nodo con ese valor y lo desconecta de la lista
    public static void eliminarPorValor(int valor) {
        if (estaVacia()) {
            System.out.println("No se puede eliminar, la lista esta vacia");
            return;
        }

        // caso: un solo nodo
        if (cabeza.siguiente == cabeza) {
            if (cabeza.dato == valor) {
                cabeza = null;
            } else {
                System.out.println("Valor no encontrado");
            }
            return;
        }

        Nodo2 actual = cabeza;
        Nodo2 anterior = null;

        do {
            if (actual.dato == valor) {
                if (actual == cabeza) {
                    Nodo2 ultimo = cabeza;
                    while (ultimo.siguiente != cabeza) {
                        ultimo = ultimo.siguiente;
                    }
                    cabeza = cabeza.siguiente;
                    ultimo.siguiente = cabeza;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        } while (actual != cabeza);

        System.out.println("Valor no encontrado");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion, valor, posicion;

        do {
            System.out.println("\n------------------------------");
            System.out.println("  INSERCION Y ELIMINACION");
            System.out.println("------------------------------");
            System.out.println("1. Insertar en posicion especifica");
            System.out.println("2. Eliminar por posicion");
            System.out.println("3. Eliminar por valor");
            System.out.println("4. Mostrar lista");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Valor a insertar: ");
                    valor = sc.nextInt();
                    System.out.print("Posicion (0 = inicio): ");
                    posicion = sc.nextInt();
                    System.out.println("Lista ANTES:");
                    mostrarLista();
                    insertarEnPosicion(valor, posicion);
                    System.out.println("Lista DESPUES:");
                    mostrarLista();
                    break;
                case 2:
                    System.out.print("Posicion a eliminar: ");
                    posicion = sc.nextInt();
                    System.out.println("Lista ANTES:");
                    mostrarLista();
                    eliminarPorPosicion(posicion);
                    System.out.println("Lista DESPUES:");
                    mostrarLista();
                    break;
                case 3:
                    System.out.print("Valor a eliminar: ");
                    valor = sc.nextInt();
                    System.out.println("Lista ANTES:");
                    mostrarLista();
                    eliminarPorValor(valor);
                    System.out.println("Lista DESPUES:");
                    mostrarLista();
                    break;
                case 4:
                    mostrarLista();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 5);

        sc.close();
    }
}
