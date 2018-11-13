package com.example.mdsuhelrana.helloandroid.AdapterClasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mdsuhelrana.helloandroid.ModelClasses.MissingPeople;
import com.example.mdsuhelrana.helloandroid.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by Md Suhel Rana on 3/23/2018.
 */

public class MissPeopleAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<MissingPeople>list;

    public MissPeopleAdapter(Context context, ArrayList<MissingPeople> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class MissHolder{
        TextView tvName;
        TextView tvAge;
        TextView tvGender;
        TextView tvlastseen;
        TextView tvDate;
        TextView tvDetails;
        TextView tvAddress;
        ImageView imageView;
        TextView missingstaust;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MissHolder holder;
        if (view ==null){
            holder=new MissHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.miss_people_singlerow,viewGroup,false);
            holder.tvName=view.findViewById(R.id.tv_missName_Id);
            holder.tvAge=view.findViewById(R.id.tv_age_Id);
            holder.tvGender =view.findViewById(R.id.tv_gender_Id);
            holder.tvDate=view.findViewById(R.id.tv_Mpdate_Id);
            holder.tvlastseen=view.findViewById(R.id.tv_lastseen_Id);
            holder.tvDetails=view.findViewById(R.id.tv_details_Id);
            holder.tvAddress=view.findViewById(R.id.tv_missAddress_Id);
            holder.imageView=view.findViewById(R.id.missImage_Id);
            holder.missingstaust=view.findViewById(R.id.tv_missingStatus_Id);
            view.setTag(holder);

        }else {
           holder= (MissHolder) view.getTag();
        }
        holder.tvName.setText(list.get(i).getName());
        holder.tvAge.setText(list.get(i).getAge());
        holder.tvGender.setText(list.get(i).getGender());
        holder.tvlastseen.setText(list.get(i).getLastseen());
        holder.tvDate.setText(list.get(i).getDate());
        holder.tvDetails.setText(list.get(i).getDetails());
        holder.tvAddress.setText(list.get(i).getAddrdss());
        holder.missingstaust.setText(list.get(i).getMissingStatus());
        byte[] image=list.get(i).getImage();
        ByteArrayInputStream stream=new ByteArrayInputStream(image);
        Bitmap bitmap= BitmapFactory.decodeStream(stream);
        holder.imageView.setImageBitmap(bitmap);
        return view;
    }
    public void UapdteMissPeople(ArrayList<MissingPeople>list){
        this.list=list;
        notifyDataSetChanged();
    }
}
