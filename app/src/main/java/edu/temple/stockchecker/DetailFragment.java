package edu.temple.stockchecker;

//Made by Sean McNamara
//My Stuff to import
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//This will be our detail fragment class this will basically be showing the stocks and such
public class DetailFragment extends Fragment {

    //View obj called l (layout)
    View l;
    //make Stocks obj called stocks
    Stocks stocks = new Stocks();
    //position int
    int pos;

    public DetailFragment() {
        // Required empty public constructor
    }


    //On create view method
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        l = inflater.inflate(R.layout.fragment_detail, container, false);
        //make text view called name_tv where I use my View l and do findViewById and it using company_name
        final TextView name_tv = (TextView) l.findViewById(R.id.name);
        //now use price_tv and do same thing above but with price instead
        final TextView price_tv = (TextView) l.findViewById(R.id.price);
        //Make a handler
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //set the text of my textview obj to the stock name using pos to help
                name_tv.setText(stocks.stockNames.get(pos));
                //set text of textview price_tv to correct price using pos to help find position
                price_tv.setText(stocks.stockPrices.get(pos));
            }
        }, 1500);
        //return the view to be displayed
        return l;
    }

    public int sendPosition(int pos) {
        return pos;
    }
}