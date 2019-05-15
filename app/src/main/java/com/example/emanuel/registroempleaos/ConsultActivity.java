package com.example.emanuel.registroempleaos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emanuel.registroempleaos.data_access.SQLiteConnection;
import com.example.emanuel.registroempleaos.utils.DataBaseConstants;

public class ConsultActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnConsult,btnDelete;
    private EditText txtID;
    private TextView lbID,lbName,lbAge;
    private SQLiteConnection bdConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        bdConnection = new SQLiteConnection(this,"Employees",null,1);

        btnConsult = findViewById(R.id.btnConsult);
        btnDelete = findViewById(R.id.btnDelete);
        btnConsult.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        txtID = findViewById(R.id.txtIDC);

        lbID = findViewById(R.id.lbID);
        lbName = findViewById(R.id.lbName);
        lbAge = findViewById(R.id.lbAge);
    }

    @Override
    public void onClick(View view) {
        if(view == btnConsult){
            if(txtID.getText().length() < 1){
                Toast.makeText(getApplicationContext(),"El id parece no estar registrado",Toast.LENGTH_LONG).show();
                return;
            }
            consult();
            return;
        }
        if(view == btnDelete){

            return;
        }
    }

    private void consult(){
        SQLiteDatabase db = bdConnection.getWritableDatabase();

        try {
            String[] parameters = {txtID.getText().toString()};
            String[] fields = {DataBaseConstants.NAME_FIELD,DataBaseConstants.AGE_FIELD};

            Cursor cursor = db.query(DataBaseConstants.EMPLOYEE_TABLE,fields,DataBaseConstants.ID_FIELD+"=?",parameters,null,null,null);
            cursor.moveToFirst();

            lbID.setText(lbID.getText()+" "+txtID.getText());
            lbName.setText(lbName.getText()+" "+cursor.getString(0));
            lbAge.setText(lbAge.getText()+" "+cursor.getString(1));
            cleanForm();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El id parece no estar registrado",Toast.LENGTH_LONG).show();
            cleanForm();
            db.close();
        }

        db.close();
    }

    private void consultBySQL(){
        SQLiteDatabase db = bdConnection.getWritableDatabase();

        try {
            String[] parameters = {txtID.getText().toString()};


            Cursor cursor = db.rawQuery("SELECT "+DataBaseConstants.NAME_FIELD+","+DataBaseConstants.AGE_FIELD
                            +" FROM "+DataBaseConstants.EMPLOYEE_TABLE+" WHERE "+DataBaseConstants.ID_FIELD+"=?",parameters);
            cursor.moveToFirst();

            lbID.setText(lbID.getText()+" "+txtID.getText());
            lbName.setText(lbName.getText()+" "+cursor.getString(0));
            lbAge.setText(lbAge.getText()+" "+cursor.getString(1));
            cleanForm();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El id parece no estar registrado",Toast.LENGTH_LONG).show();
            cleanForm();
            db.close();
        }

        db.close();
    }

    private void cleanForm(){
        txtID.setText("");
    }
}
