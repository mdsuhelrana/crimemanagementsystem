package com.example.mdsuhelrana.helloandroid.userModiulPackage;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mdsuhelrana.helloandroid.AdapterClasses.MissPeopleAdapter;
import com.example.mdsuhelrana.helloandroid.DataBase.CrimeDBSource;
import com.example.mdsuhelrana.helloandroid.ModelClasses.MissingPeople;
import com.example.mdsuhelrana.helloandroid.activities.PeopleUpdateActivity;
import com.example.mdsuhelrana.helloandroid.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MissingPeopleStatusFragment extends Fragment {

    private ArrayList<MissingPeople>list;
    private CrimeDBSource dbSource;
    private ListView lv;
    private MissPeopleAdapter adapter;
    public MissingPeopleStatusFragment() {
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_missingpeople_status, container, false);
        dbSource=new CrimeDBSource(getContext());
        list=dbSource.getMissPeopleData();
        adapter=new MissPeopleAdapter(getContext(),list);
        lv=view.findViewById(R.id.miss_lv_Id);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                LayoutInflater inflater1=LayoutInflater.from(getContext());
                LinearLayout l1= (LinearLayout) inflater1.inflate(R.layout.dialog_view,null,false);
                builder.setView(l1);
                final Button delete=l1.findViewById(R.id.delete_Id);
                final Button update=l1.findViewById(R.id.update_Id);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder1=new AlertDialog.Builder(getContext());
                        builder1.setMessage("do you want to delete");
                        builder1.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                boolean status=dbSource.deleteMissPeople(list.get(pos).getId());
                                if(status){
                                    list.remove(pos);
                                    adapter.UapdteMissPeople(list);
                                    builder.setCancelable(true);
                                    Toast.makeText(getContext(),"data has been deleted",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder1.setNegativeButton("cancel",null);
                        builder1.create();
                        builder1.show();
                    }
                });
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getContext(), PeopleUpdateActivity.class);
                        intent.putExtra("id",list.get(pos).getId());
                        intent.putExtra("pos",pos);
                        startActivity(intent);
                    }
                });
                builder.create();
                builder.show();
            }
        });
        return view;
    }
}
