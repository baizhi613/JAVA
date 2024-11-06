public class driver {
    private String driverID;
    private String name;
    private String phone;
    private String licenseNumber;

    public driver(String driverID, String name, String phone, String licenseNumber) {
        this.driverID = driverID;
        this.name = name;
        this.phone = phone;
        this.licenseNumber = licenseNumber;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
