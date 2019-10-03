package model;

import java.io.IOException;

public interface Saveable {
    public void saveData(String filename) throws IOException;
}
