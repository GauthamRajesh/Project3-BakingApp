package com.example.android.bakingapp.utils;

class RecipeNumber {
    static int getRecipeId(String name) {
        switch(name) {
            case "Nutella Pie":
                return 1;
            case "Brownie":
                return 2;
            case "Yellow Cake":
                return 3;
            case "Cheesecake":
                return 4;
        }
        return -1;
    }
}
