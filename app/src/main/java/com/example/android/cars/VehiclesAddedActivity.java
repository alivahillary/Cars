package com.example.android.cars;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VehiclesAddedActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CustomAdapter adapter;
    private List<Vehicles> vehicles_91214;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_added);

     recyclerView=(RecyclerView)findViewById(R.id.recycler_view);

     vehicles_91214 =new ArrayList<>();

     display_vehicles(0);

        linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new CustomAdapter (this,vehicles_91214);
        recyclerView.setAdapter(adapter);


    }


    private void display_vehicles(final int id){

        @SuppressLint("StaticFieldLeak") AsyncTask<Integer ,Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("https://92494.000webhostapp.com/show.php?id="+id)
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i =0;i<array.length();i++){

                        JSONObject object = array.getJSONObject(i);

                        Vehicles  vehicles= new Vehicles(object.getInt("id"),object.getString("vehiclename"),
                                object.getString("vehicleplate"),object.getString("enginecapacity"),object.getString("odometer"));

                        vehicles_91214.add(vehicles);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("End of Content");
                }
                return null;

            }

            @Override

            protected void onPostExecute(Void avoid){
                adapter.notifyDataSetChanged();
            }
        };


        task.execute(id);



    }

}
