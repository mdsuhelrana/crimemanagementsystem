package com.example.mdsuhelrana.helloandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mdsuhelrana.helloandroid.DataBase.CrimeDBSource;
import com.example.mdsuhelrana.helloandroid.ModelClasses.Signup;
import com.example.mdsuhelrana.helloandroid.R;

public class SignupActivity extends AppCompatActivity {
    private CrimeDBSource dbSource;

    private EditText etName,etPhoneNumber,etEmail,etPassword,etConfirmPassword;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);
        dbSource=new CrimeDBSource(this);
        init();
    }

    private void init() {
        register=findViewById(R.id.register_Id);
        etName=findViewById(R.id.etName_Id);
        etPhoneNumber=findViewById(R.id.etPhonenumber_Id);
        etEmail=findViewById(R.id.etSignupEmail_Id);
        etPassword=findViewById(R.id.etSignupPassword_Id);
        etConfirmPassword=findViewById(R.id.etconfirmPassword_Id);
    }

    public void register(View view) {
        String name = etName.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            etName.setError("must be fillup");
        } else if (TextUtils.isEmpty(phoneNumber)) {
            etPhoneNumber.setError("must be fillup");

        } else if (TextUtils.isEmpty(email)) {
            etEmail.setError("must be fillup");

        } else if (TextUtils.isEmpty(password)) {
            etPassword.setError("must be fillup");

        } else if (TextUtils.isEmpty(confirmPassword)) {
            etConfirmPassword.setError("must be fillup");

        } else {
            if (password.equals(confirmPassword)) {

                Signup signup = new Signup(name, phoneNumber, email, password);
                boolean status = dbSource.inserSignupData(signup);
                if (status) {
                    startActivity(new Intent(this, MainActivity.class));
                }
            } else {
                Toast.makeText(this, "confirm password doesnt matches", Toast.LENGTH_SHORT).show();

            }

        }
    }
}
