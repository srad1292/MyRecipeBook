package radfordsm.myrecipebook;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PantryActivity extends AppCompatActivity {


    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private PantryController pc;
    private Cursor c;
    private EditText result;
    private String chosen;
    private String given_name;
    private HashMap<String, String> category_map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.pantry_toolbar);
        myToolbar.setTitle("Pantry");
        setSupportActionBar(myToolbar);
        pc = new PantryController(this);
        initCatMap();

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.pantry_accordion);

        // preparing list data
        prepareListData();
        prepareListChildren();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pantry_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_pantry_add:
                LayoutInflater li = LayoutInflater.from(this);
                View prompt = li.inflate(R.layout.prompt_new_pantry_item,null);
                AlertDialog.Builder aDB = new AlertDialog.Builder(this);

                aDB.setView(prompt);
                final Spinner dropdown = (Spinner) prompt.findViewById(R.id.pantry_prompt_spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listDataHeader);
                dropdown.setAdapter(adapter);
                dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String item = parent.getItemAtPosition(position).toString();
                        Log.i("item", item);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }

                });
                final EditText item_name = (EditText) prompt.findViewById(R.id.pantry_prompt_text);

                aDB.setCancelable(false).setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        given_name = item_name.getText().toString();
                                        chosen = dropdown.getSelectedItem().toString();
                                        addNewItem(given_name,chosen);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = aDB.create();

                // show it
                alertDialog.show();

                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    private void initCatMap(){
        category_map = new HashMap<>();
        pc.open();
        String[] names = pc.getColumnNames();
        int l = names.length;
        Log.i("Number of columns: ", l+" ");
        String t;
        for(int iter = 0; iter < l; iter++){
            t = names[iter];
            if(!category_map.containsKey(t)){
                category_map.put(t,"no");
            }
        }
        pc.close();
    }

    public void addNewItem(String name, String cat){
        pc.open();

        category_map.put(cat,"yes");
        String[] column_vals = new String[pc.getNumberOfColumns()];

        column_vals[0] = name;
        int iter = 0;

        for(Map.Entry<String, String> entry: category_map.entrySet()) {
            if(iter > 0){
                column_vals[iter] = entry.getValue();
            }
            iter++;
        }


        pc.insert(column_vals);
        pc.close();
        prepareListChildren();
    }

    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String,List<String>>();

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
    }

    private void prepareListChildren() {


        pc.open();
        c = pc.fetch();
        // Adding child data
        List<String> meats = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!meats.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("meats")).equals("yes")) {
                    meats.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }
        meats.add("Filet");

        List<String> poultry = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!poultry.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("poultry")).equals("yes")) {
                    poultry.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> fish = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!fish.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("fish")).equals("yes")) {
                    fish.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> fruits = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!fruits.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("fruits")).equals("yes")) {
                    fruits.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> vegetables = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!vegetables.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("vegetables")).equals("yes")) {
                    vegetables.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> dairy = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!dairy.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("dairy")).equals("yes")) {
                    dairy.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> breads = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!breads.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("breads")).equals("yes")) {
                    breads.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> jams = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!jams.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("jams")).equals("yes")) {
                    jams.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> sauces = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!sauces.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("sauces")).equals("yes")) {
                    sauces.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> spices = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!spices.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("spices")).equals("yes")) {
                    spices.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> mixes = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!mixes.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("mixes")).equals("yes")) {
                    mixes.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> dressings = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!dressings.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("dressings")).equals("yes")) {
                    dressings.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> oils = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!oils.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("oils")).equals("yes")) {
                    oils.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> basic_ingredients = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!basic_ingredients.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("basic_ingredients")).equals("yes")) {
                    basic_ingredients.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> canned_foods = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!canned_foods.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("canned_foods")).equals("yes")) {
                    canned_foods.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> sweets = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!sweets.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("sweets")).equals("yes")) {
                    sweets.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> chips = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!chips.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("chips")).equals("yes")) {
                    chips.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }

        List<String> crackers = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!crackers.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("crackers")).equals("yes")) {
                    crackers.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }


        List<String> cereals = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!cereals.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("cereals")).equals("yes")) {
                    cereals.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }


        List<String> snacks = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!snacks.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("snacks")).equals("yes")) {
                    snacks.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }


        List<String> drinks = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!drinks.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("drinks")).equals("yes")) {
                    drinks.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }


        List<String> alcohol = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!alcohol.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("alcohol")).equals("yes")) {
                    alcohol.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }


        List<String> other = new ArrayList<String>();
        if (c != null && c.moveToFirst()) {
            do {
                if (!other.contains(c.getString(c.getColumnIndex("_id"))) && c.getString(c.getColumnIndex("other")).equals("yes")) {
                    other.add(c.getString(c.getColumnIndex("_id")));
                }
            } while (c.moveToNext());
        }


        pc.close();



        listDataChild.put(listDataHeader.get(0), meats); 
        listDataChild.put(listDataHeader.get(1), poultry);
        listDataChild.put(listDataHeader.get(2), fish);
        listDataChild.put(listDataHeader.get(3), fruits); 
        listDataChild.put(listDataHeader.get(4), vegetables);
        listDataChild.put(listDataHeader.get(5), dairy);
        listDataChild.put(listDataHeader.get(6), breads); 
        listDataChild.put(listDataHeader.get(7), jams);
        listDataChild.put(listDataHeader.get(8), sauces);
        listDataChild.put(listDataHeader.get(9), spices);
        listDataChild.put(listDataHeader.get(10), mixes);
        listDataChild.put(listDataHeader.get(11), dressings);
        listDataChild.put(listDataHeader.get(12), oils);
        listDataChild.put(listDataHeader.get(13), basic_ingredients);
        listDataChild.put(listDataHeader.get(14), canned_foods);
        listDataChild.put(listDataHeader.get(15), sweets);
        listDataChild.put(listDataHeader.get(16), chips);
        listDataChild.put(listDataHeader.get(17), crackers);
        listDataChild.put(listDataHeader.get(18), cereals);
        listDataChild.put(listDataHeader.get(19), snacks);
        listDataChild.put(listDataHeader.get(20), drinks);
        listDataChild.put(listDataHeader.get(21), alcohol);
        listDataChild.put(listDataHeader.get(22), other);
    }
}
