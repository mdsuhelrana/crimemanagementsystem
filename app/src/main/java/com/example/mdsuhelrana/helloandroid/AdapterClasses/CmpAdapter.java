package com.example.mdsuhelrana.helloandroid.AdapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mdsuhelrana.helloandroid.ModelClasses.CrimeModel;
import com.example.mdsuhelrana.helloandroid.R;
import java.util.ArrayList;


public class CmpAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CrimeModel>models;

    public CmpAdapter(Context context, ArrayList<CrimeModel> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int i) {
        return models.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class CustomViewHolder{
        TextView tvLocation;
        TextView tvPostalcode;
        TextView tvcity;
        TextView tvSubject;
        TextView tvComplain;
        TextView tvComplainstatus;
        TextView tvDate;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       CustomViewHolder viewHolder;
        if (view==null){
            viewHolder=new CustomViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.complain_single_row,viewGroup,false);
            viewHolder.tvLocation=view.findViewById(R.id.tv_location_Id);
            viewHolder.tvPostalcode=view.findViewById(R.id.tv_postalcode_Id);
            viewHolder.tvcity=view.findViewById(R.id.tv_city_Id);
            viewHolder.tvDate=view.findViewById(R.id.tv_date_Id);
            viewHolder.tvSubject=view.findViewById(R.id.tv_subject_Id);
            viewHolder.tvComplain=view.findViewById(R.id.tv_complain_Id);
            viewHolder.tvComplainstatus=view.findViewById(R.id.tvComplainstaust_Id);
            view.setTag(viewHolder);
        }else {
            viewHolder= (CustomViewHolder) view.getTag();
        }
        viewHolder.tvLocation.setText(models.get(i).getLocation());
        viewHolder.tvPostalcode.setText(models.get(i).getPostalcode());
        viewHolder.tvcity.setText(models.get(i).getCity());
        viewHolder.tvDate.setText(models.get(i).getDate());
        viewHolder.tvSubject.setText(models.get(i).getSubject());
        viewHolder.tvComplain.setText(models.get(i).getComplain());
        viewHolder.tvComplainstatus.setText(models.get(i).getComplainstaus());
        return view;
    }
    public void aupdateAdaper(ArrayList<CrimeModel> models){
        this.models=models;
        notifyDataSetChanged();
    }
}
