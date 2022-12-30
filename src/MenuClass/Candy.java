package MenuClass;

import java.io.Serializable;

public class Candy extends Product implements Serializable {
    private int weight;

    public Candy(String name, double price, int quantity, Category category, int weight) {
        super(name, price, quantity, category);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Candy" +
                "weight=" + weight +
                ", which is a subclass of "
                + super.toString();
    }

    @Override
    public void display() {
        System.out.printf("%-5s%-20s%-15s%-10s%-15s%-15s%-15s%s",getId(),getName(),getPrice(),getQuantity(),getCategory().getName(),getWeight(),"",""+"\n");
    }
}
