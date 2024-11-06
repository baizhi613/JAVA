
public class OrderProcessor implements Runnable {
    private OrderManager orderManager;
    private String operation;
    private String orderID;

    public OrderProcessor(OrderManager orderManager, String operation, String orderID) {
        this.orderManager = orderManager;
        this.operation = operation;
        this.orderID = orderID;
    }

    @Override
    public void run() {
        switch (operation) {
            case "add":
                LogisticsOrder newOrder = new LogisticsOrder(orderID, "客户 " + orderID, "地址 " + orderID, new java.util.Date(), Math.random() * 1000, false);
                orderManager.addOrder(newOrder);
                break;
            case "update":
                orderManager.updateOrder(orderID, true);
                break;
            case "remove":
                orderManager.removeOrder(orderID);
                break;
            default:
                System.out.println("未知操作: " + operation);
        }
    }
}
