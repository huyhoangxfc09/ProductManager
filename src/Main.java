
import Manager.CategoryManager;
import Manager.ProductManager;

import MenuClass.Category;
import MenuClass.Product;

import java.util.Scanner;

public class Main {
    static String path1 = "C:\\Users\\PC\\OneDrive\\Desktop\\ProductManager\\ProductManager\\src\\Product.data";
    static String path2 = "C:\\Users\\PC\\OneDrive\\Desktop\\ProductManager\\ProductManager\\src\\Category.data";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CategoryManager categoryManager = new CategoryManager();
        ProductManager productManager = new ProductManager(categoryManager);
        productManager.readFile(path1);
        categoryManager.readFile(path2);
        menuProduct(productManager,scanner);
    }
    private static  void  menuProduct(ProductManager productManager,Scanner scanner){
        int choice;
        do {
            System.out.println("MENU");
            System.out.println("1. Menu of category.");
            System.out.println("2. Add new product.");
            System.out.println("3. Update product by id.");
            System.out.println("4. Delete product by id.");
            System.out.println("5. Display all product.");
            System.out.println("6. Show products by candy.");
            System.out.println("7. Show products by drink.");
            System.out.println("8. Products with the largest and smallest prices.");
            System.out.println("9. Products with the largest and smallest quantity.");
            System.out.println("10. Show products by bottle type.");
            System.out.println("11. Show products by category.");
            System.out.println("12. Displays a list of Candy with the largest weight.");
            System.out.println("13. Search products by name.");
            System.out.println("14. Write input data to file.");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    menuCategory(productManager.getCategoryManager(),scanner);
                    break;
                case 2:
                    Product product = productManager.create(scanner);
                    productManager.add(product);
                    productManager.writeFile(path1);
                    break;
                case 3:
                    productManager.displayAll(productManager.getProducts());
                    productManager.update(scanner);
                    productManager.writeFile(path1);
                    break;
                case 4:
                    productManager.displayAll(productManager.getProducts());
                    productManager.deleteByID(scanner);
                    productManager.writeFile(path1);
                    break;
                case 5:
                    productManager.displayAll(productManager.getProducts());
                    break;
                case 6:
                    productManager.displayCandy();
                    break;
                case 7:
                    productManager.displayDrinks();
                    break;
                case 8:
                    maxMinProduct(productManager,scanner);
                    break;
                case 9:
                    maxMinQuantity(productManager,scanner);
                    break;
                case 10:
                    productManager.displayDrinksByBottleType(scanner);
                    break;
                case 11:
                    productManager.displayCategory(scanner);
                    break;
                case 12:
                    productManager.maxWeightCandy();
                    break;
                case 13:
                    productManager.searchName(scanner);
                    break;
            }
        } while (choice!=0);
    }
    private static void menuCategory(CategoryManager categoryManager,Scanner scanner){
        int choice;
        do {
            System.out.println("MENU");
            System.out.println("1. Add category.");
            System.out.println("2. Display all category.");
            System.out.println("3. Delete category by ID.");
            System.out.println("4. Edit category by ID.");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    Category category =categoryManager.create(scanner);
                    categoryManager.add(category);
                    categoryManager.writeFile(path2);
                    break;
                case 2:
                    categoryManager.displayAll(categoryManager.getCategories());
                    break;
                case 3:
                    categoryManager.displayAll(categoryManager.getCategories());
                    categoryManager.deleteByID(scanner);
                    categoryManager.writeFile(path2);
                    break;
                case 4:
                    categoryManager.displayAll(categoryManager.getCategories());
                    categoryManager.update(scanner);
                    categoryManager.writeFile(path2);
                    break;
            }
        } while (choice!=0);
    }
    public  static void maxMinProduct(ProductManager productManager,Scanner scanner){
        int choice;
        do {
            System.out.println("MENU");
            System.out.println("1. The product with the highest price.");
            System.out.println("2. The product with the lowest price.");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    productManager.maxPrice();
                    break;
                case 2:
                    productManager.minPrice();
                    break;
            }
        } while (choice!=0);
    }
    public  static void maxMinQuantity(ProductManager productManager,Scanner scanner){
        int choice;
        do {
            System.out.println("MENU");
            System.out.println("1. The product with the highest quantity.");
            System.out.println("2. The product with the lowest quantity.");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    productManager.maxQuantity();
                    break;
                case 2:
                    productManager.minQuantity();
                    break;
            }
        } while (choice!=0);
    }
}