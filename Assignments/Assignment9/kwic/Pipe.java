package kwic;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Pipe {
    private PipedReader reader;
    private PipedWriter writer;

    Pipe() throws IOException {
        writer = new PipedWriter();
        reader = new PipedReader(writer);
    }

    public synchronized void writePipe(String stringIn) throws IOException {
        writer.write(stringIn);
        writer.flush();
        writer.close();
    }

    public synchronized String readPipe() throws IOException {
        StringBuffer readPipe = new StringBuffer();
        int charRead;
        while ((charRead = reader.read()) != -1) {
            readPipe.append((char) charRead);
        }
        return readPipe.toString();
    }
}
