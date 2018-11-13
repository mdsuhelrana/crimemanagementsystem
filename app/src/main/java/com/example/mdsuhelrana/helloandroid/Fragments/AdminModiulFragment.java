package com.example.mdsuhelrana.helloandroid.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mdsuhelrana.helloandroid.activities.AdminModiulActivity;
import com.example.mdsuhelrana.helloandroid.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminModiulFragment extends Fragment {
    private Button login;
    private static String EMALI="admin@gmail.com";
    private static String PASSWORD="admin123";
    private EditText etMail,etPassword;

    public AdminModiulFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_admin_modiul, container, false);
        init(v);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emil=etMail.getText().toString();
                String password=etPassword.getText().toString();
                if(TextUtils.isEmpty(emil)){
                    etMail.setError("must be fill up");
                }else if (TextUtils.isEmpty(password)){
                    etPassword.setError("must be fill up");
                }else {
                    if (EMALI .equals(emil) && PASSWORD.equals(password)) {
                        startActivity(new Intent(getContext(), AdminModiulActivity.class));
                    }else {
                        Toast.makeText(getContext(),"wronf email and password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return v;
    }

    private void init(View v) {
        login=v.findViewById(R.id.admin_login_Id);
        etMail=v.findViewById(R.id.admin_login_email_Id);
        etPassword=v.findViewById(R.id.admin_login_password_Id);
    }
}
