package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class RecipeFileHandler {
    private String filePath;

    public RecipeFileHandler() {
        filePath = "app/src/main/resources/recipes.txt";
    }

    public RecipeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 設問1: 一覧表示機能
     * recipes.txtからレシピデータを読み込み、それをリスト形式で返します。 <br> 
     * IOExceptionが発生したときは<i>Error reading file: 例外のメッセージ</i>とコンソールに表示します。
     *
     * @return レシピデータ
     */
    public ArrayList<String> readRecipes() {
        String filename = filePath;
        File dataFile = new File(filename);
        ArrayList<String> recipeData = new ArrayList<>();

        if (dataFile.length() == 0) {
            recipeData.add("No recipes available.");
        } else {
            //レシピ一行ずつ配列へ保存
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    recipeData.add(line);
                }
                
            } catch (IOException e) {
                System.out.println("Error reading file:" + e.getMessage());
                
            }

        }

        
        return recipeData;
    }

    /**
     * 設問2: 新規登録機能
     * 新しいレシピをrecipes.txtに追加します。<br>
     * レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
     *
     * @param recipeName レシピ名
     * @param ingredients 材料名
     */
     // 
    public void addRecipe(String recipeName, String ingredients) {
        String filename = filePath;
        ArrayList<String> recipeData = new ArrayList<>();

        //ファイル読み込み
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recipeData.add(line);
            }
            
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }

        //ファイル書き込み
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < recipeData.size(); i++) {
                writer.write(recipeData.get(i));
                writer.newLine();
            }

            //引数の文字列追加
            writer.write(recipeName + "," + ingredients);
            
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
        
    }
}
