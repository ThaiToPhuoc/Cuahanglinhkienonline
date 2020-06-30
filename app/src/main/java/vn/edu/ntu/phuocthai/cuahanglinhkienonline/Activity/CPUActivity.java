package vn.edu.ntu.phuocthai.cuahanglinhkienonline.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.edu.ntu.phuocthai.cuahanglinhkienonline.Adapter.LaptopAdapter;
import vn.edu.ntu.phuocthai.cuahanglinhkienonline.Model.linhkien;
import vn.edu.ntu.phuocthai.cuahanglinhkienonline.R;
import vn.edu.ntu.phuocthai.cuahanglinhkienonline.Util.checkconnect;
import vn.edu.ntu.phuocthai.cuahanglinhkienonline.Util.server;

public class CPUActivity extends AppCompatActivity {
    Toolbar toolbarcpu;
    ListView lvcpu;
    LaptopAdapter cpuAdapter;
    ArrayList<linhkien> cpus;
    int IDLoaiLK = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_p_u);
        addView();
        if(checkconnect.haveNetworkConnection(getApplicationContext())) {
            GetActionbar();
            getdata();
        }
        else {
            checkconnect.ShowToast_short(getApplicationContext(),"Kiểm tra lại kết nối");
            finish();
        }
    }

    private void getdata() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.duongdancpu, new Response.Listener<JSONArray>() {
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
                            cpus.add(new linhkien(ID,TenLinhKien,DonGia,HinhAnh,MoTa,IDLoaiLK));
                            cpuAdapter.notifyDataSetChanged();

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

    private void GetActionbar() {
        setSupportActionBar(toolbarcpu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarcpu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addView() {
        toolbarcpu = findViewById(R.id.tbcpu);
        lvcpu = findViewById(R.id.lvcpu);
        cpus = new ArrayList<>();
        cpuAdapter = new LaptopAdapter(getApplicationContext(),cpus);
        lvcpu.setAdapter(cpuAdapter);
    }

}