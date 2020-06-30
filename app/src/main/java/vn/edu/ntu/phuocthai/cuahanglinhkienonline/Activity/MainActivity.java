package vn.edu.ntu.phuocthai.cuahanglinhkienonline.Activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.edu.ntu.phuocthai.cuahanglinhkienonline.Adapter.linhkienAdapter;
import vn.edu.ntu.phuocthai.cuahanglinhkienonline.Adapter.loailinhkienAdapter;
import vn.edu.ntu.phuocthai.cuahanglinhkienonline.Model.linhkien;
import vn.edu.ntu.phuocthai.cuahanglinhkienonline.Model.loailinhkien;
import vn.edu.ntu.phuocthai.cuahanglinhkienonline.R;
import vn.edu.ntu.phuocthai.cuahanglinhkienonline.Util.checkconnect;
import vn.edu.ntu.phuocthai.cuahanglinhkienonline.Util.server;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper flipper;
    RecyclerView recyclerView;
    NavigationView navView;
    ListView lvmhchinh;
    DrawerLayout drawerLayoutmhchinh;

    ArrayList<loailinhkien> listloailk;
    loailinhkienAdapter adapter;
    int ID =  0;
    String Tenloai = "", HinhAnh = "";

    ArrayList<linhkien> linhkiens;
    linhkienAdapter linhkienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
        if(checkconnect.haveNetworkConnection(getApplicationContext()))
        {
            actionbar();
            actionViewFliper();
            getloailinhkien();
            getlinhkienmoinhat();
            CatchOnItemListView();
        }
        else
        {
            checkconnect.ShowToast_short(getApplicationContext(),"Kiểm tra lại kết nối!");
            finish();
        }
    }

    private void CatchOnItemListView() {
        lvmhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        if(checkconnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            checkconnect.ShowToast_short(getApplicationContext(),"Kiểm tra lại kết nối");
                        }
                        drawerLayoutmhchinh.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(checkconnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,LapTopActivity.class);
                            intent.putExtra("IDLoaiLK",listloailk.get(i).getID());
                            startActivity(intent);
                        }
                        else
                        {
                            checkconnect.ShowToast_short(getApplicationContext(),"Kiểm tra lại kết nối");
                        }
                        drawerLayoutmhchinh.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(checkconnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,CPUActivity.class);
                            intent.putExtra("IDLoaiLK",listloailk.get(i).getID());
                            startActivity(intent);
                        }
                        else
                        {
                            checkconnect.ShowToast_short(getApplicationContext(),"Kiểm tra lại kết nối");
                        }
                        drawerLayoutmhchinh.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(checkconnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,RAMActivity.class);
                            intent.putExtra("IDLoaiLK",linhkiens.get(i).getID());
                            startActivity(intent);
                        }
                        else
                        {
                            checkconnect.ShowToast_short(getApplicationContext(),"Kiểm tra lại kết nối");
                        }
                        drawerLayoutmhchinh.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(checkconnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,LienheActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            checkconnect.ShowToast_short(getApplicationContext(),"Kiểm tra lại kết nối");
                        }
                        drawerLayoutmhchinh.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if(checkconnect.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,ThongtinActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            checkconnect.ShowToast_short(getApplicationContext(),"Kiểm tra lại kết nối");
                        }
                        drawerLayoutmhchinh.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void getlinhkienmoinhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.duongdanlk, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int ID = 0;
                    String TenLinhKien = "";
                    int DonGia = 0;
                    String HinhAnh = "";
                    String MoTa = "";
                    int IDLoaiLK = 0;
                    for(int i = 0; i < response.length(); i++)
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("ID");
                            TenLinhKien = jsonObject.getString("TenLinhKien");
                            DonGia = jsonObject.getInt("DonGia");
                            HinhAnh = jsonObject.getString("HinhAnh");
                            MoTa = jsonObject.getString("MoTa");
                            IDLoaiLK = jsonObject.getInt("IDLoaiLK");
                            linhkiens.add(new linhkien(ID,TenLinhKien,DonGia,HinhAnh,MoTa,IDLoaiLK));
                            linhkienAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                checkconnect.ShowToast_short(getApplicationContext(), error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void getloailinhkien() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.duongdanloailk, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null)
                {
                    for(int i = 0; i < response.length(); i++)
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("ID");
                            Tenloai = jsonObject.getString("Tenloai");
                            HinhAnh = jsonObject.getString("HinhAnh");
                            listloailk.add(new loailinhkien(ID, Tenloai,HinhAnh));
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    listloailk.add(4,new loailinhkien(0,"Liên hệ",
                            "https://img.pngio.com/phone-icon-free-download-png-and-vector-telephone-call-png-938_981.jpg"));
                    listloailk.add(5,new loailinhkien(0,"Thông tin",
                            "https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Minimalist_info_Icon.png/600px-Minimalist_info_Icon.png"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                checkconnect.ShowToast_short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);

    }


    private void addView() {
        toolbar = findViewById(R.id.tbarmhchinh);
        flipper = findViewById(R.id.ViewFlipper);
        recyclerView = findViewById(R.id.rvnoibat);
        navView = findViewById(R.id.navViewmhchinh);
        lvmhchinh = findViewById(R.id.listviewmhchinh);
        drawerLayoutmhchinh = findViewById(R.id.drLayoutmhchinh);
        listloailk = new ArrayList<>();
        listloailk.add(0,new loailinhkien(0,"Trang chủ",
                "https://cdn.nhanh.vn/cdn/store/25203/menu/icon_8282_1542730112_home.png"));
        adapter = new loailinhkienAdapter(listloailk,getApplicationContext());
        lvmhchinh.setAdapter(adapter);

        linhkiens = new ArrayList<>();
        linhkienAdapter = new linhkienAdapter(getApplicationContext(),linhkiens);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(linhkienAdapter);
    }


    private void actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayoutmhchinh.openDrawer(GravityCompat.START);
            }
        });
    }


    private void actionViewFliper() {
        ArrayList<String> quangcao = new ArrayList<>();
        quangcao.add("https://image.thanhnien.vn/660/uploaded/dieutrang.qc/2018_08_03/phong%20vu/hinh2_sboc.png");
        quangcao.add("https://suamaytinhpci.com/wp-content/uploads/2020/01/20/18/pc-tim-hieu-cach-xay-dung-may-tinh-cho-nguoi-moi-bat-dau-build-pc-1.png");
        quangcao.add("https://suamaytinhpci.com/wp-content/uploads/2020/01/20/18/pc-tim-hieu-cach-xay-dung-may-tinh-cho-nguoi-moi-bat-dau-build-pc-8.jpg");
        quangcao.add("https://suamaytinhpci.com/wp-content/uploads/2020/01/20/18/goi-y-build-pc-lam-do-hoa-trong-tam-gia-30-trieu-dong-tot-nhat-9.png");
        for (int i = 0; i < quangcao.size(); i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(quangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            flipper.addView(imageView);
        }
        flipper.setFlipInterval(6000);
        flipper.setAutoStart(true);
        Animation slidein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.left_to_right);
        Animation slideout = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out);
        flipper.setInAnimation(slidein);
        flipper.setOutAnimation(slideout);
    }
}