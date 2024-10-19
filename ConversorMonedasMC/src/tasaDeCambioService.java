import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class tasaDeCambioService {

    private static final String API_KEY = "64a760cc6939c54cd480ac86";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public double obtenerTasaDeCambio(int opcion) throws IOException, InterruptedException {
        //Solicitud a la API
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Respuesta JSON
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject rates = jsonResponse.getAsJsonObject("conversion_rates");

        // Seleccionar la tasa de cambio según la opción elegida
        switch (opcion) {
            case 1: // Dólar a Peso Argentino
                return rates.get("ARS").getAsDouble();
            case 2: // Peso Argentino a Dólar
                return 1 / rates.get("ARS").getAsDouble();
            case 3: // Dólar a Real Brasileño
                return rates.get("BRL").getAsDouble();
            case 4: // Real Brasileño a Dólar
                return 1 / rates.get("BRL").getAsDouble();
            case 5: // Dólar a Peso Colombiano
                return rates.get("COP").getAsDouble();
            case 6: // Peso Colombiano a Dólar
                return 1 / rates.get("COP").getAsDouble();
            default:
                throw new IllegalArgumentException("Opción inválida");
        }
    }
}
