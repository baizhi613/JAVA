import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ItemManager {
    private Map<String, item> items;
    private DatabaseManager dbManager;


    public ItemManager(DatabaseManager dbManager) {
        this.items = new HashMap<>();
        this.dbManager = dbManager;
    }

    public void addItem(String id, String name, double weight, double dimensions, double price) {
        String sql = "INSERT INTO Item (ItemID, Name, Weight, Dimensions, Price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setDouble(3, weight);
            pstmt.setDouble(4, dimensions);
            pstmt.setDouble(5, price);
            pstmt.executeUpdate();
            System.out.println("商品已添加：" + new item(id, name, weight, dimensions, price));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateItem(String id, String name, double weight, double dimensions, double price) {
        String sql = "UPDATE Item SET Name = ?, Weight = ?, Dimensions = ?, Price = ? WHERE ItemID = ?";
        try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, weight);
            pstmt.setDouble(3, dimensions);
            pstmt.setDouble(4, price);
            pstmt.setString(5, id);
            pstmt.executeUpdate();
            System.out.println("商品已更新：" + new item(id, name, weight, dimensions, price));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeItem(String id) {
        String sql = "DELETE FROM Item WHERE ItemID = ?";
        try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            System.out.println("商品已删除！：" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printItems() {
        String sql = "SELECT * FROM Item";
        try (PreparedStatement pstmt = dbManager.getConnection().prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                item item = new item(
                        rs.getString("ItemID"),
                        rs.getString("Name"),
                        rs.getDouble("Weight"),
                        rs.getDouble("Dimensions"),
                        rs.getDouble("Price")
                );
                System.out.println(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}