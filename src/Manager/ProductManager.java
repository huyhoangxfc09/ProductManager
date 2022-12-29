package Manager;

import MenuClass.Candy;
import MenuClass.Category;
import MenuClass.Drinks;
import MenuClass.Product;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProductManager implements CRUD<Product> {
    private final ArrayList<Product> products;
    final CategoryManager categoryManager;

    public ProductManager(CategoryManager categoryManager) {
        products = new ArrayList<>();
        this.categoryManager = categoryManager;
    }

    public CategoryManager getCategoryManager() {
        return categoryManager;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public Product create(Scanner scanner) {
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        boolean checkPrice = true;
        double price = 0;
        while (checkPrice) {
            try {
                System.out.println("Enter product price: ");
                price = Double.parseDouble(scanner.nextLine());
                checkPrice = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter price.");
            }
        }
        boolean checkQuantity = true;
        int quantity = 0;
        while (checkQuantity) {
            try {
                System.out.println("Enter product quantity: ");
                quantity = Integer.parseInt(scanner.nextLine());
                checkQuantity = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter quantity.");
            }
        }
        categoryManager.displayAll(categoryManager.getCategories());
        Category category = choiceCategory(scanner);
        return choiceProduct(name, price, quantity, category, scanner);
    }

    Category choiceCategory(Scanner scanner) {
        Category category;
        boolean check = true;
        int idCategory = 0;
        while (check) {
            try {
                System.out.println("Enter choice category by id: (Enter 0 for create new)");
                idCategory = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter id category.");
            }
        }
        if (idCategory == 0) {
            category = categoryManager.create(scanner);
            categoryManager.add(category);
        } else {
            category = categoryManager.getById(idCategory);
        }
        if (category != null) {
            return category;
        } else {
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

    public Product choiceProduct(String name, double price, int quantity, Category category, Scanner scanner) {
        System.out.println("MENU");
        System.out.println("1. Candy:");
        System.out.println("2. Drink:");
        System.out.println("3. Another products :");
        System.out.println("Enter choice :");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                System.out.println("Enter weight candy");
                int weight = Integer.parseInt(scanner.nextLine());
                return new Candy(name, price, quantity, category, weight);
            case 2:
                System.out.println("Enter volume drink :");
                int volume = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter bottleType drink :");
                String bottleType = scanner.nextLine();
                return new Drinks(name, price, quantity, category, volume, bottleType);
            default:
                System.out.println("Enter new product :");
                return new Product(name, price, quantity, category);
        }
    }

    @Override
    public void update(Scanner scanner) {
        Product product = checkId(scanner);
        if (product != null) {
            System.out.println("Enter new product name: ");
            String name = scanner.nextLine();
            if (!name.equals("")) {
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
            if (choice.equalsIgnoreCase("Y")) {
                categoryManager.displayAll(categoryManager.getCategories());
                Category category = choiceCategory(scanner);
                product.setCategory(category);
            }
            System.out.println("Update product successfully!");
            title();
            product.display();
        } else {
            System.out.println("Not exist product have this id!");
        }
    }

    @Override
    public void deleteByID(Scanner scanner) {
        Product product = checkId(scanner);
        if (product != null) {
            products.remove(product);
            System.out.println("Delete product successfully!");
            title();
            product.display();
        } else {
            System.out.println("Not exist product have this id!");
        }
    }

    @Override
    public void displayAll(List<Product> products) {
        if (!products.isEmpty()) {
            System.out.println("List product: ");
            title();
            for (Product element : products) {
                element.display();
            }
        } else {
            System.out.println("Not exist product in list!");
        }
    }

    public void title() {
        System.out.printf("%-5s%-20s%-15s%-10s%-15s%-15s%-15s%s", "ID", "NAME", "PRICE", "QUANTITY", "CATEGORY", "WEIGHT", "VOLUME", "BOTTLE TYPE\n");
    }

    public Product checkId(Scanner scanner) {
        boolean check = true;
        int id = 0;
        while (check) {
            try {
                System.out.println("Enter id product: ");
                id = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Re-enter id.");
            }
        }
        for (Product element : products) {
            if ((element.getId() == id)) {
                return element;
            }
        }
        return null;
    }

    public void maxPrice() {
        if (!products.isEmpty()) {
            double max = products.get(0).getPrice();
            for (Product element : products) {
                if (element.getPrice() > max) ;
                max = element.getPrice();
            }
            System.out.println("The product with the highest price: ");
            title();
            for (Product element : products) {
                if (element.getPrice() == max) {
                    element.display();
                }
            }
        } else {
            System.out.println("There are no products in the list.");
        }
    }

    public void minPrice() {
        if (!products.isEmpty()) {
            double min = products.get(0).getPrice();
            for (Product element : products) {
                if (element.getPrice() < min) ;
                min = element.getPrice();
            }
            System.out.println("The products with the lowest price: ");
            title();
            for (Product element : products) {
                if (element.getPrice() == min) {
                    element.display();
                }
            }
        } else {
            System.out.println("There are no products in the list.");
        }
    }

    public void maxQuantity() {
        if (!products.isEmpty()) {
            double max = products.get(0).getQuantity();
            for (Product element : products) {
                if (element.getQuantity() > max) ;
                max = element.getQuantity();
            }
            System.out.println("The product with the highest price: ");
            title();
            for (Product element : products) {
                if (element.getQuantity() == max) {
                    element.display();
                }
            }
        } else {
            System.out.println("There are no products in the list.");
        }
    }

    public void minQuantity() {
        if (!products.isEmpty()) {
            double min = products.get(0).getQuantity();
            for (Product element : products) {
                if (element.getQuantity() < min) ;
                min = element.getQuantity();
            }
            System.out.println("The product with the highest price: ");
            title();
            for (Product element : products) {
                if (element.getQuantity() == min) {
                    element.display();
                }
            }
        } else {
            System.out.println("There are no products in the list.");
        }
    }

    public void displayCandy() {
        title();
        boolean flag = false;
        for (Product element : products) {
            if (element instanceof Candy) {
                element.display();
                flag =true;
            }
        }
        if (!flag){
            System.out.println("There are no products in the list.");
        }
    }

    public void displayDrinks() {
        title();
        boolean flag = false;
        for (Product element : products) {
            if (element instanceof Drinks) {
                element.display();
                flag = true;
            }
        }
        if (!flag){
            System.out.println("There are no products in the list.");
        }
    }

    public void displayDrinksByBottleType(Scanner scanner) {
        ArrayList<Drinks> listDrink = new ArrayList<>();
        for (Product element : products) {
            if (element instanceof Drinks) {
                listDrink.add((Drinks) element);
            }
        }
        if (!listDrink.isEmpty()) {
            System.out.println("Enter bottle type: ");
            String bottleType = scanner.nextLine();
            boolean flag = false;
            for (Drinks element : listDrink) {
                if (element.getBottleType().equals(bottleType)) {
                    element.display();
                    flag =true;
                }
            }
            if (!flag){
                System.out.println("There are no products in the list.");
            }
        }else {
            System.out.println("There are no products in the list.");
        }
    }
    public void displayCategory(Scanner scanner){
        System.out.println("Enter category: ");
        String category = scanner.nextLine();
        boolean flag = false;
        for (Product element: products){
            if (element.getCategory().getName().equalsIgnoreCase(category)){
                element.display();
                flag =true;
            }
        }
        if (!flag){
            System.out.println("There are no products in the list.");
        }
    }
}
