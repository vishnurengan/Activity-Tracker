package model;

import java.io.IOException;

public interface Loadable {
    void loadData(String filename) throws IOException;
}
