package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadWebPageEX {
    // shamelessly quoting from: https://edge.edx.org/courses/course-v1:UBC+CPSC210+all/courseware/f636f4e1dd5348ed8f6dc7c3defed983/0539f11dfbe14bf0a6867f7c6441624b/1?activate_block_id=block-v1%3AUBC%2BCPSC210%2Ball%2Btype%40vertical%2Bblock%4038dc1fe6e45a420ea1936b0165c6e58d
    public static void main(String[] args) throws MalformedURLException, IOException {

        BufferedReader br = null;
        try {
            String theURL = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=37d752f46965718c78878fb09b48712a";
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            System.out.println(sb);
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}
