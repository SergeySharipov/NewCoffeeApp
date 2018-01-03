package ca.coffee_team.newcoffeeapp.api;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import ca.coffee_team.newcoffeeapp.App;
import ca.coffee_team.newcoffeeapp.model.Customer;
import ca.coffee_team.newcoffeeapp.model.ModelObject;
import ca.coffee_team.newcoffeeapp.model.Order;
import ca.coffee_team.newcoffeeapp.model.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Path;

public class ServerAPIHelper implements ServerAPI {
    private final String LOG_NAME = "ServerAPIHelper";
    private ItemsResponseCallback mItemsResponseCallback;
    private ItemResponseCallback mItemResponseCallback;

    public void setItemsResponseCallback(ItemsResponseCallback itemsResponseCallback) {
        mItemsResponseCallback = itemsResponseCallback;
    }

    public void setItemResponseCallback(ItemResponseCallback itemResponseCallback) {
        mItemResponseCallback = itemResponseCallback;
    }

    private void updateItem(ModelObject item){
        if (mItemResponseCallback != null) {
            mItemResponseCallback.updateItem(item);
        }
    }

    private void updateListItems(List<? extends ModelObject> list){
        if (mItemsResponseCallback != null) {
            mItemsResponseCallback.updateListItems(list);
        }
    }

    @Override
    public Call<List<Customer>> getCustomers() {

        App.getApi().getCustomers().enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(@NonNull Call<List<Customer>> call, @NonNull Response<List<Customer>> response) {
                Log.e(LOG_NAME, response.message());
                updateListItems(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Customer>> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<List<Customer>> getCustomer(@Path("id") String id) {

        App.getApi().getCustomer(id).enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(@NonNull Call<List<Customer>> call, @NonNull Response<List<Customer>> response) {
                Log.e(LOG_NAME, response.message());
                updateItem(response.body().get(0));
            }

            @Override
            public void onFailure(@NonNull Call<List<Customer>> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }


//    @Override
//    public Call<Customer> getCustomer(@Path("id") String id) {
//        App.getApi().getCustomer(id).enqueue(new Callback<Customer>() {
//            @Override
//            public void onResponse(@NonNull Call<Customer> call, @NonNull Response<Customer> response) {
//                Log.e(LOG_NAME, response.message());
//                updateItem(response.body());
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Customer> call, @NonNull Throwable t) {
//                Log.e(LOG_NAME, t.getMessage());
//            }
//        });
//        return null;
//    }

    @Override
    public Call<Customer> addCustomer(@Body Customer customer) {
        App.getApi().addCustomer(customer).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(@NonNull Call<Customer> call, @NonNull Response<Customer> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(@NonNull Call<Customer> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Customer> updateCustomer(@Path("id") String id, @Body Customer customer) {
        App.getApi().updateCustomer(id, customer).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(@NonNull Call<Customer> call, @NonNull Response<Customer> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(@NonNull Call<Customer> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Customer> deleteCustomer(@Path("id") String id) {
        App.getApi().deleteCustomer(id).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(@NonNull Call<Customer> call, @NonNull Response<Customer> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(@NonNull Call<Customer> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<List<Order>> getOrders() {
        App.getApi().getOrders().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(@NonNull Call<List<Order>> call, @NonNull Response<List<Order>> response) {
                Log.e(LOG_NAME, response.message());
                updateListItems(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Order>> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<List<Order>> getOrders(@Path("customer_id") String customerId) {
        App.getApi().getOrders(customerId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(@NonNull Call<List<Order>> call, @NonNull Response<List<Order>> response) {
                Log.e(LOG_NAME, response.message());
                updateListItems(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Order>> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

//    @Override
//    public Call<Order> getOrder(@Path("id") String id) {
//        App.getApi().getOrder(id).enqueue(new Callback<Order>() {
//            @Override
//            public void onResponse(@NonNull Call<Order> call, @NonNull Response<Order> response) {
//                Log.e(LOG_NAME, response.message());
//                updateItem(response.body());
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Order> call, @NonNull Throwable t) {
//                Log.e(LOG_NAME, t.getMessage());
//            }
//        });
//        return null;
//    }

    @Override
    public Call<List<Order>> getOrder(@Path("id") String id) {

        App.getApi().getOrder(id).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(@NonNull Call<List<Order>> call, @NonNull Response<List<Order>> response) {
                Log.e(LOG_NAME, response.message());
                updateItem(response.body().get(0));
            }

            @Override
            public void onFailure(@NonNull Call<List<Order>> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Order> addOrder(@Body Order order) {
        App.getApi().addOrder(order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(@NonNull Call<Order> call, @NonNull Response<Order> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(@NonNull Call<Order> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Order> updateOrder(@Path("id") String id, @Body Order order) {
        App.getApi().updateOrder(id, order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(@NonNull Call<Order> call, @NonNull Response<Order> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(@NonNull Call<Order> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Order> deleteOrder(@Path("id") String id) {
        App.getApi().deleteOrder(id).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(@NonNull Call<Order> call, @NonNull Response<Order> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(@NonNull Call<Order> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<List<Product>> getProducts() {

        App.getApi().getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                Log.e(LOG_NAME, response.message());
                updateListItems(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

//    @Override
//    public Call<Product> getProductId(@Path("id") String id) {
//        App.getApi().getProductId(id).enqueue(new Callback<Product>() {
//            @Override
//            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
//                Log.e(LOG_NAME, response.message());
//                updateItem(response.body());
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
//                Log.e(LOG_NAME, t.getMessage());
//            }
//        });
//        return null;
//    }

    @Override
    public Call<List<Product>> getProduct(@Path("id") String id) {

        App.getApi().getProduct(id).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                Log.e(LOG_NAME, response.message());
                updateItem(response.body().get(0));
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Product> addProduct(@Body Product product) {
        App.getApi().addProduct(product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Product> updateProduct(@Path("id") String id, @Body Product product) {
        App.getApi().updateProduct(id, product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }

    @Override
    public Call<Product> deleteProduct(@Path("id") String id) {
        App.getApi().deleteProduct(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                Log.e(LOG_NAME, response.message());
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                Log.e(LOG_NAME, t.getMessage());
            }
        });
        return null;
    }
}
