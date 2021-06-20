package com.example.bengkel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class LoginActivity extends AppCompatActivity {

//    DatabaseHelper db;
    Button login;
//    EditText email, password;
    TextView register;
    private ImageView back_to_first;
    private FirebaseAuth firebaseAuth;
    private TextInputLayout email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        db = new DatabaseHelper(this);

        firebaseAuth = FirebaseAuth.getInstance();

        email = (TextInputLayout)findViewById(R.id.email2);
        password = (TextInputLayout)findViewById(R.id.password2);
        login = (Button)findViewById(R.id.btn_login);
        back_to_first = (ImageView)findViewById(R.id.back_to_first);
        register = (TextView)findViewById(R.id.go_to_register);

        // go to register
        back_to_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_register = new Intent(LoginActivity.this, FirstPage.class);
                startActivity(to_register);
                finish();
            }
        });

        // go to register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(to_register);
                finish();
            }
        });

        // go to forgot password
//        forgot_pass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent to_forgot = new Intent(LoginActivity.this, RegisterActivity.class);
//                startActivity(to_forgot);
//                finish();
//            }
//        });

        // login
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String strEmail = email.getEditText().getText().toString().trim();
                String strPassword = password.getEditText().getText().toString().trim();
                if(strEmail.equals(""))
                {
                    StyleableToast.makeText(LoginActivity.this, "Masukkan Email", R.style.exampleToast).show();
                }
                else if(strPassword.equals(""))
                {
                    StyleableToast.makeText(LoginActivity.this, "Masukkan Password", R.style.exampleToast).show();
                } else {
                    firebaseAuth.signInWithEmailAndPassword(strEmail,strPassword)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        StyleableToast.makeText(LoginActivity.this, "Login Berhasil", R.style.exampleToast).show();
                                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                    } else {
                                        StyleableToast.makeText(LoginActivity.this, "Login Gagal", R.style.exampleToast).show();
                                    }
                                }
                            });

                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent goLogin = new Intent(LoginActivity.this, FirstPage.class);
        startActivity(goLogin);
        finish();
    }
}