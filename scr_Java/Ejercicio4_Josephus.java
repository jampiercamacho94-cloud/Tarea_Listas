import java.util.Scanner;

class PersonaNodo {
    int id;
    PersonaNodo siguiente;

    public PersonaNodo(int id) {
        this.id = id;
        this.siguiente = null;
    }
}

public class Ejercicio4_Josephus {

    // Arma el circulo de n personas y elimina cada k-esima hasta dejar 1 sola
    public static void resolverJosephus(int n, int k) {
        PersonaNodo cabeza = new PersonaNodo(1);
        PersonaNodo actual = cabeza;

        // se construye el circulo con n personas
        for (int i = 2; i <= n; i++) {
            actual.siguiente = new PersonaNodo(i);
            actual = actual.siguiente;
        }
        actual.siguiente = cabeza; // se cierra el circulo

        System.out.println("\n--- Josephus con n=" + n + ", k=" + k + " ---");
        System.out.print("Orden de eliminacion: ");

        PersonaNodo anterior = actual; // apunta al ultimo (anterior a cabeza)
        actual = cabeza;

        while (actual.siguiente != actual) {
            // avanzar k-1 posiciones para llegar a la k-esima persona
            for (int i = 1; i < k; i++) {
                anterior = actual;
                actual = actual.siguiente;
            }
            System.out.print(actual.id + " ");
            anterior.siguiente = actual.siguiente; // se elimina "actual"
            actual = anterior.siguiente;
        }

        System.out.println("\nSuperviviente final: " + actual.id);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n------------------------------");
            System.out.println("    PROBLEMA DE JOSEPHUS");
            System.out.println("------------------------------");
            System.out.println("1. Ejecutar caso n=5, k=2");
            System.out.println("2. Ejecutar caso n=7, k=3");
            System.out.println("3. Ingresar n y k personalizados");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    resolverJosephus(5, 2);
                    break;
                case 2:
                    resolverJosephus(7, 3);
                    break;
                case 3:
                    System.out.print("Ingrese n: ");
                    int n = sc.nextInt();
                    System.out.print("Ingrese k: ");
                    int k = sc.nextInt();
                    resolverJosephus(n, k);
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