package com.example.academicaveragecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences myPreferences;
    private SharedPreferences.Editor myEditor;

    private EditText grade;
    private EditText credits;
    private TextView res;
    private TextView count;
    private TextView nz;
    private Button add;
    private Button clear;
    private Button dispaly;
    private Button sharing;
    private Button delete;
    public static float average;
    public static double allCredits;
    public static int counter;
    private Switch switch1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //----------------initializations-------------------------//
        myPreferences = getSharedPreferences("key" , 0);
        myEditor = myPreferences.edit();
        grade = (EditText)findViewById(R.id.edtGrade);
        credits = (EditText)findViewById(R.id.edtCredits);
        add = (Button)findViewById(R.id.btnAdd);
        clear = (Button)findViewById(R.id.btnclear);
        dispaly = (Button)findViewById(R.id.btnDisplay);
        res = (TextView)findViewById(R.id.txtResult);
        count = (TextView)findViewById(R.id.txtCounter);
        nz = (TextView)findViewById(R.id.txtCreee);
        switch1 = (Switch)findViewById(R.id.switch3);
        sharing = (Button)findViewById(R.id.btnShare);
        delete = (Button)findViewById(R.id.btnDelete);

        allCredits = 0;
        counter = 0;
        average = 0;

        //------------------set data visibility-------------------------//
        if(!(myPreferences.getString("textvalue","").equals(""))) {
            res.setText(myPreferences.getString("textvalue", ""));
            average = myPreferences.getFloat("val" , 0);
        }else{
            res.setText("");
        }
        if(!(myPreferences.getString("textvalue2","").equals(""))) {
            count.setText(myPreferences.getString("textvalue2",""));
            counter = Integer.parseInt(myPreferences.getString("textvalue2",""));
        }else{
            count.setText("");
        }
        if(!(myPreferences.getString("textvalue3","").equals(""))) {
            nz.setText(myPreferences.getString("textvalue3",""));
            allCredits = Double.parseDouble(myPreferences.getString("textvalue3",""));
        }else{
            count.setText("");
        }

        Toast.makeText(MainActivity.this,"Please enter your grade and credits",Toast.LENGTH_LONG).show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(grade.getText().toString().equals("") && credits.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this,"Waiting...",Toast.LENGTH_SHORT).show();
            }
        },7000);          //If there no action for 7 seconds, a messeage will be sent to user .


        //----------------Add button-------------------//
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(grade.getText().toString().equals("") || credits.getText().toString().equals("")){                        //Illegal state
                    Toast.makeText(MainActivity.this,"At least one field is empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                counter++;                                                                    //Promoted counter while legal

                double num = Double.parseDouble(grade.getText().toString());
                double cred = Double.parseDouble(credits.getText().toString());

                allCredits += cred;
                average += num*cred;

                refresh();

                grade.setText("");
                credits.setText("");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                average -= (Double.parseDouble(grade.getText().toString())*Double.parseDouble(credits.getText().toString()));
                allCredits -= Double.parseDouble(credits.getText().toString());
                counter--;

                refresh();

            }
        });
        //-----------------Clear the data----------------//
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allCredits = 0;
                counter = 0;
                average = 0;
                res.setText("");
                count.setText("");
                nz.setText("");
                Toast.makeText(MainActivity.this,"Data cleared",Toast.LENGTH_SHORT).show();
            }
        });

        dispaly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExampleDialog a = new ExampleDialog();

                a.show(getSupportFragmentManager(),"Ex");
            }
        });

        sharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plan");
                i.putExtra(Intent.EXTRA_TEXT,"Your Average: "+ average/allCredits + "\n\nNumber Of Courses: " + counter + "\n\nNumber Of Credits: " + allCredits);
                startActivity(Intent.createChooser(i,"Share by:"));
            }
        });
    }

    public void onBackPressed() {

        final Dialog d = new Dialog(MainActivity.this);

        d.setContentView(R.layout.dialog);

        Button dlg = d.findViewById(R.id.btnDialog);

            dlg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.super.finish();
                }
            });
        d.show();
    }
    //----------------Refreshing disply screen--------------//
    public void refresh(){
        res.setText(Double.toString(average/allCredits));
        count.setText(Integer.toString(counter));
        nz.setText(""+allCredits);
    }


    protected void onStart(){
        super.onStart();
    }
    protected void onResume(){
        super.onResume();
    }
    protected void onPause(){
        super.onPause();
    }
    protected void onStop(){
        super.onStop();
    }
    protected void onDestroy(){
        super.onDestroy();

        if(switch1.isChecked()) {
            if (!(res.getText().toString().equals(""))) {
                myEditor.putString("textvalue", res.getText().toString());
                myEditor.commit();
                myEditor.putFloat("val", average);
                myEditor.commit();
            }else{
                myEditor.putString("textvalue" , "");
                myEditor.commit();
            }
            if (!(count.getText().toString().equals(""))) {
                myEditor.putString("textvalue2", count.getText().toString());
                myEditor.commit();
            }else{
                myEditor.putString("textvalue2" , "");
                myEditor.commit();
            }
            if (!(nz.getText().toString().equals(""))) {
                myEditor.putString("textvalue3", nz.getText().toString());
                myEditor.commit();
            }else{
                myEditor.putString("textvalue3" , "");
                myEditor.commit();
            }
        }
    }
}
