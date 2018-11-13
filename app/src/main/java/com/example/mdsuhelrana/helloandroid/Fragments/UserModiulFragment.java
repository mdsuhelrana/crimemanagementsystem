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

import com.example.mdsuhelrana.helloandroid.DataBase.CrimeDBSource;
import com.example.mdsuhelrana.helloandroid.ModelClasses.Signup;
import com.example.mdsuhelrana.helloandroid.R;
import com.example.mdsuhelrana.helloandroid.activities.SignupActivity;
import com.example.mdsuhelrana.helloandroid.activities.UserModiulActivity;

import java.util.ArrayList;


public class UserModiulFragment extends Fragment {
    private CrimeDBSource dbSource;
    private ArrayList<Signup>list=new ArrayList<>();
    private Button login;
    private Button signup;
    private EditText etemail;
    private EditText etpassword;


    public UserModiulFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbSource=new CrimeDBSource(getContext());
        list=dbSource.getSignupData();
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_user_modiul, container, false);
        init(v);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=etemail.getText().toString();
                String password=etpassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    etemail.setError("must be fill up");
                }else if (TextUtils.isEmpty(password)){
                    etpassword.setError("must be fill up");
                }else {
                    if(list!=null){
                        for(int i=0; i<list.size(); i++){
                            if(password.equals(list.get(i).getPassword()) && email.equals(list.get(i).getEmail())){
                                startActivity(new Intent(getContext(),UserModiulActivity.class));
                            }
                        }
                    }
                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SignupActivity.class));
            }
        });
        return v;
    }

    private void init(View v) {
        login=v.findViewById(R.id.btn_user_login_Id);
        signup=v.findViewById(R.id.btn_user_signup_Id);
        etemail=v.findViewById(R.id.login_email_Id);
       etpassword=v.findViewById(R.id.login_password_Id);
    }

}
