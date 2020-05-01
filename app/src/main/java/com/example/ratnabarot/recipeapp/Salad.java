package com.example.ratnabarot.recipeapp;


import android.content.Intent;
import android.os.Bundle;


import com.bumptech.glide.Glide;
import com.example.ratnabarot.recipeapp.models.CategoryModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
public class Salad extends AppCompatActivity {

    RecyclerView recipe;
    FirebaseFirestore fStore;

    private ImageView imageView;

    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fStore = FirebaseFirestore.getInstance();

        recipe = findViewById(R.id.salad);

        // create a reference to the recipe collection
        CollectionReference recipesRef = fStore.collection("recipe");

        // Create a query against the collection
        Query query = recipesRef.whereEqualTo("categoryName", "Salad");

        //Query
        // Query query = fStore.collection("Salad");

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
                Glide.with(Salad.this)
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
                    intent = new Intent(Salad.this, SpiralizedBeatMandarin.class);
                    startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(Salad.this, BlackBeanQuinoa.class);
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(Salad.this, PeachArugula.class);
                    startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(Salad.this, GreenBean.class);
                    startActivity(intent);
                    break;
                case 4:
                    intent = new Intent(Salad.this, Chickpea.class);
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


}
