package vn.edu.ntu.phuocthai.cuahanglinhkienonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.ntu.phuocthai.cuahanglinhkienonline.Model.linhkien;
import vn.edu.ntu.phuocthai.cuahanglinhkienonline.R;

public class linhkienAdapter extends RecyclerView.Adapter<linhkienAdapter.linhkienViewholder> {
    Context context;
    ArrayList<linhkien> linhkiens = new ArrayList<>();

    public linhkienAdapter(Context context, ArrayList<linhkien> linhkiens) {
        this.context = context;
        this.linhkiens = linhkiens;
    }

    @NonNull
    @Override
    public linhkienViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linhkienmoinhat,null);
        linhkienViewholder linhkienViewholder = new linhkienViewholder(view);
        return linhkienViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull linhkienViewholder holder, int position) {
        linhkien linhkien = linhkiens.get(position);
        holder.bind(linhkien);
    }

    @Override
    public int getItemCount() {
        return linhkiens.size();
    }

    public class linhkienViewholder extends RecyclerView.ViewHolder{
        public ImageView imglinhkien;
        public TextView txttenlk, txtgialk;

        public linhkienViewholder(@NonNull View itemView) {
            super(itemView);
            imglinhkien = itemView.findViewById(R.id.imglinhkien);
            txttenlk = itemView.findViewById(R.id.txttenlk);
            txtgialk = itemView.findViewById(R.id.txtgialk);
        }

        public void bind(linhkien linhkien) {
            txttenlk.setText(linhkien.TenLinhKien.toString());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            txtgialk.setText("Giá: "+ decimalFormat.format(linhkien.getDonGia())+ "VNĐ");
            Picasso.with(context).load(linhkien.getHinhAnh()).placeholder(R.drawable.ic_action_loading)
                    .error(R.drawable.ic_action_erroe)
                    .into(imglinhkien);
        }
    }

}
