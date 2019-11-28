package network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

// REFERENCES:
// followed example from EDX ://shamelessly quoting from: http://zetcode.com/articles/javareadwebpage/
// followed https://github.students.cs.ubc.ca/CPSC210/JsonParserExample from piazza
// followed https://stackoverflow.com/questions/46477861/how-to-parse-json-on-java


public class GetLocation {

    // EFFECTS: reads web page and creates JSONObject
    public void getLocation() throws IOException, JSONException {

        BufferedReader br = null;

        try {
            String theURL = "http://ip-api.com/json/";
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            JSONObject obj = new JSONObject(sb.toString());
            displayMessage(obj);
        } finally {

            if (br != null) {
                br.close();
            }
        }


    }

    private void displayMessage(JSONObject obj) throws JSONException {
        String city = obj.getString("city");
        String regionName = obj.getString("regionName");
        System.out.println("I see your currently located in " + city + ", " + regionName + ".");
    }
}
