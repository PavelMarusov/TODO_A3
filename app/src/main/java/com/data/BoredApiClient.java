package com.data;

import android.util.Log;

import androidx.annotation.BoolRes;

import com.model.BoredAction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class BoredApiClient {
    private String type;


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    BoredApi service = retrofit.create(BoredApi.class);

    public void getAction(String type, Float minPrice, Float maxPrice, BoredActionCallback callback) {
        Call<BoredAction> call = service.getAction(type, minPrice, maxPrice, 0f, 3f);
        Log.d("pop", "getAction");
        Log.d("pop", "Type :" + type);
        Log.d("pop", "minPrice :" + minPrice);
        Log.d("pop", "maxPrice :" + maxPrice);

        call.enqueue(new CoreCallback<BoredAction>() {
            @Override
            void onSuccess(BoredAction result) {
                callback.onSuccess(result);
            }

            @Override
            void onFailure(Exception exception) {
                callback.onFailure(exception);
            }
        });


    }

    public interface BoredActionCallback {
        void onSuccess(BoredAction boredAction);

        void onFailure(Exception ex);
    }

    private interface BoredApi {
        @GET("api/activity/")
        Call<BoredAction> getAction(
                @Query("type") String type,
                @Query("minPrice") Float minPrice,
                @Query("maxPrice") Float maxPrice,
                @Query("minaccessibility") Float minAccessibility,
                @Query("maxaccessibility") Float maxAccessibility
        );
    }
}
