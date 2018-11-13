package com.example.mdsuhelrana.helloandroid.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.mdsuhelrana.helloandroid.DataBase.CrimeDBSource;
import com.example.mdsuhelrana.helloandroid.ModelClasses.Age;
import com.example.mdsuhelrana.helloandroid.ModelClasses.MissingPeople;
import com.example.mdsuhelrana.helloandroid.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class PeopleUpdateActivity extends AppCompatActivity implements View.OnClickListener {
    private int rowId;
    private int position;
    private ArrayList<MissingPeople>list;
    private CrimeDBSource dbSource;
    private EditText etName,etLastseen,etDetails,etAddress;
    private ImageView imageView;
    private Button takePhoto,btnSubmit,btnDate;
    private static final int REQUEST_CODE_IMAGE=1;
    private static final int REQUEST_CODE_GALLERY=2;

    private Calendar calendar;
    private int year,month,day;
    private String finaldate;
    private RadioGroup radioGroup;
    private String gender=null;
    private String age=null;

    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_missingpeople);
        rowId=getIntent().getIntExtra("id",0);
        position=getIntent().getIntExtra("pos",0);
        dbSource=new CrimeDBSource(this);
         list=dbSource.getMissPeopleData();
        calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);

       init();
       ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,
               android.R.layout.simple_spinner_dropdown_item, Age.getAges());
       sp.setAdapter(adapter);
       sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               age=Age.getAges().get(i);
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });


       etName.setText(list.get(position).getName());
       etLastseen.setText(list.get(position).getLastseen());
       btnDate.setText(list.get(position).getDate());
       etDetails.setText(list.get(position).getDetails());
       etAddress.setText(list.get(position).getAddrdss());
        byte[] image=list.get(position).getImage();
        ByteArrayInputStream stream=new ByteArrayInputStream(image);
        Bitmap bitmap=BitmapFactory.decodeStream(stream);
        imageView.setImageBitmap(bitmap);
        takePhoto.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnDate.setOnClickListener(this);
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
    }

    private void init() {
        etName=findViewById(R.id.et_missPeoplename_Id);
        etLastseen=findViewById(R.id.et_laseen_Id);
        etDetails=findViewById(R.id.et_details_Id);
        etAddress=findViewById(R.id.et_address_Id);
        imageView=findViewById(R.id.photo_Id);
        takePhoto =findViewById(R.id.capture_Id);
        btnSubmit=findViewById(R.id.add_Id);
        btnDate=findViewById(R.id.btn_Mpdate_Id);
        radioGroup=findViewById(R.id.gender_rg_Id);
        sp=findViewById(R.id.spinner_Id);
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


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.capture_Id:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                final LayoutInflater inflater=LayoutInflater.from(this);
                View v=inflater.inflate(R.layout.take_photo_dialogview,null,false);
                final Button capture=v.findViewById(R.id.dialog_capture_Id);
                final Button chose=v.findViewById(R.id.dialog_gellary_Id);
                builder.setView(v);
                capture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,REQUEST_CODE_IMAGE);
                    }
                });
                chose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent goGallery=new Intent(Intent.ACTION_PICK);
                        goGallery.setType("image/*");
                        startActivityForResult(goGallery,REQUEST_CODE_GALLERY);
                    }
                });
                builder.create();
                builder.show();

                break;
            case R.id.btn_Mpdate_Id:
                DatePickerDialog dialog=new DatePickerDialog(this,
                        listener,year,month,day);
                dialog.show();
                break;
            case R.id.add_Id:
                String name=etName.getText().toString();
                //String age=etAge.getText().toString();
                String lastseen=etLastseen.getText().toString();
                String details=etDetails.getText().toString();
                String address=etAddress.getText().toString();
                MissingPeople missingPeople=new MissingPeople(name,age,gender
                        ,address,lastseen,details,imagetoByte(imageView)
                        ,list.get(position).getMissingStatus(),finaldate);
                boolean status=dbSource.updateMissPeople(missingPeople,rowId);
                 if(status){
                     startActivity(new Intent(PeopleUpdateActivity.this, UserModiulActivity.class));
                 }
                break;
        }
    }

    private byte[] imagetoByte(ImageView imageView) {
        Bitmap bitmap= ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytes=stream.toByteArray();
        return bytes;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_CODE_IMAGE && resultCode==RESULT_OK){

           Uri uri= data.getData();
            InputStream stream= null;
            try {
                stream = getContentResolver().openInputStream(uri);
                Bitmap bitmap=BitmapFactory.decodeStream(stream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            if (requestCode==REQUEST_CODE_GALLERY && resultCode==RESULT_OK && data!=null){
                Uri uri=data.getData();

                try {
                    InputStream stream=getContentResolver().openInputStream(uri);
                    Bitmap bitmap=BitmapFactory.decodeStream(stream);
                    imageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
