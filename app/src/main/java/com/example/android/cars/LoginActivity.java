package com.example.android.cars;

import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText username,password;
    private Button btn_login;
    private TextView txt_register;
    private static String URL_LOGIN ="https://92494.000webhostapp.com/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        txt_register = findViewById(R.id.txt_register);

     txt_register.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
             startActivity(i);
         }
     });

     btn_login.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             String Uname = username.getText().toString().trim();
             String Psword = password.getText().toString().trim();

              if(!Uname.isEmpty() || !Psword.isEmpty()) {
                  Login(Uname, Psword);

              }else{
                  username.setError("Username field is empty");
                  password.setError("Password field is empty");
              }

         }
     });

    }

    private void Login(final String username, final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");


                            if(success.equals("1")){

                                for(int i = 0; i<jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String username = object.getString("username").trim();
                                    String email = object.getString("email").trim();

                                    Toast.makeText(LoginActivity.this,
                                            "You're Logged in.  \nYour Username : "
                                                    +username+"\nYour Email :"
                                                    +email, Toast.LENGTH_SHORT)
                                            .show();

                                    startActivity( new Intent(LoginActivity.this,HomeActivity.class));

                                }

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Error" +e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LoginActivity.this, "Error" +error.toString(), Toast.LENGTH_SHORT).show();

            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

               Map<String, String> params = new HashMap<>();
               params.put("username",username);
               params.put("password",password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
