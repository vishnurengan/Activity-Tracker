package ui;

import network.GetLocation;
import network.GetTime;
import org.json.JSONException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, JSONException {
//        GetTime getTime = new GetTime();
//        getTime.getTime();
//
//        GetLocation getLocation = new GetLocation();
//        getLocation.getLocation();

//        ListInterface listInterface = new ListInterface();
//        listInterface.run();


        Visuals visuals = new Visuals();
        visuals.setupMain();


    }
}
