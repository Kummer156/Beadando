package beadando.models;

import javax.persistence.*;

/**
 * Model for storing orders.
 */
@Entity
@Table(name = "Orders")
public class OrderModel {


    /**
     * Orders ID.
     */
    @Id
    @Column(name = "ORDER_ID")
    private int orderId;

    /**
     * User the order belongs to.
     */
    @ManyToOne
    private UserModel user;

    /**
     * A string to store the ordered items.
     */
    @Column
    private String itemIds;

    /**
     * @return The orderID
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId Orders id
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return The user whom the order belongs
     */
    public UserModel getUser() {
        return user;
    }

    /**
     * @param user user
     */
    public void setUser(UserModel user) {
        this.user = user;
    }

    /**
     * @return The ordered item ids
     */
    public String getItemIds() {
        return itemIds;
    }

    /**
     * @param itemIds item ids
     */
    public void setItemIds(String itemIds) {
        this.itemIds = itemIds;
    }

    /**
     * @return The data as a string
     */
    @Override
    public String toString() {
        return "OrderModel{" + "id=" + orderId + user.toString() + "}";

    }

}
