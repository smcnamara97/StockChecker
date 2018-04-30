package edu.temple.stockchecker;

//Made by Sean McNamara
//import stuffffffffffffffffff
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

//This is the adapter that I made for my portfolio basically the regular adapter we've been
//making for the past projects
public class PortfolioAdapter extends BaseAdapter {

    ArrayList<String> name_list;
    final Context c;

    public PortfolioAdapter (Context c, ArrayList<String> strings){
        this.c = c;
        this.name_list = strings;
    }

    //Adds a name to our name_list
    public void add(String name) {
        //add name to name_list
        name_list.add(name);
        //notify observer that data has been changed and refresh
        notifyDataSetChanged();
    }

    //These functions below are the normal four that you usually have to override
    //when you extend the BaseAdapter
    @Override
    public int getCount() {
        //return the size of the list of names
        return name_list.size();
    }

    @Override
    public Object getItem(int position) {
        //get the name in the list of namelist at the give pos
        return name_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //my getView function
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //make new linear l make sure to use context c also
        LinearLayout lin_lay = new LinearLayout(c);
        //then set the orientation to horizontal
        lin_lay.setOrientation(LinearLayout.HORIZONTAL);
        //new textview make sure to use our context c also
        TextView nameView = new TextView(c);
        //use our name_list with a given pos and use setText to set the text for nameView
        nameView.setText(name_list.get(position));
        //simply changing the text size
        nameView.setTextSize(25);
        //we then want to add the nameView to the existing linear l (lin_lay)
        lin_lay.addView(nameView);
        //notify observers that data has changed and refresh
        notifyDataSetChanged();
        //return our view
        return lin_lay;
    }
}