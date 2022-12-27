package Manager;

import MenuClass.Category;
import MenuClass.Product;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProductManager implements CRUD<Product> {
    private final ArrayList<Product>products;
    private final CategoryManager categoryManager;
    public ProductManager(CategoryManager categoryManager){
        products =new ArrayList<>();
        this.categoryManager= categoryManager;
    }
    public CategoryManager getCategoryManager(){
        return categoryManager;
    }
    public ArrayList<Product> getProducts(){
        return products;
    }

    @Override
    public Product create(Scanner scanner) {
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        boolean checkPrice = true;
        int price = 0;
        while (checkPrice){
            try{
                System.out.println("Enter product price: ");
                price = Integer.parseInt(scanner.nextLine());
                checkPrice = false;
            }catch (InputMismatchException | NumberFormatException e ){
                System.out.println("Re-enter price.");
            }
        }
        boolean checkQuantity = true;
        int quantity = 0;
        while (checkQuantity){
            try{
                System.out.println("Enter product quantity: ");
                quantity = Integer.parseInt(scanner.nextLine());
                checkQuantity = false;
            }catch (InputMismatchException | NumberFormatException e ){
                System.out.println("Re-enter quantity.");
            }
        }
        categoryManager.displayAll(categoryManager.getCategories());
        Category category = choiceCategory(scanner);
        return new Product(name,price,quantity,category);
    }
    private Category choiceCategory(Scanner scanner){
        Category category;
        boolean check = true;
        int idCategory = 0;
        while (check){
            try {
                System.out.println("Enter choice category by id: (Enter 0 for create new)");
                idCategory = Integer.parseInt(scanner.nextLine());
                check = false;
            }catch (InputMismatchException | NumberFormatException e ){
                System.out.println("Re-enter id category.");
            }
        }
        if (idCategory==0){
            category = categoryManager.create(scanner);
            categoryManager.add(category);
        }else {
            category = categoryManager.getById(idCategory);
        }
        if ( category!= null){
            return category;
        }else {
            return choiceCategory(scanner);
        }
    }

    @Override
    public void add(Product product) {
        products.add(product);
        System.out.println("Add product successfully!");
        title();
        product.display();
    }

    @Override
    public void update(Scanner scanner) {
        Product product = checkId(scanner);
        if (product!=null){
            System.out.println("Enter new product name: ");
            String name = scanner.nextLine();
            if (!name.equals("")){
                product.setName(name);
            }
            System.out.println("Enter new product price: ");
            int price = Integer.parseInt(scanner.nextLine());
            product.setPrice(price);
            System.out.println("Enter new product quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            product.setQuantity(quantity);
            System.out.println("Do you want to change the category ?");
            System.out.println("Enter Y to update and any keyword to skip: ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("Y")){
                categoryManager.displayAll(categoryManager.getCategories());
                Category category = choiceCategory(scanner);
                product.setCategory(category);
            }
            System.out.println("Update product successfully!");
            title();
            product.display();
        }else {
            System.out.println("Not exist product have this id!");
        }
    }

    @Override
    public void deleteByID(Scanner scanner) {
        Product product = checkId(scanner);
        if (product!=null){
            products.remove(product);
            System.out.println("Delete product successfully!");
            title();
            product.display();
        }else {
            System.out.println("Not exist product have this id!");
        }
    }

    @Override
    public void displayAll(List<Product> products) {
        if (!products.isEmpty()){
            System.out.println("List product: ");
            title();
            for (Product element : products) {
                element.display();
            }
        }else {
            System.out.println("Not exist product in list!");
        }
    }
    public void title(){
            System.out.printf("%-5s%s-20s%s-15s%s-10s%s","ID","NAME","PRICE","QUANTITY","CATEGORY\n");
    }
    public Product checkId(Scanner scanner){
        boolean check = true;
        int id = 0;
        while (check){
            try{
                System.out.println("Enter id product: ");
                id = Integer.parseInt(scanner.nextLine());
                check = false;
            }catch (InputMismatchException | NumberFormatException e ){
                System.out.println("Re-enter id.");
            }
        }
        for (Product element : products) {
            if ((element.getId()==id)){
                return element;
            }
        }
        return  null;
    }

}
