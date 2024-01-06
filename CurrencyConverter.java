import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class CurrencyConverter {

    private static final String API_KEY = "https://www.exchangerate-api.com/";

    public static double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
        String apiUrl = "https://open.er-api.com/v6/latest/" + baseCurrency + "?apikey=" + API_KEY;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        // Parse JSON response to get the exchange rate
        String jsonResponse = response.toString();
        double exchangeRate = Double.parseDouble(jsonResponse.split("\"" + targetCurrency + "\":")[1].split(",")[0]);

        return exchangeRate;
    }

    public static double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }

    public static void main(String[] args) {
        try {
            // Step 1: Allow the user to choose the base and target currencies
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the base currency code (e.g., USD): ");
            String baseCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Enter the target currency code (e.g., EUR): ");
            String targetCurrency = scanner.nextLine().toUpperCase();

            // Step 2: Fetch real-time exchange rates
            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);

            // Step 3: Take input from the user for the amount to convert
            System.out.print("Enter the amount to convert: ");
            double amount = scanner.nextDouble();

            // Step 4: Convert the amount using the fetched exchange rate
            double convertedAmount = convertCurrency(amount, exchangeRate);

            // Step 5: Display the result to the user
            System.out.printf("%f %s is equal to %f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
