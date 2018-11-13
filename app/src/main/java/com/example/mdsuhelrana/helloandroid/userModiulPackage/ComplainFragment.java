package com.example.mdsuhelrana.helloandroid.userModiulPackage;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mdsuhelrana.helloandroid.DataBase.CrimeDBSource;
import com.example.mdsuhelrana.helloandroid.ModelClasses.CrimeModel;
import com.example.mdsuhelrana.helloandroid.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplainFragment extends Fragment {
    private Button btnDate,btnSubmit;
    private EditText etLcation,etPostalcode, etCity,etSubject,etComplain;
    private CrimeDBSource dbSource;
    private Calendar calendar;
    private int year,month,day;
    String finaldate;
    //private CmpAdapter adapter;
    //private ArrayList<CrimeModel>models;


    public ComplainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_complain, container, false);
        dbSource=new CrimeDBSource(getContext());
        calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        init(v);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog=new DatePickerDialog(getContext(),
                        datelistener,year,month,day);
                dialog.show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String location=etLcation.getText().toString();
                String postalcode=etPostalcode.getText().toString();
                String city=etCity.getText().toString();
                String subject=etSubject.getText().toString();
                String complain=etComplain.getText().toString();

                CrimeModel model=new CrimeModel(location,postalcode,city,finaldate,subject,complain,"sent");
                boolean status=dbSource.inserData(model);
                if (status){
                    Toast.makeText(getContext(),"sent successfully",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

   return v;
    }

    DatePickerDialog.OnDateSetListener datelistener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            calendar.set(year,month,day);
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            finaldate=sdf.format(calendar.getTime());
            btnDate.setText(finaldate);
        }
    };

    private void init(View v) {
        etLcation=v.findViewById(R.id.location_Id);
        etPostalcode=v.findViewById(R.id.postalcode_Id);
        etCity=v.findViewById(R.id.city_Id);
        etSubject=v.findViewById(R.id.subject_Id);
        etComplain=v.findViewById(R.id.complain_Id);
        btnSubmit=v.findViewById(R.id.btn_submit_Id);
        btnDate=v.findViewById(R.id.date_Id);

    }


}
