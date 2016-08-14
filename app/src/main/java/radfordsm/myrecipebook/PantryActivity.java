package radfordsm.myrecipebook;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PantryActivity extends Activity {


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.pantry_accordion);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }


    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Meat");
        listDataHeader.add("Fruits");
        listDataHeader.add("Vegetables");
        listDataHeader.add("Dairy");
        listDataHeader.add("Bread");
        listDataHeader.add("Sauces");
        listDataHeader.add("Spices");
        listDataHeader.add("Mixes");
        listDataHeader.add("Basic Ingredients");
        listDataHeader.add("Canned Food");
        listDataHeader.add("Sweets");
        listDataHeader.add("Chips");
        listDataHeader.add("Crackers");
        listDataHeader.add("Cereal");
        listDataHeader.add("Drinks");
        listDataHeader.add("Alcohol");
        listDataHeader.add("Other");

        // Adding child data
        List<String> meat = new ArrayList<String>();
        List<String> fruits = new ArrayList<String>();
        List<String> vegetables = new ArrayList<String>();
        List<String> dairy = new ArrayList<String>();
        List<String> bread = new ArrayList<String>();
        List<String> sauces = new ArrayList<String>();
        List<String> spices = new ArrayList<String>();
        List<String> mixes = new ArrayList<String>();
        List<String> basicIngrdients = new ArrayList<String>();
        List<String> cannedFood = new ArrayList<String>();
        List<String> sweets = new ArrayList<String>();
        List<String> chips = new ArrayList<String>();
        List<String> crackers = new ArrayList<String>();
        List<String> cereal = new ArrayList<String>();
        List<String> drinks = new ArrayList<String>();
        List<String> alcohol = new ArrayList<String>();
        List<String> other = new ArrayList<String>();



    }
}
