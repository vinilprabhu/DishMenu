package model;

import java.io.Serializable;

/**
 * Created by vinil on 4/7/17.
 */
public class Dish implements Serializable {
    public String name ;
    public String price;
    public String quantity;

    public Dish(String name1, String price1, String quantity1) {
        name = name1;
        price = price1;
        quantity=quantity1;
    }

    public Dish() {



    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }
}
