package com.example.emanuel.registroempleaos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.emanuel.registroempleaos.data_access.SQLiteConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText txtID,txtName,txtAge;
    Button btnSave,btnConsult;
    SQLiteConnection bdConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtID = (EditText) findViewById(R.id.txtID);
        txtName = (EditText) findViewById(R.id.txtName);
        txtAge = (EditText) findViewById(R.id.txtAge);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnConsult = (Button) findViewById(R.id.btnConsult);

        bdConnection = new SQLiteConnection(this,"Employees",null,1);
    }

    @Override
    public void onClick(View view) {
        if(view == btnSave){

            return;
        }
        if(view == btnConsult){

            return;
        }
    }
}
