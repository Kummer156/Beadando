package beadando.models;

/*-
 * #%L
 * Beadando
 * %%
 * Copyright (C) 2016 - 2018 Faculty of Informatics, University of Debrecen
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import javax.persistence.*;

/**
 * Model for storing orders.
 */
@Entity
@Table(name = "Orders")
public class OrderModel {
    /**
     * Basic constructor.
     *
     * @param orderId orderID
     * @param user user
     * @param itemIds itemIds
     * @param price price
     */
    public OrderModel(int orderId, UserModel user, String itemIds, int price) {
        this.orderId = orderId;
        this.user = user;
        this.itemIds = itemIds;
        this.price = price;
    }

    /**
     * Empty constructor.
     */
    public OrderModel()
    {

    }

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
     * The price of the order.
     */
    @Column
    private int price;

    /**
     * @return The price of the order
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price Price
     */
    public void setPrice(int price) {
        this.price = price;
    }

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
        return "OrderModel{" + "id=" + orderId + user.toString() + " itemids:" + itemIds + " price" + price + "}";

    }

}
