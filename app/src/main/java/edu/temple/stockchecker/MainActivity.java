package edu.temple.stockchecker;

//Made by Sean McNamara
//My import stufffffff
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//I want to leave a disclaimer and say I didn't complete this lab, there was an error
//somewhere in my code (I think it was in my QuoteService, I talk about it a little more on
//that file, but for the life of me I couldn't figure out what I was doing wrong and I kinda
//have finals coming up so I decided to just turn this in even though its incomplete, sorry in advance

//Main Activity
public class MainActivity extends Activity implements PortfolioFragment.onStockClickListener {

    //Booleans, Stocks, Strings, and Fragments below
    boolean two_panes;
    Stocks stocks = new Stocks();
    String stock_name;
    PortfolioFragment portfolio_fragment = new PortfolioFragment();
    DetailFragment detail_fragment = new DetailFragment();

    //My onCreate function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //First I check if the there is a second container
        //this will let us know if we are in landscape, large, or normal
        two_panes = (findViewById(R.id.container_two) != null);
        //Make the fm
        FragmentManager fm = getFragmentManager();
        //Also make the ft and beging the transaction
        FragmentTransaction ft = fm.beginTransaction();
        //Add my portfolio fragment to the container and commit afterwards
        ft.add(R.id.container_one, new PortfolioFragment());
        ft.commit();
        //Then we check if there are two panes (land or large)
        if (two_panes) {
            ft = fm.beginTransaction();
            //If there is I add the detail fragment to the second container
            ft.add(R.id.container_two, new DetailFragment());
            ft.commit();
        }

    }

    //This creates our option menu (menu at the top)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mif = getMenuInflater();
        //Make sure its to our button_menu
        mif.inflate(R.menu.button_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //This is when our button in the action bar is hit
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //When the add_button is hit...
            case R.id.add_button:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //Want to show the box that will basically as for what stock they want
                //and then we'll will save it eventually (down below).
                View view = getLayoutInflater().inflate(R.layout.dialog_addstock, null);
                //This will be temporary storage for when they put in what stock they want
                final EditText temp_stock_name = (EditText) view.findViewById(R.id.stockName_editText);
                //This will be our save button that they will hit after typing in the stock
                Button s_button = (Button) view.findViewById(R.id.save_button);
                //Our onClickListener for when they hit the save button
                s_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //We will put the stock name they want into a string called s_name
                        String s_name = temp_stock_name.getText().toString();
                        //Set our stock object with stock name to correct name
                        stock_name = s_name;
                        //We then want to send the stock name to the portfolio fragment
                        portfolio_fragment.sendString(stock_name);
                        //We will also add the stock name to our stock list
                        stocks.l.add(s_name);
                        //Then we do our load fragment of our portfolio_fragment to the container
                        loadFragment(R.id.container_one, portfolio_fragment);
                    }
                });
                builder.setView(view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }
        return true;
    }

    @Override
    public void onStockSelected(int position) {
        //First we want to check if there are two panes (basically horizontal or vertical)
        if (!two_panes) {
            //If not we just want to load our detail_fragment into our loadFragment
            loadFragment(R.id.container_one, detail_fragment);
        }
        //Want to set our stocks posistion to the pos we are at
        stocks.pos = position;
        Intent stockQuoteIntent = new Intent(MainActivity.this, QuoteService.class);
        stockQuoteIntent.putExtra("stock_symbol", stocks.l.get(position));
        startService(stockQuoteIntent);
        //Want to send the pos we are at to the detail fragment
        detail_fragment.sendPosition(position);
    }
    //Loads the fragment (implied by name)
    private void loadFragment (int containerId, Fragment fragment) {
        //Make our fm
        FragmentManager fm = getFragmentManager();
        //The our ft and begin the transaction
        FragmentTransaction ft = fm.beginTransaction();
        //We to replace containerId with fragment
        ft.replace(containerId, fragment);
        //Place it on the stack and afterwards commit
        ft.addToBackStack(null);
        ft.commit();
    }
}
