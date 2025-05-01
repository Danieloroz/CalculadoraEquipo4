import java.util.Scanner;

public class Resta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        System.out.println("¡Bienvenido a la Calculadora de Resta!");
        while (continuar) {
            try {
                // Solicitar el primer número
                System.out.print("\nIngrese el primer número: ");
                double numero1 = scanner.nextDouble();
                // Solicitar el segundo número
                System.out.print("Ingrese el segundo número a restar: ");
                double numero2 = scanner.nextDouble();
                // Realizar la resta
                double resultado = numero1 - numero2;
                // Mostrar el resultado
                System.out.println("\nResultado: " + numero1 + " - " + numero2 + " = " + resultado);
                // Preguntar si desea continuar
                System.out.print("\n¿Desea realizar otra resta? (s/n): ");
                scanner.nextLine(); // Consumir el salto de línea pendiente
                String respuesta = scanner.nextLine().toLowerCase();
                if (respuesta.equals("n")) {
                    continuar = false;
                    System.out.println("¡Gracias por usar la Calculadora de Resta!");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese solo valores numéricos válidos.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
        scanner.close();
    }
}
