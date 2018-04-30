package edu.temple.stockchecker;

//Made by Sean McNamara
//My import stuff
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

//This is the portfolio fragment here we are supposed to store the different
//stocks that the user wants to view and allows search
public class PortfolioFragment extends Fragment {
    //Make an onStockClickListner called mCallback
    onStockClickListener mCallback;
    //Make new Stocks obj called stocks
    Stocks stocks = new Stocks();
    //List view called list_view
    ListView list_view;
    //String to temp store our stock name
    String stock_name;



    public PortfolioFragment() {
        //Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Want to inflate the layout of this fragment
        View l = inflater.inflate(R.layout.fragment_portfolio, container, false);
        //Use findViewById on list_view and store it into our ListView obj
        list_view = (ListView) l.findViewById(R.id.list_view);
        PortfolioAdapter myAdapter = new PortfolioAdapter(getContext(), stocks.l);
        //Set the adapter of myAdapter to list_view
        list_view.setAdapter(myAdapter);
        if (stock_name != "supersecretcodethatnoonewillwrite") {
            myAdapter.add(stock_name);
        }
        //This notifys any observers that data has been changed and it will make sure
        //that anything displaying the data is refreshed
        myAdapter.notifyDataSetChanged();
        //Set an on click listener for our list view
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //return the activity and set it equal to our onStockClickListener obj
                mCallback = (onStockClickListener) getActivity();
                //run the onStockSelected function on mCallback with the pos on the item they clicked
                mCallback.onStockSelected(position);
            }
        });
        //return the l
        return l;
    }

    //This is my onResume() function here we set the stock name to the "code"
    @Override
    public void onResume() {
        super.onResume();
        stock_name = "supersecretcodethatnoonewillwrite";
    }


    public interface onStockClickListener {
        public void onStockSelected(int position);
    }

    //function to set name to the stock_name when user calls sendString with param name
    public void sendString (String name) {
        stock_name = name;
    }
}