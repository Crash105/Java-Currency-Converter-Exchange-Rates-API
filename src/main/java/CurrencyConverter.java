import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.*;
import java.awt.event.*;
import org.json.JSONObject;
import io.github.cdimascio.dotenv.Dotenv;

public class CurrencyConverter {
    private static Dotenv dotenv;
    private static String apiKey;
    private static String API_BASE_URL;

    static {
        dotenv = Dotenv.load();
        apiKey = dotenv.get("API_KEY");
        API_BASE_URL = "http://api.exchangeratesapi.io/v1/latest?access_key=" + apiKey;
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel label = new JLabel("Enter the amount in EUR to convert: ");
        int labelWidth = 250;
        int labelHeight = 75;
        int frameWidth = frame.getWidth();
        int xPosition = (frameWidth - labelWidth) / 2;
        int yPosition = 50;
        label.setBounds(xPosition, yPosition, labelWidth, labelHeight);// Set position and size
        frame.add(label);

        JTextField t = new JTextField(1);
        int textWidth = 50;
        int textHeight = 75;
        int frametextWidth = frame.getWidth();
        int xtextPosition = (frametextWidth - textWidth) / 2;
        int ytextPosition = 125;
        t.setBounds(xtextPosition, ytextPosition, textWidth, textHeight);
        frame.add(t);

        String[] currencies = {
                "USD", "GBP", "JPY", "CAD", "AUD", "CHF", "CNY", "HKD", "NZD",
                "SEK", "KRW", "SGD", "NOK", "MXN", "INR", "RUB", "ZAR", "TRY", "BRL",
                "TWD", "DKK", "PLN", "THB", "IDR", "HUF", "CZK", "ILS", "CLP", "PHP",
                "AED", "COP", "SAR", "MYR", "RON"
        };
        JComboBox<String> dropdown = new JComboBox<>(currencies); // Pass the list of currencies

        int dropdownWidth = 200;
        int dropdownHeight = 75;
        int dropframeWidth = frame.getWidth();
        int xdropPosition = (dropframeWidth - dropdownWidth) / 2;
        int ydropPosition = 200;
        dropdown.setBounds(xdropPosition, ydropPosition, dropdownWidth, dropdownHeight);// Set position and size
        frame.add(dropdown);

        JButton b = new JButton("Submit");
        int buttonWidth = 100;
        int buttonHeight = 75;
        int framebuttonWidth = frame.getWidth();
        int xbuttonPosition = (framebuttonWidth - buttonWidth) / 2;
        int ybuttonPosition = 275;
        b.setBounds(xbuttonPosition, ybuttonPosition, buttonWidth, buttonHeight);

        JLabel answer = new JLabel("Conversion: ");
        int answerWidth = 500;
        int answerHeight = 75;
        int frameanswerWidth = frame.getWidth();
        int xanswerPosition = (frameanswerWidth - answerWidth) / 2;
        int yanswerPosition = 350;
        answer.setBounds(xanswerPosition, yanswerPosition, answerWidth, answerHeight);// Set position and size

        frame.add(answer);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fromCurrency = "EUR";

                double amount = Double.parseDouble(t.getText());

                String toCurrency = (String) dropdown.getSelectedItem();

                System.out.println("Amount:" + amount + "Dropdown: " + toCurrency);

                try {
                    double currency = convertCurrency(amount, fromCurrency, toCurrency);
                    String formattedText = String.format("Conversion: %.2f %s is equal to %.2f %s",
                            amount, fromCurrency, currency, toCurrency);
                    answer.setText(formattedText);
                } catch (IOException e1) {
                    // // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        frame.add(b);

        frame.setVisible(true);
    }

    public static double convertCurrency(double amount, String fromCurrency, String toCurrency) throws IOException {
        String apiUrl = API_BASE_URL + "&base=" + fromCurrency + "&symbols=" + toCurrency;
        URL url = new URL(apiUrl);
        System.out.println(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String response = br.readLine();
        conn.disconnect();

        JSONObject json = new JSONObject(response);
        JSONObject rates = json.getJSONObject("rates");
        double rate = rates.getDouble(toCurrency);

        return amount * rate;
    }
}
