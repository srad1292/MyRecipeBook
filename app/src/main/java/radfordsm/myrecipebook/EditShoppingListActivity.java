package radfordsm.myrecipebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * Created by San on 8/18/2016.
 */
public class EditShoppingListActivity extends AppCompatActivity {

    private EditText editTextField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shopping_list);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.edit_shopping_list_toolbar);
        myToolbar.setTitle(R.string.shopping_list_button);
        setSupportActionBar(myToolbar);
        editTextField = (EditText) findViewById(R.id.edit_shopping_list_text_field);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_shopping_list_toolbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_edit_shopping:


                Intent goingBack = new Intent();
                goingBack.putExtra("value","1");
                goingBack.putExtra("newText",editTextField.getText());
                setResult(RESULT_OK,goingBack);
                finish();

                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
