package kwic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
            this.outPipe.writePipe(inString.toString());

            System.out.println("=====================================");
            System.out.println("Input Filter:");
            System.out.println("=====================================");
            System.out.println(inString.toString());

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
            lines = this.inPipe.readPipe().split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExecutorService executer = Executors.newFixedThreadPool(lines.length); // Thread pool for concurrent processing
        shiftedLines = new CopyOnWriteArraySet<>(); // Allows threads to mutate shared list

        List<Future<?>> futures = new ArrayList<>(); // For retreiving finals from the executers.
        for (String line : lines) {

            Future<?> future = executer.submit(() -> {
                Deque<String> shiftLine = new ArrayDeque<>();
                shiftLine.addAll(Arrays.asList(line.split(" ")));

                shiftedLines.add(shiftLine.stream().collect(Collectors.joining(" ")));
                for (int i = 0; i < shiftLine.size() - 1; i++) {
                    shiftLine.addLast(shiftLine.poll());
                    shiftedLines.add(shiftLine.stream().collect(Collectors.joining(" ")));
                }

            });

            futures.add(future);

        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executer.shutdown();

        stringOutBuffer = new StringBuffer();
        stringOutBuffer.append(shiftedLines.stream().collect(Collectors.joining("\n")));
        // System.out.println(stringOutBuffer);

        System.out.println("=====================================");
        System.out.println("CIRCULAR SHIFTER FILTER");
        System.out.println("=====================================");
        try {
            this.outPipe.writePipe(stringOutBuffer.toString());
            System.out.println(stringOutBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

class AlphabetizerFilter extends Filter {

    public AlphabetizerFilter(Pipe shifterToAlphaPipe, Pipe alphaToOutPipe) {
        this.inPipe = shifterToAlphaPipe;
        this.outPipe = alphaToOutPipe;
    }

    @Override
    public void run() {
        String lineAll = null;
        try {
            lineAll = this.inPipe.readPipe();

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> lines = new ArrayList<>();
        lines.addAll(Arrays.asList(lineAll.split("\n")));
        Collections.sort(lines, String.CASE_INSENSITIVE_ORDER);
        lineAll = lines.stream().collect(Collectors.joining("\n"));

        System.out.println("=====================================");
        System.out.println("ALPHABETIZER FILTER");
        System.out.println("=====================================");
        try {
            outPipe.writePipe(lineAll);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(lineAll);

    }

}

class OutputFilter extends Filter {

    public OutputFilter(Pipe alphaToOutPipe) {
        this.inPipe = alphaToOutPipe;
    }

    @Override
    public void run() {
        String outString = null;
        String fileName = "kwic/KWIC_documents.txt";
        try {
            outString = inPipe.readPipe();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("=====================================");
        System.out.println("ALPHABETIZER FILTER");
        System.out.println("=====================================");
        System.out.printf("File: \"%s\"\n", fileName);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            for (char c : outString.toCharArray()) {
                fileOutputStream.write((int) c);
            }
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
