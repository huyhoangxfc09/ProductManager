package Manager;

import MenuClass.Category;
import MenuClass.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CategoryManager implements CRUD<Category>{
    private ArrayList<Category> categories;


    public CategoryManager() {
        categories = new ArrayList<>();
    }
    public ArrayList<Category> getCategories(){
        return  categories;
    }
    public  void title(){
        System.out.printf("%-10s%s","ID","NAME\n");
    }
    public Category findId(int id){
        for (Category element : categories) {
            if(element.getId()==id){
                return element;
            }
        }
        return null;
    }
    public Category getById(int idCategory){
        for (Category element : categories) {
            if ((element.getId())==idCategory){
                return element;
            }
        }
        return null;
    }
    @Override
    public Category create(Scanner scanner) {
        System.out.println("Enter product type name: ");
        String name = scanner.nextLine();
        return new Category(name);
    }
    @Override
    public void add(Category category) {
        categories.add(category);
        System.out.println("Add category successfully!");
        title();
        category.display();
    }
    @Override
    public void update(Scanner scanner) {
        boolean check = true;
        int id =0;
        while (check){
            try {
                System.out.println("Enter Id edit: ");
                id = Integer.parseInt(scanner.nextLine());
                check =false;
            }catch (InputMismatchException|NumberFormatException e ){
                System.out.println("Re-enter ID.");
            }
        }
        Category category = findId(id);
        if (category!=null){
            System.out.println("Enter new category name: ");
            String name = scanner.nextLine();
            if (!name.equals("")) {
                category.setName(name);
            }
            System.out.println("Update category successfully!");
            title();
            category.display();
        }else {
            System.out.println("Not exist category have this id!");
        }
    }
    @Override
    public void deleteByID(Scanner scanner) {
        boolean check = true;
        int id =0;
        while (check){
            try {
                System.out.println("Enter Id edit: ");
                id = Integer.parseInt(scanner.nextLine());
                check =false;
            }catch (InputMismatchException|NumberFormatException e ){
                System.out.println("Re-enter ID.");
            }
        }
        Category category = findId(id);
        if (category!=null){
            categories.remove(category);
            System.out.println("Delete category successfully!");
            title();
            category.display();
        }else {
            System.out.println("Not exist category have this id!");
        }
    }
    @Override
    public void displayAll(List<Category> categories) {
        if (categories!=null){
            System.out.println("List  categories: ");
            title();
            for (Category element: categories) {
                element.display();
            }
        }else {
            System.out.println("List categories haven't element!");
        }
    }
    public  void  writeFile(String path){
        File file = new File(path);
        try{
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
//            for (Category element :categories){
//                oos.writeObject(element);
//            }
//            oos.flush();
            oos.writeObject(categories);

            oos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void  readFile(String path){
        File file = new File(path);
        try{
            InputStream is =new  FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            categories = (ArrayList<Category>) ois.readObject();
//            Category list;
//            while (true){
//                Object oj = ois.readObject();
//                if (oj==null){
//                    break;
//                }
//                if(oj!=null){
//                    list = (Category) oj;
//                    this.categories.add(list);
//                }
//            }
            ois.close();
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
