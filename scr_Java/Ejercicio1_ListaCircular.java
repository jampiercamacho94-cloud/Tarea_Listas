import java.util.Scanner;

class Nodo1 {
    int dato;
    Nodo1 siguiente;

    public Nodo1(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

public class Ejercicio1_ListaCircular {

    static Nodo1 cabeza;

    // Retorna true si la lista no tiene nodos
    public static boolean estaVacia() {
        return cabeza == null;
    }

    // Inserta un nuevo nodo y lo convierte en la nueva cabeza
    public static void insertarInicio(int valor) {
        Nodo1 nuevo = new Nodo1(valor);
        if (estaVacia()) {
            cabeza = nuevo;
            nuevo.siguiente = cabeza;
            return;
        }
        Nodo1 actual = cabeza;
        while (actual.siguiente != cabeza) {
            actual = actual.siguiente;
        }
        nuevo.siguiente = cabeza;
        actual.siguiente = nuevo;
        cabeza = nuevo;
    }

    // Inserta un nuevo nodo al final, sin cambiar la cabeza
    public static void insertarFinal(int valor) {
        Nodo1 nuevo = new Nodo1(valor);
        if (estaVacia()) {
            cabeza = nuevo;
            nuevo.siguiente = cabeza;
            return;
        }
        Nodo1 actual = cabeza;
        while (actual.siguiente != cabeza) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevo;
        nuevo.siguiente = cabeza;
    }

    // Recorre la lista hasta volver a la cabeza (no hay NULL, es circular)
    public static void mostrarLista() {
        if (estaVacia()) {
            System.out.println("Lista vacia");
            return;
        }
        Nodo1 actual = cabeza;
        do {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        } while (actual != cabeza);
        System.out.println("(vuelve a " + cabeza.dato + ")");
    }

    // Cuenta los nodos recorriendo la lista una sola vuelta
    public static int contarElementos() {
        if (estaVacia()) return 0;
        int contador = 0;
        Nodo1 actual = cabeza;
        do {
            contador++;
            actual = actual.siguiente;
        } while (actual != cabeza);
        return contador;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion, valor;

        do {
            System.out.println("\n------------------------------");
            System.out.println("      LISTA CIRCULAR");
            System.out.println("------------------------------");
            System.out.println("1. Insertar al inicio");
            System.out.println("2. Insertar al final");
            System.out.println("3. Mostrar lista");
            System.out.println("4. Verificar si esta vacia");
            System.out.println("5. Contar elementos");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese valor: ");
                    valor = sc.nextInt();
                    insertarInicio(valor);
                    break;
                case 2:
                    System.out.print("Ingrese valor: ");
                    valor = sc.nextInt();
                    insertarFinal(valor);
                    break;
                case 3:
                    mostrarLista();
                    break;
                case 4:
                    System.out.println("Esta vacia? " + estaVacia());
                    break;
                case 5:
                    System.out.println("Numero de elementos: " + contarElementos());
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