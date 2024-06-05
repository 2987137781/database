package pojo;
import operation.*;

public class OutstoreRequest {
    private String employeeId;
    private String productId;
    private int quantity;

    // Constructor, getters, and setters

    public OutstoreRequest(String employeeId, String productId, int quantity) {

        this.employeeId = employeeId;
        this.productId = productId;
        this.quantity = quantity;
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
