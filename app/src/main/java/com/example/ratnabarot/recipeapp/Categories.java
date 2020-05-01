package com.example.ratnabarot.recipeapp;

        import android.content.Intent;
        import android.os.Bundle;

        import com.google.android.material.floatingactionbutton.FloatingActionButton;
        import com.google.android.material.snackbar.Snackbar;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

        import android.view.View;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.Toast;

        import java.sql.*;

public class Categories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    public void appetizer(View view){
        //Takes to a second activity(options in appetizers & their ingredients) when the button is clicked.
        Intent openAppetizers = new Intent(Categories.this, Appetizer.class);
        startActivity(openAppetizers);
    }

    public void breakfast(View view){
        //Takes to a second activity(options in desserts & their ingredients) when the button is clicked.
        Intent openBreakfasts = new Intent(Categories.this, Breakfast.class);
        startActivity(openBreakfasts);
    }

    public void soup(View view){
        //Takes to a second activity(options in soups & their ingredients) when the button is clicked.
        Intent openSoups = new Intent(Categories.this, Soup.class);
        startActivity(openSoups);
    }

    public void salad(View view){
        //Takes to a second activity(options in salads & their ingredients) when the button is clicked.
        Intent openSalads = new Intent(Categories.this, Salad.class);
        startActivity(openSalads);
    }

    public void drinks(View view){
        //Takes to a second activity(options in drinks & their ingredients) when the button is clicked.
        Intent openDrinks = new Intent(Categories.this, Drink.class);
        startActivity(openDrinks);
    }

    public void dessert(View view){
        //Takes to a second activity(options in desserts & their ingredients) when the button is clicked.
        Intent openDesserts = new Intent(Categories.this, Dessert.class);
        startActivity(openDesserts);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_home:
                Intent openHome = new Intent(this, Login.class);
                startActivity(openHome);
                return true;
            case R.id.action_account:
                Intent openAccount = new Intent( this, profile.class);
                startActivity(openAccount);
                return true;
            case R.id.action_feedback:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"barotr@csp.edu", "nicholsd1@csp.edu", "hennemas@csp.edu"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                i.putExtra(Intent.EXTRA_TEXT   , "Enter your feedback here");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
                return true;
        }

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }
}
