package pojo;

import java.util.Date;

public class Outbound {
    String id;
    String employee_id;
    String goods_id;
    int num;
    Date trade_time;

    public Outbound(String id, String employee_id, String goods_id, int num, Date trade_time) {
        this.id = id;
        this.employee_id = employee_id;
        this.goods_id = goods_id;
        this.num = num;
        this.trade_time = trade_time;
    }

    public Outbound() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getTrade_time() {
        return trade_time;
    }

    public void setTrade_time(Date trade_time) {
        this.trade_time = trade_time;
    }

    @Override
    public String toString() {
        return "Outbound{" +
                "id='" + id + '\'' +
                ", employee_id='" + employee_id + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", num=" + num +
                ", trade_time=" + trade_time +
                '}';
    }
}
