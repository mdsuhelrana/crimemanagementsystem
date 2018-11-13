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
import com.example.mdsuhelrana.helloandroid.AdapterClasses.MissPeopleAdapter;
import com.example.mdsuhelrana.helloandroid.ModelClasses.MissingPeople;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminMisspeopleStatusFragment extends Fragment {
    private ListView lv;
    private MissPeopleAdapter adapter;
    private ArrayList<MissingPeople> list;
    private CrimeDBSource dbSource;
    private RadioGroup radioGroup;
    private static final String SEEN="seen";
    private static final String PROCESSING="processing";
    private static final String FINISHED="finished";

    public AdminMisspeopleStatusFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbSource=new CrimeDBSource(getContext());
        list=dbSource.getMissPeopleData();
        View v= inflater.inflate(R.layout.fragment_missingpeople_status, container, false);
        lv=v.findViewById(R.id.miss_lv_Id);
        adapter=new MissPeopleAdapter(getContext(),list);
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
        MissingPeople people=new MissingPeople(seen);
        boolean status= dbSource.updateMissStatus(people,list.get(pos).getId());
        if(status){
            adapter.UapdteMissPeople(list);
        }
    }
}
