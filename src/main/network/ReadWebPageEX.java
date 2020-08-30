package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.*;

public class ReadWebPageEX {
    public static void main(String[] args) throws MalformedURLException, IOException {
        Scanner scan = null;
        try {
            String theURL = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=37d752f46965718c78878fb09b48712a";
            URL url = new URL(theURL);
            scan = new Scanner(url.openStream());
            String sb = new String();
            while ((scan.hasNext())) {
                sb += scan.nextLine();
            }
            JSONObject obj = new JSONObject(sb);

            JSONObject weather = obj.getJSONArray("weather").getJSONObject(0);
            System.out.println("Vancouver");
            System.out.println("Main: " + weather.get("main") + "\nDescription: " + weather.get("description"));
        } finally {
            scan.close();
        }
    }
}

