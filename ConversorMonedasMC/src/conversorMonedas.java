import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class ConversorMonedas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        tasaDeCambioService tasaDeCambioService = new tasaDeCambioService();
        boolean tipoMoneda = true;

        while (tipoMoneda) {
            mostrarMenu();

            try {
                int opcion = scanner.nextInt();

                if (opcion == 7) {
                    tipoMoneda = false;
                    System.out.println("Gracias por usar el conversor.");
                    break;
                }

                if (opcion < 1 || opcion > 7) {
                    System.out.println("Opción es inválida, por favor seleccione una opción del menú.");
                    continue;
                }

                System.out.print("Ingrese la cantidad: ");
                double cantidad = scanner.nextDouble();

                double tasaCambio = tasaDeCambioService.obtenerTasaDeCambio(opcion);
                double resultado = cantidad * tasaCambio;
                System.out.printf(cantidad + " es igual a: " + resultado);//"Resultado: %.2f%n", resultado);
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor ingrese un número válido.");
                scanner.next(); // Limpiar la entrada incorrecta
            } catch (IOException | InterruptedException e) {
                System.out.println("Error al obtener la tasa de cambio: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("---- Conversor de Monedas ----");
        System.out.println("1. Dólar a Peso Argentino");
        System.out.println("2. Peso Argentino a Dólar");
        System.out.println("3. Dólar a Real Brasileño");
        System.out.println("4. Real Brasileño a Dólar");
        System.out.println("5. Dólar a Peso Colombiano");
        System.out.println("6. Peso Colombiano a Dólar");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }
}

