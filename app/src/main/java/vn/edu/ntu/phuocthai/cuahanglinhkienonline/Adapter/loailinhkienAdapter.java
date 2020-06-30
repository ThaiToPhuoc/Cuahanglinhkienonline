package vn.edu.ntu.phuocthai.cuahanglinhkienonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.edu.ntu.phuocthai.cuahanglinhkienonline.Model.loailinhkien;
import vn.edu.ntu.phuocthai.cuahanglinhkienonline.R;

public class loailinhkienAdapter extends BaseAdapter {

    ArrayList<loailinhkien>  listloailk;
    Context context;

    public loailinhkienAdapter(ArrayList<loailinhkien> listloailk, Context context) {
        this.listloailk = listloailk;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listloailk.size();
    }

    @Override
    public Object getItem(int i) {
        return listloailk.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder = null;
        if(view == null)
        {
            viewholder = new Viewholder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.loailinhkien,null);
            viewholder.txtloailk = view.findViewById(R.id.txtloailk);
            viewholder.imgloailk = view.findViewById(R.id.imgloailk);
            view.setTag(viewholder);
        }
        else
        {
            viewholder = (Viewholder) view.getTag();
        }
        loailinhkien loailk = (loailinhkien) getItem(i);
        viewholder.txtloailk.setText(loailk.Tenloai);
        Picasso.with(context).load(loailk.getHinhAnh()).placeholder(R.drawable.ic_action_loading).error(R.drawable.ic_action_erroe)
                .into(viewholder.imgloailk);
        return view;
    }

    public class Viewholder
    {
        TextView txtloailk;
        ImageView imgloailk;

    }
}
