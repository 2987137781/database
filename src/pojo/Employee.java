package pojo;

public class Employee {
    String id;
    String name;
    String phone_number;
    Integer authority;

    public Employee(String id, String name, String phone_number, Integer authority) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.authority = authority;
    }

    public Employee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", authority=" + authority +
                '}';
    }
}
