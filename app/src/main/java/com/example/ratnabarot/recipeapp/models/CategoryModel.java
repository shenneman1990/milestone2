package com.example.ratnabarot.recipeapp.models;

public class CategoryModel {

    private String recipeName;

    private String recipeDescription;

    private String recipeImage;


    private int numLike;


    private CategoryModel() {}

    private CategoryModel(String recipeName, String recipeDescription, String recipeImage) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeImage = recipeImage;


    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getRecipeImage() {return recipeImage;}

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }


    public int getNumLike() {
        return numLike;
    }

    public void setNumLike(int numLike) {
        this.numLike = numLike;
    }


}
