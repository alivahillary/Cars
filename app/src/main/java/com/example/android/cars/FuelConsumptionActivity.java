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

public class FuelConsumptionActivity extends AppCompatActivity {

    private EditText fuelvalue;
    private EditText litres;
    private EditText distance;
    private Button btn_Calc;
    private Button btn_save;
    private TextView tv_ans;
    private static String URL_FUEL = "https://92494.000webhostapp.com/fuel.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_consumption);



        fuelvalue = (EditText)findViewById(R.id.fuelvalue);
        litres = (EditText)findViewById(R.id.litres);
        distance = (EditText)findViewById(R.id.distance);
        btn_Calc = (Button)findViewById(R.id.btn_Calc);
        btn_save =(Button)findViewById(R.id.btn_save);

        tv_ans = (TextView)findViewById(R.id.tv_ans);


        btn_Calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int num1 = Integer.parseInt(distance.getText().toString());
                int num2 = Integer.parseInt(litres.getText().toString());



                int  value = num1/num2;

                tv_ans.setText("(Km/litre) "+String.valueOf(value));


            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fuel();
            }
        });

    }

    private  void Fuel(){

        final String fuelvalue = this.fuelvalue.getText().toString().trim();
        final String litres = this.litres.getText().toString().trim();
        final String distance = this.distance.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FUEL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    try{

                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");

                        if(success.equals("1")){
                            Toast.makeText(FuelConsumptionActivity.this, "Fuel Records Added", Toast.LENGTH_SHORT).show();
                            
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(FuelConsumptionActivity.this, "Fuel Records Not Added"+e.toString(), Toast.LENGTH_SHORT).show();
                    }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FuelConsumptionActivity.this, "Fuel Records Not Added"+error.toString(), Toast.LENGTH_SHORT).show();

            }
        }

        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("fuelvalue",fuelvalue);
                params.put("litres",litres);
                params.put("distance",distance);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
