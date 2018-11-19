package com.example.android.cars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VehiclesActivity extends AppCompatActivity {

    private EditText vehiclename,vehicleplate,enginecapacity,odometer;
    private Button btn_Add;
    private static String URL_VEHICLES="https://92494.000webhostapp.com/vehicles.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);


    vehiclename = findViewById(R.id.vehiclename);
    vehicleplate = findViewById(R.id.vehicleplate);
    enginecapacity = findViewById(R.id.enginecapacity);
    odometer = findViewById(R.id.odometer);
    btn_Add= findViewById(R.id.btn_Add);


    btn_Add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Vehicle();

        }
    });

    }

      private void Vehicle(){


        final String vehiclename = this.vehiclename.getText().toString().trim();
        final String vehicleplate = this.vehicleplate.getText().toString().trim();
        final String enginecapacity = this.vehicleplate.getText().toString().trim();
        final String odometer = this.odometer.getText().toString().trim();

          StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_VEHICLES,
                  new Response.Listener<String>() {
                      @Override
                      public void onResponse(String response) {
                       try{

                           JSONObject jsonObject = new JSONObject(response);

                           String success =jsonObject.getString("success");

                           if(success.equals("1")){

                               Toast.makeText(VehiclesActivity.this, "Vehicle Added", Toast.LENGTH_SHORT).show();
                           }


                       } catch (JSONException e) {
                           e.printStackTrace();
                           Toast.makeText(VehiclesActivity.this, "Failed! Try Again"+ e.toString(), Toast.LENGTH_SHORT).show();
                       }
                      }
                  },

                  new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {
                          Toast.makeText(VehiclesActivity.this, "Failed! Try Again"+ error.toString(), Toast.LENGTH_SHORT).show();

                      }
                  })
          {
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {

                  Map<String, String> params = new HashMap<>();
                  params.put("vehiclename",vehiclename);
                  params.put("vehicleplate",vehicleplate);
                  params.put("enginecapacity",enginecapacity);
                  params.put("odometer",odometer);

                  return params;
              }
          };

          RequestQueue requestQueue = Volley.newRequestQueue(this);
          requestQueue.add(stringRequest);


}

}
