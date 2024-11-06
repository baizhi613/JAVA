public class warehouse {
    private String warehouseID;
    private String name;
    private String address;
    private int capacity;

    public warehouse(String warehouseID, String name, String address, int capacity) {
        this.warehouseID = warehouseID;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    public String getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(String warehouseID) {
        this.warehouseID = warehouseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
