package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherApp {

        private static final String API_KEY = "d4d02e985ba71dbd42436b263b2e281e"; // Replace with your API key
        private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the Weather App!");

            while (true) {
                System.out.print("\nEnter city name (or type 'exit' to quit): ");
                String city = scanner.nextLine();

                if (city.equalsIgnoreCase("exit")) {
                    System.out.println("Goodbye!");
                    break;
                }

                getWeather(city);
            }

            scanner.close();
        }

        private static void getWeather(String city) {
            try {
                String urlString = API_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    parseAndDisplayWeather(response.toString());
                } else {
                    System.out.println("Error: Unable to fetch weather data. Please check the city name.");
                }
            } catch (Exception e) {
                System.out.println("Error: Unable to fetch weather data. Please check your internet connection.");
            }
        }

        private static void parseAndDisplayWeather(String jsonResponse) {
            //System.out.println(jsonResponse.toString());
            JSONObject jsonObj = new JSONObject(jsonResponse);
            String cityName = jsonObj.getString("name");
            JSONObject main = jsonObj.getJSONObject("main");
            double temp = main.getDouble("temp");
            double feelsLike = main.getDouble("feels_like");
            int humidity = main.getInt("humidity");

            JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);
            String description = weather.getString("description");
            System.out.print("===============");
            System.out.println("\nWeather in " + cityName + ":");
            System.out.println("===============\n");
            System.out.println("Temperature: " + temp + "°C");
            System.out.println("Feels Like: " + feelsLike + "°C");
            System.out.println("Description: " + description);
            System.out.println("Humidity: " + humidity + "%");
        }


}
