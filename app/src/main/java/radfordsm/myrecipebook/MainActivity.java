package radfordsm.myrecipebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    /** Called when the Recipes button is clicked */
    public void startRecipes(View view){
        Intent intent = new Intent(this, RecipeActivity.class);
        startActivity(intent);
    }

    /** Called when the Shopping button is clicked */
    public void startShoppingList(View view){
        Intent intent = new Intent(this, ShoppingListActivity.class);
        startActivity(intent);
    }

    /** Called when the Pantry button is clicked */
    public void startPantry(View view){
        Intent intent = new Intent(this, PantryActivity.class);
        startActivity(intent);
    }

    /** Called when the Meal Builder button is clicked */
    public void startMealBuilder(View view){
        Intent intent = new Intent(this, MealBuilderActivity.class);
        startActivity(intent);
    }
}
