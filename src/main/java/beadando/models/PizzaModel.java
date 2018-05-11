package beadando.models;

import javax.persistence.*;

/**
 * Model for storing information of the pizzas
 */
@Entity
@Table(name = "Pizza")
public class PizzaModel {

    /**
     * Pizza ID.
     */
    @Id
    private int id;
    /**
     * Pizza name.
     */
    @Column
    private String pizza;
    /**
     * Pizza description.
     */
    @Column
    private String desc;
    /**
     * Price of the pizza.
     */
    @Column
    private int price;

    /**
     * @return The ID of the pizza
     */
    public int getId() {
        return id;
    }

    /**
     * @param id Pizzas id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The name of the pizza
     */
    public String getPizza() {
        return pizza;
    }

    /**
     * @param pizza pizzas name
     */
    public void setPizza(String pizza) {
        this.pizza = pizza;
    }

    /**
     * @return The description of the pizza
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc pizzas description
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return The price of the pizza
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price pizzas price
     */
    public void setPrice(int price) {
        this.price = price;
    }
}
