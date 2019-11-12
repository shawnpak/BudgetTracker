package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.*;

public class ReadWebPageEX {
    // shamelessly quoting from: https://edge.edx.org/courses/course-v1:UBC+CPSC210+all/courseware/f636f4e1dd5348ed8f6dc7c3defed983/0539f11dfbe14bf0a6867f7c6441624b/1?activate_block_id=block-v1%3AUBC%2BCPSC210%2Ball%2Btype%40vertical%2Bblock%4038dc1fe6e45a420ea1936b0165c6e58d
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

