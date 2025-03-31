package kwic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class Filter extends Thread {
    protected Pipe inPipe;
    protected Pipe outPipe;

    public abstract void run();
}

class InputFilter extends Filter {
    private FileInputStream fileInStream;
    private StringBuffer inString;

    public InputFilter(FileInputStream input, Pipe inputToShifterPipe) {
        this.outPipe = inputToShifterPipe;
        fileInStream = input;
        inString = new StringBuffer();
    }

    @Override
    public void run() {
        int charInput;
        try {
            while ((charInput = fileInStream.read()) != -1) {
                inString.append((char) charInput);
            }
            outPipe.writePipe(inString.toString());

            System.out.println("Input Filter:");
            System.out.println(outPipe.readPipe());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class CircularShifterFilter extends Filter {
    private Set<String> shiftedLines;
    private StringBuffer stringOutBuffer;
    private String[] lines;

    public CircularShifterFilter(Pipe inputToShifterPipe, Pipe shifterToAlphaPipe) {
        this.inPipe = inputToShifterPipe;
        this.outPipe = shifterToAlphaPipe;
    }

    @Override
    public void run() {
        try {
            // Split lines
            lines = inPipe.readPipe().split("\\R");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExecutorService executer = Executors.newFixedThreadPool(lines.length); // Thread pool for concurrent processing

        shiftedLines = new CopyOnWriteArraySet<>(); // Allows threads to mutate shared list
        for (String line : lines) {

            executer.submit(() -> {
                Deque<String> shiftLine = new ArrayDeque<>();
                shiftLine.addAll(Arrays.asList(line.split(" ")));

                shiftedLines.add(shiftLine.stream().collect(Collectors.joining(" ")));
                for (int i = 0; i < shiftLine.size() - 1; i++) {
                    shiftLine.addLast(shiftLine.poll());
                    shiftedLines.add(shiftLine.stream().collect(Collectors.joining(" ")));
                }
            });

        }

        stringOutBuffer.append(shiftedLines.stream().collect(Collectors.joining("\n")));
        try {
            outPipe.writePipe(stringOutBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class AlphabetizerFilter extends Filter {

    public AlphabetizerFilter(Pipe shifterToAlphaPipe, Pipe alphaToOutPipe) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void run() {

    }

}

class OutputFilter extends Filter {

    public OutputFilter(Pipe alphaToOutPipe) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void run() {

    }

}
