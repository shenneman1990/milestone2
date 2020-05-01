package com.example.ratnabarot.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToLogin (View view){
        Intent intent = new Intent (this, Login.class);
        startActivity(intent);
    }

    public void goToRegister (View view){
        Intent intent = new Intent (this, register.class);
        startActivity(intent);
    }

}