package kwic;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class KWIC_Tester {
    public static void main(String[] args) throws IOException {
        Pipe inputToShifterPipe = new Pipe();
        Pipe shifterToAlphaPipe = new Pipe();
        Pipe alphaToOutPipe = new Pipe();

        FileInputStream input = null;
        try {
            input = new FileInputStream(new File("kwic/documents.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        Filter inputFilter = new InputFilter(input, inputToShifterPipe);
        Filter shifterFilter = new CircularShifterFilter(inputToShifterPipe, shifterToAlphaPipe);
        Filter alphaFilter = new AlphabetizerFilter(shifterToAlphaPipe, alphaToOutPipe);
        Filter outputFilter = new OutputFilter(alphaToOutPipe);

        try {
            inputFilter.start();
            shifterFilter.start();
            alphaFilter.start();
            outputFilter.start();

            outputFilter.join();
            inputFilter.join();
            shifterFilter.join();
            alphaFilter.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
