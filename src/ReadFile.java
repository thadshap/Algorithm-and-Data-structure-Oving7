import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ReadFile {
    URL oracle;

    public ReadFile(String oracle) throws IOException {
        this.oracle = new URL(oracle);
    }

    public URL getOracle() {
        return oracle;
    }
    public BufferedReader reader () throws IOException {
        URLConnection yc = getOracle().openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        return in;
    }
}
