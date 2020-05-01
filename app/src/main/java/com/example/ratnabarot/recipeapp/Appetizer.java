package com.example.ratnabarot.recipeapp;

import android.content.Intent;
import android.os.Bundle;


import com.bumptech.glide.Glide;
import com.example.ratnabarot.recipeapp.models.CategoryModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Appetizer extends AppCompatActivity {

    RecyclerView recipe;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;

    private ImageView imageView;

    private FirestoreRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appetizer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        recipe = findViewById(R.id.appetizer);

        // create a reference to the recipe collection
        final CollectionReference recipesRef = fStore.collection("recipe");

        // Create a query against the collection
        Query query = recipesRef.whereEqualTo("categoryName", "Appetizer");

        //Query
       // Query query = fStore.collection("appetizer");

        //RecyclerOptions
        FirestoreRecyclerOptions<CategoryModel> options = new FirestoreRecyclerOptions.Builder<CategoryModel>()
                .setQuery(query, CategoryModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<CategoryModel, CategoryViewHolder>(options) {

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
                return new CategoryViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position, @NonNull CategoryModel model) {

                holder.list_name.setText(model.getRecipeName());
                holder.list_desc.setText(model.getRecipeDescription());
                Glide.with(Appetizer.this)
                        .load(model.getRecipeImage())
                        .into(imageView);

                //like function
                holder.numLike.setText(String.valueOf(model.getNumLike()));
                holder.likeRecipe.setOnClickListener(new View.OnClickListener() {



                    @Override
                    public void onClick(View v) {

                        //grabbing the document ID of the currently clicked recipe from the Recipe collection
                        DocumentSnapshot snapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());

                        fStore.collection("recipe")
                                .document(snapshot.getId())
                                .update("numLike", FieldValue.increment(1));

                    }
                });

            }


        };



        recipe.setHasFixedSize(true);
        recipe.setLayoutManager(new LinearLayoutManager(this));
        recipe.setAdapter(adapter);



    }



    private class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView list_name;
        private TextView list_desc;
        private ImageView likeRecipe;
        private TextView numLike;



        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            list_name = itemView.findViewById(R.id.list_recipeName);
            list_desc = itemView.findViewById(R.id.list_recipeDescription);
            imageView = itemView.findViewById(R.id.recipe_image);
            likeRecipe = itemView.findViewById(R.id.numLikes_btn);
            numLike = itemView.findViewById(R.id.numLikes_Lbl);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            Intent intent;

            switch(getAdapterPosition()) {
                case 0:
                    intent = new Intent(Appetizer.this, SweetPotatoTots.class);
                    startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(Appetizer.this, GrilledVegetable.class);
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(Appetizer.this, Bruschetta.class);
                    startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(Appetizer.this, ChickenNuggets.class);
                    startActivity(intent);
                    break;
                case 4:
                    intent = new Intent(Appetizer.this, BakedCornCrab.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            };
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (adapter != null) {
            adapter.stopListening();

        }


    }

    //Dashboard - Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appetizer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_home:
                Intent openHome = new Intent(this, Categories.class);
                startActivity(openHome);
                return true;
            case R.id.action_account:
                Intent openAccount = new Intent( this, profile.class);
                startActivity(openAccount);
                return true;
            case R.id.action_logout:
                Intent logout = new Intent(this, Login.class);
                //FirebaseAuth.getInstance().signOut();//logout
                startActivity(logout);
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

        return super.onOptionsItemSelected(item);
    }


}
