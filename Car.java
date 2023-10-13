//定义一个汽车类
class Car {
    private String name;
    private int width;
    private int height;
    private int length;
    private double x;
    private double y;
    private double fuel;

    Car(String name, int width, int height, int length, double fuel) {
        this.name = name;
        this.width = width;
        this.length = length;
        this.height = height;
        this.fuel = fuel;
        x = y = 0.0;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    double getFuel() {
        return fuel;
    }

    void putSepc() {
        System.out.println("名称：" + name);
        System.out.println("车宽：" + width + "mm");
        System.out.println("车高：" + height + "mm");
        System.out.println("车长：" + length + "mm");
    }

    boolean move(double dx, double dy) {
        double dist = Math.sqrt(dx * dx + dy * dy);
        if (dist > fuel)
            return false;
        else {
            fuel -= fuel;
            x += dx;
            y += dy;
            return true;
        }
    }
}