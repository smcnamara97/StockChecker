package edu.temple.stockchecker;

//Made by Sean McNamara
//Stuff being imported
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


//So I think the error in my lab is in here somewhere when I'm trying to use JSON I'm not 100% sure
//but it seems like it would be however I've been looking for awhile and couldn't figure it out


//Class QuoteService
public class QuoteService extends IntentService {

    //Some string variables
    String stock_name;
    String stock_price;

    public QuoteService() {
        super("QuoteService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        getQuote(intent.getStringExtra("stock_symbol"));
    }

    public void getQuote(final String symbol) {
        //Make a URL called url
        URL url;
        //new Stocks object called stocks
        Stocks stocks = new Stocks();

        try {
            //Get stock information with help of json
            //Want the url so we have it and the symbol is whatever stock the user wants to see
            url = new URL ("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json/?symbol=" + symbol);
            //We open up a connection to the url so we can pull resources from it
            URLConnection urlc = url.openConnection();
            //Set up a buffer reader to read the information
            BufferedReader buffer_reader = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            //Simple string that will temporarily hold some info
            String line;
            //While the line we are reading does not equal NULL
            while((line = buffer_reader.readLine()) != null) {
                //Make a json object that will take in our line
                JSONObject json_obj = new JSONObject(line);
                for (int i = 0; i <json_obj.length(); i++) {
                    //Look for name
                    stock_name = json_obj.getString("Name");
                    //and then the lastest price
                    stock_price = json_obj.getString("LastPrice");
                }
            }
            //add the new stock name we got to the list of stockNames
            stocks.stockNames.add(stock_name);
            //add the stock price to the list of stockPrices
            stocks.stockPrices.add(stock_price);
            Log.d ("StockName", stocks.stockNames.get(0));
            Log.d ("StockPrice", stocks.stockPrices.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
