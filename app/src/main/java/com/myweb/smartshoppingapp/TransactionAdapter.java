package com.myweb.smartshoppingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.myweb.smartshoppingapp.Model.Transaction;

import java.util.List;

public class TransactionAdapter extends ArrayAdapter<Transaction> {

    private Context context;
    private  List<Transaction> transactions;

    public TransactionAdapter(@NonNull Context context, int resource, @NonNull List<Transaction> objects) {
        super(context, resource, objects);
        this.context=context;
        this.transactions =objects;
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.content_main,parent,false);

        TextView txtidPersona=(TextView)rowView.findViewById(R.id.idtrans);
        TextView txtidcat=(TextView)rowView.findViewById(R.id.idcategory);
        TextView txtApellidos=(TextView)rowView.findViewById(R.id.description);;
        TextView txtmontant=(TextView)rowView.findViewById(R.id.montant);;
        TextView txtDay=(TextView)rowView.findViewById(R.id.day);;
        TextView txtNombre=(TextView)rowView.findViewById(R.id.nomCat);;
        TextView txtcheckBox1= (TextView) rowView.findViewById(R.id.txtcheckBox1);;
        TextView txtcheckBox2= (TextView) rowView.findViewById(R.id.txtcheckBox2);;
        TextView txtcheckBox3= (TextView) rowView.findViewById(R.id.txtcheckBox3);;

        txtidPersona.setText(String.format("ID:%d", transactions.get(position).getId()));
        txtidcat.setText(String.format("ID CATEGORY:%d", transactions.get(position).getIdcategoryy()));
        txtApellidos.setText(String.format("DESCRIPTION: %s", transactions.get(position).getDescription()));
        txtmontant.setText(String.format("PRICE: %s","$ "+ transactions.get(position).getMontant()));
        txtDay.setText(String.format("DAY:%s",transactions.get(position).getDay()));
        txtcheckBox1.setText(String.format("TYPE:%s",transactions.get(position).getType()));
        txtNombre.setText(String.format("NAME CATEGORY:%s", transactions.get(position).getnomCat()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, TransactionActivity.class);
                intent.putExtra("ID",String.valueOf(transactions.get(position).getId()));
                intent.putExtra("ID CAT",String.valueOf(transactions.get(position).getIdcategoryy()));
                intent.putExtra("DESCRIPTION", transactions.get(position).getDescription());
                intent.putExtra("MONTANT", "$ "+transactions.get(position).getMontant());
                intent.putExtra("DAY",transactions.get(position).getDay());
                intent.putExtra("TYPE",transactions.get(position).getType());
                intent.putExtra("NOMCAT", transactions.get(position).getnomCat());

                context.startActivity(intent);
            }
        });
        return rowView;

    }

}
