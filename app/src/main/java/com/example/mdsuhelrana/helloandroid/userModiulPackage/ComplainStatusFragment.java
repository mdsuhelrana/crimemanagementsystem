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

import com.example.mdsuhelrana.helloandroid.AdapterClasses.CmpAdapter;
import com.example.mdsuhelrana.helloandroid.DataBase.CrimeDBSource;
import com.example.mdsuhelrana.helloandroid.ModelClasses.CrimeModel;
import com.example.mdsuhelrana.helloandroid.R;
import com.example.mdsuhelrana.helloandroid.activities.UpdateActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplainStatusFragment extends Fragment {

    private ListView lv;
    private ArrayList<CrimeModel>models=new ArrayList<>();
    private CrimeDBSource dbSource;
    private CmpAdapter adapter;


    public ComplainStatusFragment() {

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         dbSource=new CrimeDBSource(getContext());
         models=dbSource.getAllData();
         adapter=new CmpAdapter(getContext(),models);
        View v= inflater.inflate(R.layout.fragment_complain_status, container, false);
        lv=v.findViewById(R.id.cmp_lv_Id);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                LayoutInflater inflater1 =LayoutInflater.from(getContext());
                LinearLayout l1 = (LinearLayout) inflater1.inflate(R.layout.dialog_view,null,false);
                builder.setView(l1);
                final Button delete=l1.findViewById(R.id.delete_Id);
                final Button update=l1.findViewById(R.id.update_Id);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder1=new AlertDialog.Builder(getContext());
                        builder1.setMessage("do you want to delete this");
                        builder1.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                boolean status=dbSource.deleteData(models.get(pos).getId());
                                if (status){
                                    models.remove(pos);
                                    adapter.aupdateAdaper(models);
                                    builder.setCancelable(true);
                                    Toast.makeText(getContext(),"data has been delete",Toast.LENGTH_SHORT).show();
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
                        Intent intent=new Intent(getContext(), UpdateActivity.class);
                        intent.putExtra("location",models.get(pos).getLocation());
                        intent.putExtra("postalcode",models.get(pos).getPostalcode());
                        intent.putExtra("city",models.get(pos).getCity());
                        intent.putExtra("date",models.get(pos).getDate());
                        intent.putExtra("subject",models.get(pos).getSubject());
                        intent.putExtra("complain",models.get(pos).getComplain());
                        intent.putExtra("complainstatus",models.get(pos).getComplainstaus());
                        intent.putExtra("key",models.get(pos).getId());
                        startActivity(intent);
                    }
                });
                builder.create();
                builder.show();
            }
        });
        return v;
    }

}
