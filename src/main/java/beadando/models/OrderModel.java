package beadando.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Orders")
public class OrderModel {


    @Id
    @Column(name = "ORDER_ID")
    private int orderId;

    @ManyToOne
    private UserModel user;

    @Column
    private String itemIds;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getItemIds() {
        return itemIds;
    }

    public void setItemIds(String itemIds) {
        this.itemIds = itemIds;
    }

    @Override
    public String toString() {
        return "OrderModel{" + "id=" + orderId + user.toString() + "}";

    }

}
