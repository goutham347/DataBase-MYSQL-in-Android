package com.tt.dbmysql;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    EditText username,qualification,yearofpassing,rollno,userid;
    Button addDetails,viewDetails,deleteData,UpdateData,deleteAllData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper = new DataBaseHelper(this);

        userid = (EditText)findViewById(R.id.edt_id);
        username = (EditText)findViewById(R.id.edt_username);
        qualification = (EditText)findViewById(R.id.edt_quali);
        yearofpassing = (EditText)findViewById(R.id.edt_yrop);
        rollno = (EditText)findViewById(R.id.edt_rolno);

        addDetails = (Button)findViewById(R.id.btn_add);
        viewDetails = (Button)findViewById(R.id.btn_view);
        deleteData = (Button)findViewById(R.id.btn_dlt);
        deleteAllData = (Button)findViewById(R.id.btn_dltall);
        UpdateData = (Button)findViewById(R.id.btn_update);

        AddDetails();
        ViewAllData();
        DeleteData();
        UpdateData();
        DeleteAll();
    }

   public void AddDetails(){
        addDetails.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                boolean IsEntry = dataBaseHelper.InsertDeleteSpace(userid.getText().toString(),username.getText().toString(),qualification.getText().toString(),yearofpassing.getText().toString(),rollno.getText().toString());

                if (IsEntry) {
                    Toast.makeText(MainActivity.this, "Data Entry With USER ID", Toast.LENGTH_SHORT).show();
                }
                else{
                    dataBaseHelper.insertData(username.getText().toString(),qualification.getText().toString(),yearofpassing.getText().toString(),rollno.getText().toString());
                    Toast.makeText(MainActivity.this,"Data Entry With ID",Toast.LENGTH_SHORT).show();
                }

                userid.setText("");
                username.setText("");
                qualification.setText("");
                yearofpassing.setText("");
                rollno.setText("");
            }
        });
   }

    public void  ViewAllData(){
viewDetails.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Cursor cursorresult = dataBaseHelper.getAllData();
        if(cursorresult.getCount() == 0){
            showmsg("Please add all student details","Nothing found");

            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (cursorresult.moveToNext()){
            stringBuffer.append("User Id :"+cursorresult.getString(0)+"\n");
            stringBuffer.append("User Name :"+cursorresult.getString(1)+"\n");
            stringBuffer.append("Qualification :"+cursorresult.getString(2)+"\n");
            stringBuffer.append("Year of Passing :"+cursorresult.getString(3)+"\n");
            stringBuffer.append("Roll No :"+cursorresult.getString(4)+"\n\n\n");

        }
        showmsg("Student Details",stringBuffer.toString());
        userid.setText("");
        username.setText("");
        qualification.setText("");
        yearofpassing.setText("");
        rollno.setText("");
    }
});
    }
    public void DeleteData(){
deleteData.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Integer deleterows = dataBaseHelper.DeleteData(userid.getText().toString());
        if (deleterows > 0)
            Toast.makeText(MainActivity.this,"Data was Deleted",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this,"Data Was Not Found",Toast.LENGTH_SHORT).show();
        userid.setText("");
        username.setText("");
        qualification.setText("");
        yearofpassing.setText("");
        rollno.setText("");
    }
});
    }
    public void UpdateData(){
 UpdateData.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         boolean isupdate = dataBaseHelper.UpdateData(userid.getText().toString(),username.getText().toString(),qualification.getText().toString(),yearofpassing.getText().toString(),rollno.getText().toString());
         if (isupdate) {
             Toast.makeText(MainActivity.this, "Data Updated Successful", Toast.LENGTH_SHORT).show();
         }
         else {
             Toast.makeText(MainActivity.this, "Data Not Update", Toast.LENGTH_SHORT).show();
         }
         userid.setText("");
         username.setText("");
         qualification.setText("");
         yearofpassing.setText("");
         rollno.setText("");
     }
 });
    }
    public void DeleteAll(){
deleteAllData.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        dataBaseHelper.DeleteAll();
        Toast.makeText(MainActivity.this,"All Data was Deleted",Toast.LENGTH_SHORT).show();

    }
});
    }

    public void showmsg(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
