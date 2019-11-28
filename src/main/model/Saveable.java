package model;

import java.io.IOException;

public interface Saveable {
    void saveData(String filename) throws IOException;
}
