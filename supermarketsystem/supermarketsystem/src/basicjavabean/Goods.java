package basicjavabean;
// 商品类
public class Goods {
    private String id;
    private String name;
    // 进价
    private double inPrice;
    // 出价
    private double outPrice;
    // 存货
    private int storage;
    // 促销
    private double promotion;

    public Goods() {
    }

    public Goods(String id, String name, double inPrice, double outPrice, int storage, double promotion) {
        this.id = id;
        this.name = name;
        this.inPrice = inPrice;
        this.outPrice = outPrice;
        this.storage = storage;
        this.promotion = promotion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInPrice() {
        return inPrice;
    }

    public void setInPrice(double inPrice) {
        this.inPrice = inPrice;
    }

    public double getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(double outPrice) {
        this.outPrice = outPrice;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public double getPromotion() {
        return promotion;
    }

    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }

    //输出商品信息
    public void printGoodsInfo(){

    }
}
