package com.aziza.cereal.Adapter;

import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aziza.cereal.Api.ApiRequest;
import com.aziza.cereal.Api.RetrofitServer;
import com.aziza.cereal.Model.DataModel;
import com.aziza.cereal.Model.ResponseDataModel;
import com.aziza.cereal.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ActorViewHolder> {

    List<DataModel> items;
    private Context ctx;
    public TransactionAdapter(List<DataModel> items, Context ctx) {
        this.items = items;
        this.ctx=ctx;


    }

    @Override
    public TransactionAdapter.ActorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_rows_transactions,viewGroup,false);

        TransactionAdapter.ActorViewHolder Actionview = new TransactionAdapter.ActorViewHolder(v);
        return Actionview;
    }

    @Override
    public void onBindViewHolder(TransactionAdapter.ActorViewHolder holder, int position) {
        DataModel dm = items.get(position);
        holder.txtTransaction.setText(dm.getQuantite_trans()+" KG du : "+dm.getType_cereal());
        holder.txtDate.setText("Le : "+dm.getDate_trans());
        holder.dm=dm;

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public  class ActorViewHolder extends RecyclerView.ViewHolder{
        TextView txtTransaction,txtDate;
        DataModel dm;
        public ActorViewHolder(View itemView) {
            super(itemView);

            txtTransaction =  itemView.findViewById(R.id.txtTransaction);
            txtDate =  itemView.findViewById(R.id.txtDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

        }
    }


}
