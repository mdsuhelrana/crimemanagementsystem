package com.example.mdsuhelrana.helloandroid.activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mdsuhelrana.helloandroid.DataBase.CrimeDBSource;
import com.example.mdsuhelrana.helloandroid.ModelClasses.CrimeModel;
import com.example.mdsuhelrana.helloandroid.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etLocation,etPostalcode,etCity,etSubject,etComplain;
    private CrimeDBSource dbSource;
    private int rowId;
    private String complainstatus;
    private Button btnDate,btnSubmit;
    private Calendar calendar;
    private int year,month,day;
    private String finaldate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_complain);
        dbSource=new CrimeDBSource(this);
        String location= getIntent().getStringExtra("location");
       String postalcode= getIntent().getStringExtra("postalcode");
       String city= getIntent().getStringExtra("city");
       String date= getIntent().getStringExtra("date");
       String subject= getIntent().getStringExtra("subject");
       String complain= getIntent().getStringExtra("complain");
       complainstatus=getIntent().getStringExtra("complainstatus");
       rowId=getIntent().getIntExtra("key",0);

       calendar=Calendar.getInstance();
       year=calendar.get(Calendar.YEAR);
       month=calendar.get(Calendar.MONTH);
       day=calendar.get(Calendar.DAY_OF_MONTH);

       init();
       etLocation.setText(location);
       etPostalcode.setText(postalcode);
       etCity.setText(city);
       btnDate.setText(date);
       etSubject.setText(subject);
       etComplain.setText(complain);

       btnSubmit.setOnClickListener(this);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog=new DatePickerDialog(UpdateActivity.this,
                        listener,year,month,day);
                dialog.show();
            }
        });
    }

    DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
           calendar.set(year,month,day);
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            finaldate=sdf.format(calendar.getTime());
            btnDate.setText(finaldate);
        }
    };


    private void init() {
        etLocation=findViewById(R.id.location_Id);
        etPostalcode=findViewById(R.id.postalcode_Id);
        etCity=findViewById(R.id.city_Id);
        etSubject=findViewById(R.id.subject_Id);
        etComplain=findViewById(R.id.complain_Id);
        btnSubmit=findViewById(R.id.btn_submit_Id);
        btnDate=findViewById(R.id.date_Id);
    }

    @Override
    public void onClick(View view) {
        String location=etLocation.getText().toString();
        String postalcode=etPostalcode.getText().toString();
        String city=etCity.getText().toString();
        String subject=etSubject.getText().toString();
        String complain=etSubject.getText().toString();
        CrimeModel crimeModel=new CrimeModel(location,postalcode,city,finaldate,subject,complain,complainstatus);
        boolean status=dbSource.updateData(crimeModel,rowId);
        if(status){
            Toast.makeText(this,"updated success",Toast.LENGTH_SHORT).show();
        }
    }
}
