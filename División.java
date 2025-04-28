import java.util.Scanner;

/**
 * Programa para realizar operaciones de división en Java
 */
class Division {

    /**
     * Método para dividir dos números
     *
     * @param dividendo Número a dividir (numerador)
     * @param divisor Número por el cual se divide (denominador)
     * @return El resultado de la división
     * @throws ArithmeticException Si el divisor es cero
     */
    public static double dividir(double dividendo, double divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Error: No se puede dividir entre cero");
        }
        return dividendo / divisor;
    }

    /**
     * Verificar si un número es entero
     */
    public static boolean esEntero(double numero) {
        return numero == Math.floor(numero) && !Double.isInfinite(numero);
    }

    /**
     * Calcular el máximo común divisor (MCD) para simplificar fracciones
     */
    public static int mcd(int a, int b) {
        return b == 0 ? a : mcd(b, a % b);
    }

    /**
     * Método principal
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("CALCULADORA DE DIVISIÓN");
        System.out.println("----------------------");

        try {
            // Solicitar entrada al usuario
            System.out.print("Introduce el dividendo (número a dividir): ");
            double dividendo = scanner.nextDouble();

            System.out.print("Introduce el divisor (número por el cual se divide): ");
            double divisor = scanner.nextDouble();

            try {
                // Realizar la división
                double resultado = dividir(dividendo, divisor);

                // Mostrar el resultado
                System.out.println("\nEl resultado de " + dividendo + " ÷ " + divisor + " = " + resultado);

                // Si el resultado es un número entero, mostrarlo como entero
                if (esEntero(resultado)) {
                    System.out.println("El resultado en formato entero: " + (int)resultado);
                }

                // Mostrar como fracción si los números son enteros
                if (esEntero(dividendo) && esEntero(divisor)) {
                    int numEntero = (int)dividendo;
                    int denEntero = (int)divisor;
                    int divisorComun = mcd(Math.abs(numEntero), Math.abs(denEntero));

                    int numSimplificado = numEntero / divisorComun;
                    int denSimplificado = denEntero / divisorComun;

                    // Manejar números negativos - asegurar que el signo esté en el numerador
                    if (denSimplificado < 0) {
                        numSimplificado = -numSimplificado;
                        denSimplificado = -denSimplificado;
                    }

                    if (denSimplificado == 1) {
                        System.out.println("Como fracción simplificada: " + numSimplificado);
                    } else {
                        System.out.println("Como fracción simplificada: " + numSimplificado + "/" + denSimplificado);
                    }
                }
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error: Entrada no válida. Asegúrate de introducir números válidos.");
        } finally {
            scanner.close();
        }
    }
}