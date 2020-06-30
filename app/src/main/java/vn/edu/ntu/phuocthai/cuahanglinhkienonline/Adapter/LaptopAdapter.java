package vn.edu.ntu.phuocthai.cuahanglinhkienonline.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.ntu.phuocthai.cuahanglinhkienonline.Model.linhkien;
import vn.edu.ntu.phuocthai.cuahanglinhkienonline.R;

public class LaptopAdapter extends BaseAdapter {
    Context context;
    ArrayList<linhkien> laptops;

    public LaptopAdapter(Context context, ArrayList<linhkien> laptops) {
        this.context = context;
        this.laptops = laptops;
    }

    @Override
    public int getCount() {
        return laptops.size();
    }

    @Override
    public Object getItem(int i) {
        return laptops.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewholder viewholder = null;
        if(view == null)
        {
            viewholder = new viewholder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.laptop,null);
            viewholder.txttenlaptop = view.findViewById(R.id.txttenlaptop);
            viewholder.txtgialaptop = view.findViewById(R.id.txtgialaptop);
            viewholder.txtmotalaptop = view.findViewById(R.id.txtmotalaptop);
            viewholder.imglaptop = view.findViewById(R.id.imglaptop);
            view.setTag(viewholder);
        }
        else
        {
            viewholder = (viewholder) view.getTag();
        }
        linhkien linhkien = (linhkien) getItem(i);
        viewholder.txttenlaptop.setText(linhkien.getTenLinhKien().toString());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewholder.txtgialaptop.setText("Giá: "+ decimalFormat.format(linhkien.getDonGia())+ "VNĐ");
        viewholder.txtmotalaptop.setMaxLines(2);
        viewholder.txtmotalaptop.setEllipsize(TextUtils.TruncateAt.END);
        viewholder.txtmotalaptop.setText(linhkien.getMoTa().toString());
        Picasso.with(context).load(linhkien.getHinhAnh()).placeholder(R.drawable.ic_action_loading).error(R.drawable.ic_action_erroe)
                .into(viewholder.imglaptop);
        return view;
    }

    public class viewholder
    {
        public TextView txttenlaptop, txtgialaptop,txtmotalaptop;
        public ImageView imglaptop;
    }
}
