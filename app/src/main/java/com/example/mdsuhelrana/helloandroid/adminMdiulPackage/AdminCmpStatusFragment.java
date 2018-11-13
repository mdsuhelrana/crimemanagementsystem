package com.example.mdsuhelrana.helloandroid.adminMdiulPackage;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mdsuhelrana.helloandroid.DataBase.CrimeDBSource;
import com.example.mdsuhelrana.helloandroid.R;
import com.example.mdsuhelrana.helloandroid.AdapterClasses.CmpAdapter;
import com.example.mdsuhelrana.helloandroid.ModelClasses.CrimeModel;

import java.util.ArrayList;

public class AdminCmpStatusFragment extends Fragment {
    private ListView lv;
    private CmpAdapter adapter;
    private ArrayList<CrimeModel> models;
    private CrimeDBSource dbSource;
    private RadioGroup radioGroup;
    private static final String SEEN="seen";
    private static final String PROCESSING="processing";
    private static final String FINISHED="finished";
    public AdminCmpStatusFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbSource=new CrimeDBSource(getContext());
        models=dbSource.getAllData();
        View v= inflater.inflate(R.layout.fragment_complain_status, container, false);
        lv=v.findViewById(R.id.cmp_lv_Id);
        adapter=new CmpAdapter(getContext(),models);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                LayoutInflater inflater1=LayoutInflater.from(getContext());
                LinearLayout l1= (LinearLayout) inflater1.inflate(R.layout.dialog_status,null,false);
                radioGroup=l1.findViewById(R.id.radiogroup_Id);
                builder.setView(l1);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                        switch (checkId){
                            case R.id.seen_Id:
                                statusUpdate(SEEN,pos);
                                break;
                            case R.id.processing_Id:
                                statusUpdate(PROCESSING,pos);
                                break;
                            case R.id.finished_Id:
                                statusUpdate(FINISHED,pos);
                                break;
                        }
                    }
                });
                builder.create();
                builder.show();
            }
        });
        return v;
    }
    private void statusUpdate(String seen,int pos) {
        CrimeModel model=new CrimeModel(seen);
        boolean status= dbSource.updateStatus(model,models.get(pos).getId());
        if(status){
            adapter.aupdateAdaper(models);
        }
    }

}
