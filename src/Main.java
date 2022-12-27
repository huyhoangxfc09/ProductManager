import Manager.CategoryManager;
import Manager.ProductManager;
import MenuClass.Category;
import MenuClass.Product;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CategoryManager categoryManager = new CategoryManager();
        ProductManager productManager = new ProductManager(categoryManager);
        menuProduct(productManager,scanner);
    }
    private static  void  menuProduct(ProductManager productManager, Scanner scanner){
        int choice;
        do {
            System.out.println("MENU");
            System.out.println("1. Menu of category.");
            System.out.println("2. Add new product.");
            System.out.println("3. Update product by id.");
            System.out.println("4. Delete product by id.");
            System.out.println("5. Display all product.");
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
                    break;
                case 3:
                    productManager.update(scanner);
                    break;
                case 4:
                    productManager.deleteByID(scanner);
                    break;
                case 5:
                    productManager.displayAll(productManager.getProducts());
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
                    break;
                case 2:
                    categoryManager.displayAll(categoryManager.getCategories());
                    break;
                case 3:
                    categoryManager.displayAll(categoryManager.getCategories());
                    categoryManager.deleteByID(scanner);
                    break;
                case 4:
                    categoryManager.displayAll(categoryManager.getCategories());
                    categoryManager.update(scanner);
                    break;
            }
        } while (choice!=0);
    }
}