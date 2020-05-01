package com.example.ratnabarot.recipeapp.models;

public class RecipeModel {

    private String ingredientsName;

    private RecipeModel() {};

    private RecipeModel(String ingredientsName) {

        this.ingredientsName = ingredientsName;

    }

    public String getIngredientsName() {
        return ingredientsName;
    }

    public void setIngredientsName(String ingredientsName) {
        this.ingredientsName = ingredientsName;
    }


}
