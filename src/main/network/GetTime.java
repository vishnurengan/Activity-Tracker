package network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

// followed example from EDX ://shamelessly quoting from: http://zetcode.com/articles/javareadwebpage/
// followed https://github.students.cs.ubc.ca/CPSC210/JsonParserExample from piazza
// followed https://stackoverflow.com/questions/46477861/how-to-parse-json-on-java

public class GetTime {

    public void getTime() throws IOException, JSONException {

        BufferedReader br = null;

        try {
            //String theURL = "https://www.students.cs.ubc.ca/~cs-210/2018w1/welcomemsg.html"; //this can point to any URL
            //String theURL = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,CA&mode=json&APPID=6153dfc3a4eea8c23ee91b16a05b797d";
            String theURL = "https://api.timezonedb.com/v2.1/get-time-zone?key=BQP1Z6890EBV&format=json&by=zone&zone=America/Vancouver";
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
        String time = obj.getString("formatted");
        String shours = time.substring(11,13);
        String sminutes = time.substring(14,16);
        int ihours = Integer.parseInt(shours);
        int iminutes = Integer.parseInt(sminutes);


        if (ihours >= 6 && ihours < 12) {
            System.out.println("Good Morning Vishnu. It's " + shours + ":" + sminutes);
        } else if (ihours >= 12 && ihours < 17) {
            System.out.println("Good Afternoon Vishnu. It's " + shours + ":" + sminutes);
        } else if (ihours >= 17 && ihours < 22) {
            System.out.println("Good Evening Vishnu. It's " + shours + ":" + sminutes);
        } else {
            System.out.println("What are you doing up so late? It's " + shours + ":" + sminutes);
        }

    }
}