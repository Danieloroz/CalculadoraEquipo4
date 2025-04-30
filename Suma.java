import java.util.Scanner;

public class Suma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== CALCULADORA DE SUMA =====");

        // Solicitar el primer número
        System.out.print("Ingrese el primer número: ");
        double numero1 = scanner.nextDouble();

        // Solicitar el segundo número
        System.out.print("Ingrese el segundo número: ");
        double numero2 = scanner.nextDouble();

        // Realizar la suma
        double resultado = numero1 + numero2;

        // Mostrar el resultado
        System.out.println("\nEl resultado de " + numero1 + " + " + numero2 + " = " + resultado);

        scanner.close();
    }
}
