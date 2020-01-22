package com.example.student.myapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.student.myapp.R;
import com.example.student.myapp.adapter.DienThoaiAdapter;
import com.example.student.myapp.adapter.LaptopAdapter;
import com.example.student.myapp.model.Sanpham;
import com.example.student.myapp.ultil.CheckConnection;
import com.example.student.myapp.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LapTopActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView lvdt;
    LaptopAdapter laptopAdapter;
    ArrayList<Sanpham> manglt;
    int lddt=0;
    int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_top);
        AnhXa();
        if(CheckConnection.haveNetworkconnection(getApplicationContext())){
            GetIdloaisp();
            ActionTolbar();
            GetData(page);
        } else{
            CheckConnection.ShowToast_Short(getApplicationContext(),"ban hay kiem tra lai internet");
            finish();
        }

    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.duongdandienthoai+String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String Tenlt ="";
                int Gialt=0;
                String Hinhanhlt="";
                String Mota="";
                int idsplt=0;
                if (response!=null&&response.length()!=2){
                    try {
                        JSONArray jsonArray  = new JSONArray(response);
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            Tenlt=jsonObject.getString("tensp");
                            Gialt=jsonObject.getInt("giasp");
                            Hinhanhlt=jsonObject.getString("hinhanhsp");
                            Mota=jsonObject.getString("motasp");
                            idsplt =jsonObject.getInt("idsanpham");
                            manglt.add(new Sanpham(id,Tenlt,Gialt,Hinhanhlt,Mota,idsplt));
                            laptopAdapter.notifyDataSetChanged();//cap nhap lai adapter
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String,String>();
                param.put("idsanpham",String.valueOf(lddt));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionTolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void GetIdloaisp() {
        lddt = getIntent().getIntExtra("idloaisanpham",-1);
        Log.d("giatriloaisp",lddt+"");
    }

    private void AnhXa() {
        toolbar = (Toolbar) findViewById(R.id.toolbarlaptop);
        lvdt = (ListView)findViewById(R.id.listviewlaptop);
        manglt = new ArrayList<>();
        laptopAdapter = new LaptopAdapter(getApplicationContext(),manglt);
        lvdt.setAdapter(laptopAdapter);
    }
}
