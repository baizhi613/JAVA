import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 12345;
    private OrderManager orderManager;

    public Server(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("服务器已启动，等待客户端连接...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("连接成功");
                new Thread(new ClientHandler(clientSocket, orderManager)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private OrderManager orderManager;

    public ClientHandler(Socket clientSocket, OrderManager orderManager) {
        this.clientSocket = clientSocket;
        this.orderManager = orderManager;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {

            String command = (String) ois.readObject();
            switch (command) {
                case "add":
                    LogisticsOrder newOrder = (LogisticsOrder) ois.readObject();
                    orderManager.addOrder(newOrder);
                    oos.writeObject("Order added");
                    break;
                case "update":
                    String orderIDToUpdate = (String) ois.readObject();
                    boolean delivered = (Boolean) ois.readObject();
                    orderManager.updateOrder(orderIDToUpdate, delivered);
                    oos.writeObject("Order updated");
                    break;
                case "remove":
                    String orderIDToRemove = (String) ois.readObject();
                    orderManager.removeOrder(orderIDToRemove);
                    oos.writeObject("Order removed");
                    break;
                case "query":
                    String orderIDToQuery = (String) ois.readObject();
                    LogisticsOrder order = orderManager.getOrderById(orderIDToQuery);
                    oos.writeObject(order);
                    break;
                default:
                    oos.writeObject("Unknown command");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
