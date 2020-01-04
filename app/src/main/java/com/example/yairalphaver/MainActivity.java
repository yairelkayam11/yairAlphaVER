package com.example.yairalphaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    Button btn1;
    EditText email;
    EditText phone;
    String Semail;
    String Sphone;
    String popup1 = "email pass";
    public FirebaseAuth refAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);


    }


    public void login(View view) {
        Semail = email.getText().toString();
        Sphone = phone.getText().toString();
        Toast.makeText(this, Semail + Sphone, Toast.LENGTH_SHORT).show();
        refAuth.createUserWithEmailAndPassword(Semail, Sphone).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(popup1, "createUserWithEmail:success");
                    FirebaseUser user = refAuth.getCurrentUser();
                } else {
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    Log.w("MainActivity", "createUserWithEmail:failure", task.getException());

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String st= item.getTitle().toString();


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











