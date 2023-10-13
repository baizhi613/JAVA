import java.util.Scanner;

class CarTester2 {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("请输入车的数据：");
        System.out.print("名称：");
        String name = stdIn.next();
        System.out.print("宽度：");
        int width = stdIn.nextInt();
        System.out.print("高度：");
        int height = stdIn.nextInt();
        System.out.print("长度：");
        int length = stdIn.nextInt();
        System.out.print("燃料数量：");
        double fuel = stdIn.nextDouble();

        Car mycar = new Car(name, width, height, length, fuel);
        while (true) {
            System.out.println("当前位置(" + mycar.getX() + "," + mycar.getY() + ").剩余燃料" + mycar.getFuel());

            System.out.println("是否移动[0=No/1=yes]:");
            if (stdIn.nextInt() == 0)
                break;
            System.out.println("X方向上移动距离:");
            double dx = stdIn.nextDouble();
            System.out.println("Y方向上移动的距离:");
            double dy = stdIn.nextDouble();

            if (!mycar.move(dx, dy))
                System.out.println("燃料不足！");
        }
    }
}
