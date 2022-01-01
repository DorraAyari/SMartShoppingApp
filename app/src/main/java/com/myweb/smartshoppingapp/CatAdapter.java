package com.myweb.smartshoppingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.myweb.smartshoppingapp.Model.category;

import java.util.List;


public class CatAdapter extends ArrayAdapter<category> {

    private Context context;
    private List<category> categories;

    public CatAdapter(@NonNull Context context, int resource, @NonNull List<category> objects) {
        super(context, resource, objects);
        this.context=context;
        this.categories =objects;
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.content_cat,parent,false);

        TextView txtidPersona=(TextView)rowView.findViewById(R.id.idcat);
        TextView txtNombre=(TextView)rowView.findViewById(R.id.nomCat);;

        txtidPersona.setText(String.format("ID:%d", categories.get(position).getidcat()));
        txtNombre.setText(String.format("NOMCAT:%s", categories.get(position).getnomCat()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(context, CatActivity.class);
               intent.putExtra("ID CATEGORY:",String.valueOf(categories.get(position).getidcat()));
               intent.putExtra("NAME CATEGORY:", categories.get(position).getnomCat());

               context.startActivity(intent);
            }
        });
        return rowView;

    }

}
