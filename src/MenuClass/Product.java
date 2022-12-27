package MenuClass;

public class Product {
    private  static  int INDEX =1;
    private final  int id;
    private String name;
    private int price;
    private int quantity;
    private Category category;

    public Product(String name, int price, int quantity, Category category) {
        this.id = INDEX;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        INDEX++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category +
                '}';
    }
    public void display(){
        System.out.printf("%-5s%s-20s%s-15s%s-10s%s",id,name,price,quantity,category);
    }
}
