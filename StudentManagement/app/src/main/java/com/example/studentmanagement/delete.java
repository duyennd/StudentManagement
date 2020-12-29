package com.example.studentmanagement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class delete extends AppCompatActivity {
    TextView tv_email,tv_phone,tv_name;
    ListView listView;
    ArrayList<HashMap<String, String>> al_contact = new ArrayList<HashMap<String, String>>();
    SimpleAdapter sa;
    DBHelper dbh;
    SQLiteDatabase db;
    ArrayAdapter<String> adapter;
    ArrayList<String> data=new ArrayList<String>();
    Cursor cursor;
    String[]from={"name","email","gender","phone"};
    int [] to={R.id.tv_name,R.id.tv_email,R.id.tv_gender,R.id.tv_phone};
    AlertDialog.Builder ab;
    Button butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        dbh = new DBHelper(this);
        db = dbh.getReadableDatabase();
        cursor = dbh.getContacs(db);
        listView = (ListView) findViewById(R.id.add_contact1);

        butt = findViewById(R.id.butt);


        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_phone = (TextView) findViewById(R.id.tv_phone);

        data.add("Tên: Nguyen Thi Duyen\nMSSV:20173076");
        data.add("Tên: Duong Hong Tuan \nMSSV:20173439");
        data.add("Tên: Tran Thi Thuy\nMSSV:20173394");

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,data);
        listView.setAdapter(adapter);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteStudent();
            }
        });


    }
    public void deleteStudent(){
        int pos=listView.getCheckedItemPosition();
        if(pos>-1){
            adapter.remove(data.get(pos).toString());
            Toast.makeText(getApplicationContext(),"Deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Select student", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }


}