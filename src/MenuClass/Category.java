package MenuClass;

public class Category {
    private static  int INDEX = 1;
    private  final  int id;
    private String name;

    public Category(String name) {
        this.name = name;
        this.id = INDEX;
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    public void display(){
        System.out.printf("%-10s%s",id,name+"\n");
    }
}
