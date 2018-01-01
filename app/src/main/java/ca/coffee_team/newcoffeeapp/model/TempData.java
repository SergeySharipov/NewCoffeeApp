package ca.coffee_team.newcoffeeapp.model;

import java.util.ArrayList;
import java.util.List;

public class TempData {

    public static List<Customer> getCustomers(){
        List<Customer> list = new ArrayList<>();
        for(int i = 0; i<16;i++) {
            list.add(new Customer(i,"Customer"+i));
        }
        return list;
    }

    public static List<Order> getOrders(){
        List<Order> list = new ArrayList<>();
        for(int i = 0; i<16;i++) {
            list.add(new Order(i,"Order"+i));
        }
        return list;
    }

    public static List<Product> getProducts(){
        List<Product> list = new ArrayList<>();
        for(int i = 0; i<16;i++) {
            list.add(new Product(i,"Product"+i));
        }
        return list;
    }
}
