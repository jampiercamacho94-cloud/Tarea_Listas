import java.util.Scanner;

class ProcesoNodo {
    String nombre;
    int tiempoRestante;
    ProcesoNodo siguiente;

    public ProcesoNodo(String nombre, int tiempoRestante) {
        this.nombre = nombre;
        this.tiempoRestante = tiempoRestante;
        this.siguiente = null;
    }
}

public class Ejercicio3_RoundRobin {

    static ProcesoNodo cabeza;
    static final int QUANTUM = 2;

    // Retorna true si no quedan procesos en la lista
    public static boolean estaVacia() {
        return cabeza == null;
    }

    // Agrega un nuevo proceso al final de la cola circular
    public static void agregarProceso(String nombre, int tiempo) {
        ProcesoNodo nuevo = new ProcesoNodo(nombre, tiempo);
        if (estaVacia()) {
            cabeza = nuevo;
            nuevo.siguiente = cabeza;
            return;
        }
        ProcesoNodo actual = cabeza;
        while (actual.siguiente != cabeza) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevo;
        nuevo.siguiente = cabeza;
    }

    // Imprime el estado actual de la cola de procesos
    public static void mostrarEstado() {
        if (estaVacia()) {
            System.out.println("   Lista vacia (no quedan procesos)");
            return;
        }
        ProcesoNodo actual = cabeza;
        do {
            System.out.print("   [" + actual.nombre + ":" + actual.tiempoRestante + "] -> ");
            actual = actual.siguiente;
        } while (actual != cabeza);
        System.out.println("(vuelve a " + cabeza.nombre + ")");
    }

    // Simulacion Round Robin con quantum fijo de 2
    public static void simular() {
        if (estaVacia()) {
            System.out.println("No hay procesos para simular");
            return;
        }

        int turno = 1;
        ProcesoNodo actual = cabeza;
        ProcesoNodo anterior = null; // para poder eliminar el nodo actual

        // se ubica "anterior" en el ultimo nodo antes de empezar
        anterior = cabeza;
        while (anterior.siguiente != cabeza) {
            anterior = anterior.siguiente;
        }

        while (!estaVacia()) {
            System.out.println("\n--- Turno " + turno + " ---");
            System.out.println("Ejecutando: " + actual.nombre + " (tiempo restante antes: " + actual.tiempoRestante + ")");

            int tiempoUsado = Math.min(QUANTUM, actual.tiempoRestante);
            actual.tiempoRestante -= tiempoUsado;

            System.out.println("Tiempo usado: " + tiempoUsado + " | Tiempo restante: " + actual.tiempoRestante);

            if (actual.tiempoRestante <= 0) {
                System.out.println(actual.nombre + " termino su ejecucion y sale de la lista");

                if (actual == cabeza) {
                    if (actual.siguiente == actual) {
                        // era el unico proceso
                        cabeza = null;
                        actual = null;
                    } else {
                        cabeza = actual.siguiente;
                        anterior.siguiente = cabeza;
                        actual = cabeza;
                    }
                } else {
                    anterior.siguiente = actual.siguiente;
                    actual = anterior.siguiente;
                }
            } else {
                System.out.println(actual.nombre + " no termino, vuelve al final del ciclo");
                anterior = actual;
                actual = actual.siguiente;
            }

            System.out.println("Estado de la lista:");
            mostrarEstado();
            turno++;
        }

        System.out.println("\nTodos los procesos han finalizado.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n------------------------------");
            System.out.println("       ROUND ROBIN");
            System.out.println("------------------------------");
            System.out.println("1. Agregar proceso");
            System.out.println("2. Mostrar lista de procesos");
            System.out.println("3. Iniciar simulacion");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del proceso: ");
                    String nombre = sc.next();
                    System.out.print("Tiempo restante: ");
                    int tiempo = sc.nextInt();
                    agregarProceso(nombre, tiempo);
                    break;
                case 2:
                    mostrarEstado();
                    break;
                case 3:
                    simular();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 4);

        sc.close();
    }
}