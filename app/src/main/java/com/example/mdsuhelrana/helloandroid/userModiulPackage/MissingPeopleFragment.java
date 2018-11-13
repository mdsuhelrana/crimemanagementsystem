package com.example.mdsuhelrana.helloandroid.userModiulPackage;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mdsuhelrana.helloandroid.ModelClasses.Age;
import com.example.mdsuhelrana.helloandroid.DataBase.CrimeDBSource;
import com.example.mdsuhelrana.helloandroid.ModelClasses.MissingPeople;
import com.example.mdsuhelrana.helloandroid.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class MissingPeopleFragment extends Fragment {
    private EditText etName,etAddress,etLastseen,etDetails;
    private ImageView imageView;
    private Button takePhoto,btnSubmit,btnDate;
    private static final int REQUEST_CODE_GALLARY=999;
    private static final int REQUEST_CODE_CAMERA=1;
    private RadioGroup radioGroup;
    private CrimeDBSource dbSource;
    private Spinner sp;


    private Calendar calendar;
    private int year,month,day;
    private String finaldate;
    private String gender=null;
    private String age=null;

    public MissingPeopleFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbSource=new CrimeDBSource(getContext());
        final View v= inflater.inflate(R.layout.fragment_missingpeople, container, false);
        calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        init(v);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, Age.getAges());
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //age=adapterView.getSelectedItem().toString();
                age=Age.getAges().get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch (checkId){
                    case R.id.male_Id:
                        gender="male";
                        break;
                    case R.id.female_Id:
                        gender="female";
                        break;
                }

            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog=new DatePickerDialog(getContext(),
                        listener,year,month,day);
                dialog.show();
            }
        });

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                LayoutInflater inflater1=LayoutInflater.from(getContext());
                final View view1=inflater1.inflate(R.layout.take_photo_dialogview,null,false);
                final Button capure=view1.findViewById(R.id.dialog_capture_Id);

                final Button delete=view1.findViewById(R.id.dialog_gellary_Id);
                builder.setView(view1);
                capure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_CODE_CAMERA);
                    }
                });

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       Intent goGallery=new Intent(Intent.ACTION_PICK);
                       goGallery.setType("image/*");
                       startActivityForResult(goGallery,REQUEST_CODE_GALLARY);
                    }
                });
                builder.create();
                builder.show();


            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lastseen=etLastseen.getText().toString();
                String details=etDetails.getText().toString();
                String name=etName.getText().toString();
                String address=etAddress.getText().toString();
                MissingPeople missingPeople=new MissingPeople(name,age,gender,address,
                        lastseen,details,imageTobyte(imageView),"sent",finaldate);

                boolean status=dbSource.insertMissPeople(missingPeople);
                if (status){
                    Toast.makeText(getContext(),"sent successfully",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE_CAMERA && resultCode==getActivity().RESULT_OK && data!=null){
           Uri uri= data.getData();
            try {
                InputStream stream=getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap=BitmapFactory.decodeStream(stream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else if(requestCode==REQUEST_CODE_GALLARY && resultCode==getActivity().RESULT_OK && data !=null){
            Uri uri=data.getData();
            try {
                InputStream stream= getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap=BitmapFactory.decodeStream(stream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(View v) {
        etLastseen=v.findViewById(R.id.et_laseen_Id);
        etDetails=v.findViewById(R.id.et_details_Id);
        etName=v.findViewById(R.id.et_missPeoplename_Id);
        etAddress=v.findViewById(R.id.et_address_Id);
        imageView=v.findViewById(R.id.photo_Id);
        takePhoto =v.findViewById(R.id.capture_Id);
        btnSubmit=v.findViewById(R.id.add_Id);
        btnDate=v.findViewById(R.id.btn_Mpdate_Id);
        radioGroup=v.findViewById(R.id.gender_rg_Id);
        sp=v.findViewById(R.id.spinner_Id);
    }


    private byte[] imageTobyte(ImageView imageView) {
        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        byte[] byteArray=outputStream.toByteArray();
        return byteArray;
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

}

