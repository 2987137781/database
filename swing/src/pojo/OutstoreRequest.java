package pojo;

public class OutstoreRequest {
    private String requestId;
    private String employeeId;
    private String productId;
    private int quantity;

    public OutstoreRequest(String requestId, String employeeId, String productId, int quantity) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
