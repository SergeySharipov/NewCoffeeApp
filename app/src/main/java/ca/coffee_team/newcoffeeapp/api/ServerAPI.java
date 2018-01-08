package ca.coffee_team.newcoffeeapp.api;

import java.util.List;

import ca.coffee_team.newcoffeeapp.model.Customer;
import ca.coffee_team.newcoffeeapp.model.Order;
import ca.coffee_team.newcoffeeapp.model.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServerAPI {

    /**
     * API for customers
     */
    @GET("/customers")
    Call<List<Customer>> getCustomers();

    @GET("/customers/{id}")
    Call<Customer> getCustomer(@Path("id") String id);

    @POST("/customers")
    Call<Customer> addCustomer(@Body Customer customer);

    @PUT("/customers/{id}")
    Call<Customer> updateCustomer(@Path("id") String id, @Body Customer customer);

    @DELETE("/customers/{id}")
    Call<Customer> deleteCustomer(@Path("id") String id);

    /**
     * API for orders
     */
    @GET("/orders")
    Call<List<Order>> getOrders();

    @GET("/customers/{customer_id}/orders")
    Call<List<Order>> getOrders(@Path("customer_id") String customerId);

    @GET("/orders/{id}")
    Call<Order> getOrder(@Path("id") String id);

    @POST("/orders")
    Call<Order> addOrder(@Body Order order);

    @PUT("/orders/{id}")
    Call<Order> updateOrder(@Path("id") String id, @Body Order order);

    @DELETE("/orders/{id}")
    Call<Order> deleteOrder(@Path("id") String id);

    /**
     * API for products
     */
    @GET("/products")
    Call<List<Product>> getProducts();

    @GET("/products/{id}")
    Call<Product> getProduct(@Path("id") String id);

    @POST("/products")
    Call<Product> addProduct(@Body Product product);

    @PUT("/products/{id}")
    Call<Product> updateProduct(@Path("id") String id, @Body Product product);

    @DELETE("/products/{id}")
    Call<Product> deleteProduct(@Path("id") String id);
}
