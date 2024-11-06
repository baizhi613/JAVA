public class item {
    private String itemID;
    private String name;
    private double weight;
    private double dimensions;
    private double price;

    public item(String itemID, String name, double weight, double dimensions, double price) {
        this.itemID = itemID;
        this.name = name;
        this.weight = weight;
        this.dimensions = dimensions;
        this.price = price;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDimensions() {
        return dimensions;
    }

    public void setDimensions(double dimensions) {
        this.dimensions = dimensions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "item{" +
                "商品ID='" + itemID + '\'' +
                ", 商品名称='" + name + '\'' +
                ", 重量=" + weight +
                ", 体积=" + dimensions +
                ", 价格=" + price +
                '}';
    }
}
