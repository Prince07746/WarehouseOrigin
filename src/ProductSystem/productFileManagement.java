package ProductSystem;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class productFileManagement {

    public static Path productFile;
    static {
        productFile = Paths.get("src","FILEDB","PRODUCT.txt").toAbsolutePath();
    }
    public static ArrayList<Item> listOfProduct = new ArrayList<>();

     // loading the product file
     public static void loadingProduct(){
         try(FileReader reader = new FileReader(productFile.toString())){
             Scanner line = new Scanner(reader);
             while(line.hasNextLine()) {
                 String newLine = line.nextLine();
                 String[] lineSplited = newLine.split(",");
                 Item newItem = new Item(lineSplited[0], lineSplited[1], lineSplited[2], lineSplited[3],
                         Integer.parseInt(lineSplited[4]), Integer.parseInt(lineSplited[5]),
                         Integer.parseInt(lineSplited[6]),lineSplited[7], Integer.parseInt(lineSplited[8]),
                         lineSplited[9]);
                 listOfProduct.add(newItem);
             }
             System.out.println("\t Product file loaded successfully");
             line.close();
         } catch (IOException e){
             System.out.println("\t Error loading product file: " + e.getMessage());
         }
     }

     // saving the product file
    public static void writingToProduct(Item item){
        loadingProduct();
        boolean check = false;
        for(Item items:listOfProduct){
            if(items.getId().equals(item.getId())){
                System.out.println("\t the product with the same ID exist ");
                check = true;
                break;
            }
        }

        if(!check){
            listOfProduct.add(item);
            StringBuilder formater = new StringBuilder();
            for(Item items : listOfProduct) {
                formater.append(items.toString()+"\n");
            }
            try (FileWriter writer = new FileWriter(productFile.toString())) {
                writer.write(formater.toString());
                System.out.println("\t Product added successfully");
                listOfProduct.clear();
                writer.close();
            } catch (IOException e){
                System.out.println("\t Error writing to product file: " + e.getMessage());
            }
        }


    }










}
