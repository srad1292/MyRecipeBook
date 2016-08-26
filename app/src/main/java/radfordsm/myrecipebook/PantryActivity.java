package radfordsm.myrecipebook;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PantryActivity extends AppCompatActivity {


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    PantryController pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);

        pc = new PantryController(this);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.pantry_accordion);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }


    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Meats");
        listDataHeader.add("Poultry");
        listDataHeader.add("Fish");
        listDataHeader.add("Fruits");
        listDataHeader.add("Vegetables");
        listDataHeader.add("Dairy");
        listDataHeader.add("Breads");
        listDataHeader.add("Jams");
        listDataHeader.add("Sauces");
        listDataHeader.add("Spices");
        listDataHeader.add("Mixes");
        listDataHeader.add("Dressings");
        listDataHeader.add("Oils");
        listDataHeader.add("Basic Ingredients");
        listDataHeader.add("Canned Foods");
        listDataHeader.add("Sweets");
        listDataHeader.add("Chips");
        listDataHeader.add("Crackers");
        listDataHeader.add("Cereals");
        listDataHeader.add("Snacks");
        listDataHeader.add("Drinks");
        listDataHeader.add("Alcohol");
        listDataHeader.add("Other");

        // Adding child data
        List<String> meats = new ArrayList<String>();
        List<String> poultry = new ArrayList<String>();
        List<String> fish = new ArrayList<String>();
        List<String> fruits = new ArrayList<String>();
        List<String> vegetables = new ArrayList<String>();
        List<String> dairy = new ArrayList<String>();
        List<String> breads = new ArrayList<String>();
        List<String> jams = new ArrayList<String>();
        List<String> sauces = new ArrayList<String>();
        List<String> spices = new ArrayList<String>();
        List<String> mixes = new ArrayList<String>();
        List<String> dressings = new ArrayList<String>();
        List<String> oils = new ArrayList<String>();
        List<String> basic_ingrdients = new ArrayList<String>();
        List<String> canned_foods = new ArrayList<String>();
        List<String> sweets = new ArrayList<String>();
        List<String> chips = new ArrayList<String>();
        List<String> crackers = new ArrayList<String>();
        List<String> cereals = new ArrayList<String>();
        List<String> snacks = new ArrayList<String>();
        List<String> drinks = new ArrayList<String>();
        List<String> alcohol = new ArrayList<String>();
        List<String> other = new ArrayList<String>();

    }
}
