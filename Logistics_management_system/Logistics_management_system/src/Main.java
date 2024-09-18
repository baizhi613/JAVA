import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();
        DatabaseManager dbManager = new DatabaseManager();
        ItemManager itemManager = new ItemManager(dbManager);

        //dbManager.loadOrdersFromDatabase(orderManager);

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("请选择操作：");
            System.out.println("1. 添加订单");
            System.out.println("2. 添加商品");
            System.out.println("3. 更新订单");
            System.out.println("4. 删除订单");
            System.out.println("5. 显示所有订单");
            System.out.println("6. 保存订单到文件");
            System.out.println("7. 从文件加载订单");
            System.out.println("8. 退出");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("请输入订单ID：");
                    String addOrderID = scanner.nextLine();
                    new Thread(new OrderProcessor(orderManager, "add", addOrderID)).start();
                    break;
                case 2:
                    System.out.print("请输入商品ID：");
                    String itemID = scanner.nextLine();
                    System.out.print("请输入商品名称：");
                    String name = scanner.nextLine();
                    System.out.print("请输入商品重量：");
                    double weight = scanner.nextDouble();
                    System.out.print("请输入商品尺寸：");
                    double dimensions = scanner.nextDouble();
                    System.out.print("请输入商品价格：");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    itemManager.addItem(itemID, name, weight, dimensions, price);
                    break;
                case 3:
                    System.out.print("请输入要更新的订单ID：");
                    String updateOrderID = scanner.nextLine();
                    new Thread(new OrderProcessor(orderManager, "update", updateOrderID)).start();
                    break;
                case 4:
                    System.out.print("请输入要删除的订单ID：");
                    String removeOrderID = scanner.nextLine();
                    new Thread(new OrderProcessor(orderManager, "remove", removeOrderID)).start();
                    break;
                case 5:
                    orderManager.printOrders();
                    break;
                case 6:
                    System.out.print("请输入文件名：");
                    String saveFile = scanner.nextLine();
                    orderManager.saveOrdersToFile(saveFile);
                    break;
                case 7:
                    System.out.print("请输入文件名：");
                    String loadFile = scanner.nextLine();
                    orderManager.loadOrdersFromFile(loadFile);
                    break;
                case 8:
                    // 启动服务器线程
                    new Thread(() -> {
                        Server server = new Server(orderManager);
                        server.start();
                    }).start();
                    // 启动客户端线程
                    new Thread(() -> {
                        Client client = new Client();
                    }).start();
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("无效的选择，请重新选择。");
            }
        }

        scanner.close();
    }
}
