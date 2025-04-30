import java.util.Scanner;

/**
 * Programa para realizar operaciones de multiplicación en Java
 */
class Multiplicacion {

    /**
     * Método para multiplicar dos números
     *
     * @param primerNumero Primer factor de la multiplicación
     * @param segundoNumero Segundo factor de la multiplicación
     * @return El resultado de la multiplicación
     */
    public static double multiplicar(double primerNumero, double segundoNumero) {
        return primerNumero * segundoNumero;
    }

    /**
     * Verificar si un número es entero
     */
    public static boolean esEntero(double numero) {
        return numero == Math.floor(numero) && !Double.isInfinite(numero);
    }

    /**
     * Método principal
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("CALCULADORA DE MULTIPLICACIÓN");
        System.out.println("----------------------------");

        try {
            // Solicitar entrada al usuario
            System.out.print("Introduce el primer número: ");
            double primerNumero = scanner.nextDouble();

            System.out.print("Introduce el segundo número: ");
            double segundoNumero = scanner.nextDouble();

            // Realizar la multiplicación
            double resultado = multiplicar(primerNumero, segundoNumero);

            // Mostrar el resultado
            System.out.println("\nEl resultado de " + primerNumero + " × " + segundoNumero + " = " + resultado);

            // Si el resultado es un número entero, mostrarlo como entero
            if (esEntero(resultado)) {
                System.out.println("El resultado en formato entero: " + (int)resultado);
            }

            // Casos especiales de multiplicación
            if (primerNumero == 0 || segundoNumero == 0) {
                System.out.println("Nota: Cualquier número multiplicado por cero es siempre cero.");
            }

            if (primerNumero == 1) {
                System.out.println("Nota: Cualquier número multiplicado por 1 es el mismo número.");
            }

            if (segundoNumero == 1) {
                System.out.println("Nota: Cualquier número multiplicado por 1 es el mismo número.");
            }

            if (primerNumero == -1) {
                System.out.println("Nota: Cualquier número multiplicado por -1 es el negativo del mismo número.");
            }

            if (segundoNumero == -1) {
                System.out.println("Nota: Cualquier número multiplicado por -1 es el negativo del mismo número.");
            }

        } catch (Exception e) {
            System.out.println("Error: Entrada no válida. Asegúrate de introducir números válidos.");
        } finally {
            scanner.close();
        }
    }
}