package MenuClass;

public class Drinks extends Product{
    private int volume;
    private String bottleType;

    public Drinks(String name, int price, int quantity, Category category, int volume, String bottleType) {
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
}
