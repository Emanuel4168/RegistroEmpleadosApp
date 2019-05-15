package com.example.emanuel.registroempleaos;

import android.content.ContentValues;
import android.content.Intent;
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

    private EditText txtID,txtName,txtAge;
    private Button btnSave,btnModify,btnConsult;
    private SQLiteConnection bdConnection;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtID = (EditText) findViewById(R.id.txtIDC);
        txtName = (EditText) findViewById(R.id.txtName);
        txtAge = (EditText) findViewById(R.id.txtAge);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnConsult = (Button) findViewById(R.id.btnConsult);
        btnModify = findViewById(R.id.btnModify);
        btnSave.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnConsult.setOnClickListener(this);

        bdConnection = new SQLiteConnection(this,"Employees",null,1);
    }

    @Override
    public void onClick(View view) {
        if(view == btnSave){
            if(!validateFields()){
                Toast.makeText(getApplicationContext(),"Algunos campos no son válidos",Toast.LENGTH_LONG).show();
                return;
            }
            putEmployee();
            cleanForm();
            return;
        }
        if (view == btnModify) {
            if(!validateFields()){
                Toast.makeText(getApplicationContext(),"Algunos campos no son válidos",Toast.LENGTH_LONG).show();
                return;
            }
            modifyEmployee();
            return;
        }
        if(view == btnConsult){
            openConsultActivity();
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
        db.close();
    }

    private void putEmployeeBySQL(){
        SQLiteDatabase db = bdConnection.getWritableDatabase();

        String insert = "INSERT INTO "+DataBaseConstants.EMPLOYEE_TABLE+" ("+DataBaseConstants.ID_FIELD+","+DataBaseConstants.NAME_FIELD+","
                        +DataBaseConstants.AGE_FIELD+") VALUES ("+txtID.getText().toString()+", '"+txtName.getText().toString()+"' ,"
                        +txtAge.getText().toString()+")";
        db.execSQL(insert);

        db.close();
    }

    private void modifyEmployee(){
        SQLiteDatabase db = bdConnection.getWritableDatabase();
        String[] parameters = {txtID.getText().toString()};
        ContentValues values = new ContentValues();

        values.put(DataBaseConstants.NAME_FIELD,txtName.getText().toString());
        values.put(DataBaseConstants.AGE_FIELD,txtAge.getText().toString());

        db.update(DataBaseConstants.EMPLOYEE_TABLE,values,DataBaseConstants.ID_FIELD+"=?",parameters);
        Toast.makeText(getApplicationContext(),"Actualización correcta",Toast.LENGTH_LONG).show();
        cleanForm();
        db.close();
    }

    private void openConsultActivity(){
        intent = new Intent(this, ConsultActivity.class);
        startActivity(intent);
    }

    private void cleanForm(){
        txtID.setText("");
        txtName.setText("");
        txtAge.setText("");
    }

    private boolean validateFields(){
        return txtID.getText().length() > 1 && txtName.getText().length() > 10 && txtAge.getText().length() > 1;
    }
}
