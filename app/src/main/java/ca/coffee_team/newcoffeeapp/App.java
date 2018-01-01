package ca.coffee_team.newcoffeeapp;

import android.app.Application;

import ca.coffee_team.newcoffeeapp.api.ServerAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static ServerAPI mServerApi;

    public static ServerAPI getApi() {
        return mServerApi;
    }

    private void initServerApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://serene-eyrie-60807.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mServerApi = retrofit.create(ServerAPI.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initServerApi();
    }
}