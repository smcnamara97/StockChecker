package edu.temple.stockchecker;

import java.util.ArrayList;

//This is my class I made for the stocks
public class Stocks {
    //Our stocks our going to have a list of names to keep track of
    public ArrayList<String> stockNames = new ArrayList<>();
    //We will also need a list of the prices
    public ArrayList<String> stockPrices = new ArrayList<>();
    //Secondary list that we will use
    public ArrayList<String> l = new ArrayList<>();
    int pos;

    public Stocks() {
    }

    //Made a function to add stock names to our secondary list
    public void add (String stockName) {
        l.add(stockName);
    }
}
