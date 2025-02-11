package assignment4.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;

public class MyListDemo extends URLLoader {
    protected List<String> list = new ArrayList<>();

    @Override
    protected void processLine(String tokens) {
        list.add(tokens);
    }

    public static void main(String[] args) {
        MyListDemo myListDemo = new MyListDemo();
        myListDemo.loadData();
        System.out.println(myListDemo.list.size());
    }
}
