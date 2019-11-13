package network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class GetLocation {


    public void getLocation() throws IOException, JSONException {

        BufferedReader br = null;

        try {
            //String theURL = "https://www.students.cs.ubc.ca/~cs-210/2018w1/welcomemsg.html"; //this can point to any URL
            //String theURL = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,CA&mode=json&APPID=6153dfc3a4eea8c23ee91b16a05b797d";
            //String theURL = "https://api.timezonedb.com/v2.1/get-time-zone?key=BQP1Z6890EBV&format=json&by=zone&zone=America/Vancouver";
            String theURL = "http://ip-api.com/json/";
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            //System.out.println(sb);
            JSONObject obj = new JSONObject(sb.toString());
            displayMessage(obj);
        } finally {

            if (br != null) {
                br.close();
            }
        }


    }

    public void displayMessage(JSONObject obj) throws JSONException {
        String city = obj.getString("city");
        String regionName = obj.getString("regionName");
        System.out.println("I see your currently located in " + city + ", " + regionName + ".");
    }
}
