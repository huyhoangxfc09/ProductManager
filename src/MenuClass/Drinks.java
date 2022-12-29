package MenuClass;

public class Drinks extends Product{
    private int volume;
    private String bottleType;

    public Drinks(String name, double price, int quantity, Category category, int volume, String bottleType) {
        super(name, price, quantity, category);
        this.volume = volume;
        this.bottleType = bottleType;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getBottleType() {
        return bottleType;
    }

    public void setBottleType(String bottleType) {
        this.bottleType = bottleType;
    }

    @Override
    public String toString() {
        return "Drinks" +
                "volume=" + volume +
                ", bottleType='" + bottleType + '\'' +
                ", which is a subclass of "
                + super.toString();
    }

    @Override
    public void display() {
        System.out.printf("%-5s%-20s%-15s%-10s%-15s%-15s%-15s%s",getId(),getName(),getPrice(),getQuantity(),getCategory().getName(),"",getVolume(),getBottleType()+"\n");
    }
}
