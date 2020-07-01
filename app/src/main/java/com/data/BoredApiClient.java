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
private  String type;



    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    BoredApi service = retrofit.create(BoredApi.class);

   public void getAction(String type,Float minPrice, Float maxPrice,BoredActionCallback callback) {
        Call<BoredAction> call = service.getAction(type,minPrice,maxPrice,0f,3f);
       Log.d("pop","getAction");
       Log.d("pop","Type :"+type);
       Log.d("pop","minPrice :" +minPrice);
       Log.d("pop","maxPrice :" +maxPrice);

       call.enqueue(new Callback<BoredAction>() {
            @Override
            public void onResponse(Call<BoredAction> call, Response<BoredAction> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        String activity = response.body().getActivity();
                        callback.onSuccess(response.body());
                        Log.d("pop",response.body().toString());
                    }
                    else {
                        callback.onFailure(new Exception("Body is empty"));

                    }
                }
                else {
                    callback.onFailure(new Exception("Response code" + response.code()));
                }
            }

            @Override
            public void onFailure(Call<BoredAction> call, Throwable t) {
                callback.onFailure(new Exception(t.getMessage()));

            }
        });


    }
    public  interface BoredActionCallback{
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
