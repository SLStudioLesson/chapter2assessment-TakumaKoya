package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        displayRecipes();

                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        ArrayList<String> recipeData = fileHandler.readRecipes();

        if ("No recipes available.".equals(recipeData.get(0))) {
            System.out.print(recipeData.get(0));
        } else {
            System.out.println("\nRecipes:");
            System.out.println("-----------------------------------");

            // 一行ずつ出力
            for (String line : recipeData) {
                String[] element = line.split(",");
                for (int i = 0; i < element.length; i++) {
                    if (i == 0) {
                        System.out.println("Recipe Name:" + element[i]);
                    } else if (i == 1) {
                        System.out.print("Main Ingredients:" + element[i]);
                    } else {
                        System.out.print("," + element[i]);
                    }
                }
                System.out.print("\n"); // 改行
                System.out.println("-----------------------------------");
            }

        }
    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void addNewRecipe() {
        RecipeFileHandler handler = new RecipeFileHandler();

        // 入力受付
        try {
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();
            System.out.print("Enter main ingredients (comma separated): ");
            String ingredients = reader.readLine();
    
            // RecipeFileHandlerのaddRecipeメソッド実行
            handler.addRecipe(recipeName, ingredients);

        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }

    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {

    }

}
