package com.example.yairalphaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.yairalphaver.FBref.FBDB;
import static com.example.yairalphaver.FBref.refData;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<String> StuffList = new ArrayList<>();
    ArrayList<LVdata> Values = new ArrayList<>();

    Button btn;
    EditText et;
    LVdata data;
    String st;
    String str1;
    ListView lv;
    String dataTMP;
    ArrayAdapter adp;
    AlertDialog.Builder adb;
    ValueEventListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        btn = (Button) findViewById(R.id.btn);
        et = (EditText) findViewById(R.id.et);
        lv = (ListView) findViewById(R.id.lv);
        data = new LVdata();

       ValueEventListener listener = new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot ds) {

               StuffList.clear();
               Values.clear();

               for(DataSnapshot data : ds.getChildren()) {

                   str1 = (String) data.getKey();
                   LVdata dataTMP = data.getValue(LVdata.class);
                   Values.add(dataTMP);
                   StuffList.add(str1);
               }
               adp = new ArrayAdapter<String>(Main2Activity.this,R.layout.support_simple_spinner_dropdown_item, StuffList);
               lv.setAdapter(adp);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       };
       refData.addValueEventListener(listener);
        lv.setOnItemClickListener(this);

            }




    public void add (View view){

        st = et.getText().toString();
        data.setLvd(st);
        refData.child(st).setValue(data);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        adb=new AlertDialog.Builder(this);
        adb.setMessage("Do you want to delete this text?");
        adb.setNegativeButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tmp= StuffList.get(position);
                refData.child(tmp).removeValue();
            }
        });
        AlertDialog ad = adb.create();
        ad.show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String st = item.getTitle().toString();

        if (st.equals("Authentication")){
            Intent t=new Intent(this, MainActivity.class);
            startActivity(t);}

        if (st.equals("DataBase")){
            Intent t=new Intent(this, Main2Activity.class);
            startActivity(t);
        }

        if (st.equals("Storage")){
            Intent t=new Intent(this, Main3Activity.class);
            startActivity(t);
        }

        return super.onOptionsItemSelected(item);
    }


}
