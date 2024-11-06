import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/logistics";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private Connection connection;

    public DatabaseManager() {
        try {
            // 加载mysql驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void close() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 创建一个新客户
    public boolean addCustomer(String customerID, String name, String address, String phone) {
        String sql = "INSERT INTO Customer (CustomerID, Name, Address, Phone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, customerID);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setString(4, phone);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 创建一个新订单
    public boolean addOrder(String orderID, String customerID, String orderDate, String status) {
        String sql = "INSERT INTO `Order` (OrderID, CustomerID, OrderDate, Status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, orderID);
            pstmt.setString(2, customerID);
            pstmt.setString(3, orderDate);
            pstmt.setString(4, status);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 创建一个新司机
    public boolean addDriver(String driverID, String name, String phone, String licenseNumber) {
        String sql = "INSERT INTO Driver (DriverID, Name, Phone, LicenseNumber) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, driverID);
            pstmt.setString(2, name);
            pstmt.setString(3, phone);
            pstmt.setString(4, licenseNumber);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 创建一个新车辆
    public boolean addVehicle(String vehicleID, String licensePlate, String type, String status) {
        String sql = "INSERT INTO Vehicle (VehicleID, LicensePlate, Type, Status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, vehicleID);
            pstmt.setString(2, licensePlate);
            pstmt.setString(3, type);
            pstmt.setString(4, status);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 创建一个新货物
    public boolean addItem(String itemID, String name, double weight, double dimensions, double price) {
        String sql = "INSERT INTO Item (ItemID, Name, Weight, Dimensions, Price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, itemID);
            pstmt.setString(2, name);
            pstmt.setDouble(3, weight);
            pstmt.setDouble(4, dimensions);
            pstmt.setDouble(5, price);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 创建一个新的运输记录
    public boolean addShipment(String shipmentID, String orderID, String driverID, String vehicleID, String shipmentDate, String shipmentStatus) {
        String sql = "INSERT INTO Shipment (ShipmentID, OrderID, DriverID, VehicleID, ShipmentDate, ShipmentStatus) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, shipmentID);
            pstmt.setString(2, orderID);
            pstmt.setString(3, driverID);
            pstmt.setString(4, vehicleID);
            pstmt.setString(5, shipmentDate); // Assuming shipmentDate is a String in the format "yyyy-MM-dd HH:mm:ss"
            pstmt.setString(6, shipmentStatus);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 创建一个新仓库
    public boolean addWarehouse(String warehouseID, String name, String address, int capacity) {
        String sql = "INSERT INTO Warehouse (WarehouseID, Name, Address, Capacity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, warehouseID);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setInt(4, capacity);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 根据ID返回客户
    public ResultSet getCustomer(String customerID) {
        String sql = "SELECT * FROM Customer WHERE CustomerID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, customerID);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getDriver(String driverID) throws SQLException {
        String query = "SELECT * FROM Driver WHERE DriverID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, driverID);
        return preparedStatement.executeQuery();
    }

    public ResultSet getItem(String itemID) throws SQLException {
        String query = "SELECT * FROM Item WHERE ItemID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, itemID);
        return preparedStatement.executeQuery();
    }

    public ResultSet getOrder(String orderID) throws SQLException {
        String query = "SELECT * FROM `Order` WHERE OrderID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, orderID);
        return preparedStatement.executeQuery();
    }

    public ResultSet getShipment(String shipmentID) throws SQLException {
        String query = "SELECT * FROM Shipment WHERE ShipmentID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, shipmentID);
        return preparedStatement.executeQuery();
    }

    public ResultSet getVehicle(String vehicleID) throws SQLException {
        String query = "SELECT * FROM Vehicle WHERE VehicleID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, vehicleID);
        return preparedStatement.executeQuery();
    }

    public ResultSet getWarehouse(String warehouseID) throws SQLException {
        String query = "SELECT * FROM Warehouse WHERE WarehouseID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, warehouseID);
        return preparedStatement.executeQuery();
    }

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        // 添加三个新客户
        dbManager.addCustomer("0000000001", "John Doe", "1234ElmSt", "555-1234");
        dbManager.addCustomer("0000000002", "Jane Doe", "5678OakSt", "555-5678");
        dbManager.addCustomer("0000000003", "Jim Beam", "9101MapSt", "555-9101");

        // 添加三个新订单
        dbManager.addOrder("0000000001", "0000000001", "2024-07-09", "Pending");
        dbManager.addOrder("0000000002", "0000000002", "2024-07-09", "Shipped");
        dbManager.addOrder("0000000003", "0000000003", "2024-07-09", "Delivered");

        // 添加三个新司机
        dbManager.addDriver("0000000001", "John Doe", "555-1234", "A123456");
        dbManager.addDriver("0000000002", "Jane Doe", "555-5678", "B654321");
        dbManager.addDriver("0000000003", "Jim Beam", "555-9101", "C789012");

        // 添加三个新车辆
        dbManager.addVehicle("0000000001", "ABC123", "Truck", "Available");
        dbManager.addVehicle("0000000002", "DEF456", "Van", "In Use");
        dbManager.addVehicle("0000000003", "GHI789", "SUV", "Maintenance");

        // 添加三个新货物
        dbManager.addItem("0000000001", "Laptop", 2.5, 15.6, 999.99);
        dbManager.addItem("0000000002", "Monitor", 5.0, 27.0, 199.99);
        dbManager.addItem("0000000003", "Keyboard", 1.0, 18.0, 49.99);

        // 添加三个新运输记录
        dbManager.addShipment("0000000001", "0000000001", "0000000001", "0000000001", "2024-07-09 10:00:00", "In Transit");
        dbManager.addShipment("0000000002", "0000000002", "0000000002", "0000000002", "2024-07-09 11:00:00", "Delivered");
        dbManager.addShipment("0000000003", "0000000003", "0000000003", "0000000003", "2024-07-09 12:00:00", "Pending");

        // 添加三个新仓库
        dbManager.addWarehouse("0000000001", "Main Warehouse", "1234ElmSt", 10000);
        dbManager.addWarehouse("0000000002", "Secondary Warehouse", "5678OakSt", 5000);
        dbManager.addWarehouse("0000000003", "Tertiary Warehouse", "9101MapleSt", 2000);

        dbManager.close();
    }
}
