package assignment4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import assignment4.app.MyListDemo;

public class MyListDemo2 extends MyListDemo {
    protected List<List<String>> list = new ArrayList<>();

    @Override
    protected void processLine(String[] tokens) {
        if (tokens[7].equalsIgnoreCase("British Columbia")) {
            list.add(Arrays.asList(tokens));
        }
    }

    public static void main(String[] args) {
        MyListDemo2 myListDemo = new MyListDemo2();
        myListDemo.loadData();
        System.out.println(myListDemo.list.size());
    }
}
