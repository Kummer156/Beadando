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
 * Model for storing information of the pizzas.
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
