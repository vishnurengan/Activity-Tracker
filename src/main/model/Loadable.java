package model;

import java.io.IOException;

public interface Loadable {
    public void loadData(String filename) throws IOException;
}
