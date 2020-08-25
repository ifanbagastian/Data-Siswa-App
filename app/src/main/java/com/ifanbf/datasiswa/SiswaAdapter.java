package com.ifanbf.datasiswa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class SiswaAdapter extends
        RecyclerView.Adapter<SiswaAdapter.MyViewHolder> {
    private List<SiswaModel> modelSiswas;
    Context context;
    public SiswaAdapter(Context context, List<SiswaModel> modelSiswas){
        this.context = context;
        this.modelSiswas = modelSiswas;
    }
    @Override
    public SiswaAdapter.MyViewHolder onCreateViewHolder(ViewGroup
                                                                parent, int viewType){
        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_siswa,
                        parent, false);
        return new MyViewHolder(v);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SiswaAdapter.MyViewHolder holder, int
            position){
        final SiswaModel model = modelSiswas.get(position);
        holder.nis.setText(String.valueOf(model.getNis()));
        holder.nama.setText(model.getNama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),
                        DetailActivity.class);
                intent.putExtra("id", String.valueOf(model.getId()));
                intent.putExtra("nis", String.valueOf(model.getNis()));
                intent.putExtra("nama", model.getNama());
                intent.putExtra("notel",String.valueOf(model.getNotel()));
                intent.putExtra("email", model.getEmail());
                intent.putExtra("alamat", model.getAlamat());
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount(){

        return modelSiswas.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nis, nama;
        public MyViewHolder(View itemView){
            super(itemView);
            nis = itemView.findViewById(R.id.tvNis);
            nama = itemView.findViewById(R.id.tvNama);

        }
    }
}
