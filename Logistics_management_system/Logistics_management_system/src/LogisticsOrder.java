import java.io.Serializable;
import java.util.Date;

/**
 * @author ZSH
 */
public class LogisticsOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    private String orderID;
    private String customerName;
    private String address;
    private Date orderDate;
    private double totalAmount;
    private boolean delivered;

    public LogisticsOrder(String orderID, String customerName, String address, Date orderDate, double totalAmount, boolean delivered) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.address = address;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.delivered = delivered;
    }

    // Getters and Setters
    public String getOrderID() { return orderID; }
    public void setOrderID(String orderID) { this.orderID = orderID; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public boolean isDelivered() { return delivered; }
    public void setDelivered(boolean delivered) { this.delivered = delivered; }

    @Override
    public String toString() {
        return "物流订单{" +
                "订单的ID='" + orderID + '\'' +
                ", 客户='" + customerName + '\'' +
                ", 地址='" + address + '\'' +
                ", 订单日期=" + orderDate +
                ", 金额=" + totalAmount +
                ", 是否送达=" + delivered +
                '}';
    }
}

