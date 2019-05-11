package com.example.emanuel.registroempleaos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emanuel.registroempleaos.data_access.SQLiteConnection;
import com.example.emanuel.registroempleaos.utils.DataBaseConstants;

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
            putEmployee();
            return;
        }
        if(view == btnConsult){

            return;
        }
    }

    private void putEmployee(){
        SQLiteDatabase db = bdConnection.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DataBaseConstants.ID_FIELD,Integer.parseInt(txtID.getText().toString()));
        values.put(DataBaseConstants.NAME_FIELD,txtName.getText().toString());
        values.put(DataBaseConstants.AGE_FIELD, Integer.parseInt(txtAge.getText().toString()));

        Long storeID = db.insert(DataBaseConstants.EMPLOYEE_TABLE,DataBaseConstants.ID_FIELD,values);
        Toast.makeText(getApplicationContext(),"ID de registro: "+storeID,Toast.LENGTH_LONG).show();
    }
}
