public class vehicle {
    private String vehicleID;
    private String licensePlate;
    private String type;
    private String status;

    public vehicle(String vehicleID, String licensePlate, String type, String status) {
        this.vehicleID = vehicleID;
        this.licensePlate = licensePlate;
        this.type = type;
        this.status = status;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
