package com.example.android.cars;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    TextView select_language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadLocale();

        setContentView(R.layout.activity_language);
        select_language=(TextView)findViewById(R.id.select_language);
        select_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //display list of languages using alertDialog

                showChangeLanguageDialog();

            }
        });

        }

        private  void  showChangeLanguageDialog(){

        final String[] listItems ={"English" ,"Swahili"};

        AlertDialog.Builder nBuilder = new AlertDialog.Builder(LanguageActivity.this);
        nBuilder.setTitle("Select Language . . .");
        nBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0) {
                    //English
                    setLocale("en");

                    recreate();


                }else if(which == 1){
                    //Swahili
                    setLocale("sw");

                    recreate();
                }

                //Dismiss Alert Dialogue when Language is selected
                dialog.dismiss();

            }
        });

        AlertDialog mDialog = nBuilder.create();

            mDialog.show();

        }

    private void setLocale(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        //save data to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();

    }


    //load language saved in shared preferences

    public void loadLocale(){

        SharedPreferences preferences =getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang","");
        setLocale(language);

    }

}
