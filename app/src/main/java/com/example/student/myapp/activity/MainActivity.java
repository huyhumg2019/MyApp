package com.example.student.myapp.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.student.myapp.R;
import com.example.student.myapp.adapter.LoaispAdapter;
import com.example.student.myapp.adapter.SanphamAdapter;
import com.example.student.myapp.model.Loaisp;
import com.example.student.myapp.model.Sanpham;
import com.example.student.myapp.ultil.CheckConnection;
import com.example.student.myapp.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.Object;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
 Toolbar toolbar;
 ViewFlipper viewFlipper;
 RecyclerView recyclerViewmanhinhChinh;
 NavigationView navigationView;
 ListView listView;
 DrawerLayout drawerLayout;
 ArrayList<Loaisp> mangloaisp;
 LoaispAdapter loaispAdapter;
 int id =0;
 String tenloaisp="";
 String hinhanhloaisp="";
 int ID=0;
 String Tensanpham="";
 Integer Giasanpham=0;
 String Hinhanhsanpham="";
 String Motasanpham="";
 int IDsanpham=0;
 ArrayList<Sanpham> mangsanpham;
 SanphamAdapter sanphamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if(CheckConnection.haveNetworkconnection(getApplicationContext())){
            ActionBar();
            ActionViewFlipper();
            Getdulieusp();
          GetDuLieuSPMoiNhat();
          CattchOnItemListView();
        } else{
            CheckConnection.ShowToast_Short(getApplicationContext(),"Kiem tra lai ket noi");
            finish();//ket thuc man hinh
        }

    }
// thanh menu man hinh keo
    private void CattchOnItemListView() {
     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
             switch (i){
                 case 0:
                     if(CheckConnection.haveNetworkconnection(getApplicationContext())){
                         Intent intent = new Intent(MainActivity.this,MainActivity.class);
                         startActivity(intent);
                     } else{
                         CheckConnection.ShowToast_Short(getApplicationContext(),"ban hay kiem tra lai ket noi");
                     }
                     // click vao la dong menu
                     drawerLayout.closeDrawer(GravityCompat.START); // mo kieu nao thi dong kieu do
                     break;
                 case 1:
                     if(CheckConnection.haveNetworkconnection(getApplicationContext())){
                         Intent intent = new Intent(MainActivity.this,DienThoaiActivity.class);
                         intent.putExtra("idloaisanpham",mangloaisp.get(i).getId());
                         startActivity(intent);
                     } else{
                         CheckConnection.ShowToast_Short(getApplicationContext(),"ban hay kiem tra lai ket noi");
                     }
                     // click vao la dong menu
                     drawerLayout.closeDrawer(GravityCompat.START); // mo kieu nao thi dong kieu do
                     break;
                 case 2:
                     if(CheckConnection.haveNetworkconnection(getApplicationContext())){
                         Intent intent = new Intent(MainActivity.this,LapTopActivity.class);
                         intent.putExtra("idloaisanpham",mangloaisp.get(i).getId());
                         startActivity(intent);
                     } else{
                         CheckConnection.ShowToast_Short(getApplicationContext(),"ban hay kiem tra lai ket noi");
                     }
                     // click vao la dong menu
                     drawerLayout.closeDrawer(GravityCompat.START); // mo kieu nao thi dong kieu do
                     break;
                 case 3:
                     if(CheckConnection.haveNetworkconnection(getApplicationContext())){
                         Intent intent = new Intent(MainActivity.this,LienHeActivity.class);

                         startActivity(intent);
                     } else{
                         CheckConnection.ShowToast_Short(getApplicationContext(),"ban hay kiem tra lai ket noi");
                     }
                     // click vao la dong menu
                     drawerLayout.closeDrawer(GravityCompat.START); // mo kieu nao thi dong kieu do
                     break;
                 case 4:
                     if(CheckConnection.haveNetworkconnection(getApplicationContext())){
                         Intent intent = new Intent(MainActivity.this,ThongTinActivity.class);

                         startActivity(intent);
                     } else{
                         CheckConnection.ShowToast_Short(getApplicationContext(),"ban hay kiem tra lai ket noi");
                     }
                     // click vao la dong menu
                     drawerLayout.closeDrawer(GravityCompat.START); // mo kieu nao thi dong kieu do
                     break;

             }
         }
     });
    }

    private void GetDuLieuSPMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Log.v("abcabca","abcabc1");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdansanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response!=null){
                    for (int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID=jsonObject.getInt("id");
                            Tensanpham =jsonObject.getString("tensp");
                            Giasanpham =jsonObject.getInt("giasp");
                            Hinhanhsanpham =jsonObject.getString("hinhanhsp");
                            Motasanpham =jsonObject.getString("motasp");
                            IDsanpham =jsonObject.getInt("idsp");
                            mangsanpham.add(new Sanpham(ID,Tensanpham,Giasanpham,Hinhanhsanpham,Motasanpham,IDsanpham));
                            sanphamAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);


//      ;

    }


    private void Getdulieusp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
       JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanloaisp, new Response.Listener<JSONArray>() {
           @Override
           public void onResponse(JSONArray response) {
            if(response!=null){
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        id=jsonObject.getInt("id");
                        tenloaisp =jsonObject.getString("loaisanpham");
                        hinhanhloaisp = jsonObject.getString("hinhanhsanpham");
                        mangloaisp.add(new Loaisp(id,tenloaisp,hinhanhloaisp));
                        loaispAdapter.notifyDataSetChanged();//update lai adapter
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mangloaisp.add(3,new Loaisp(0,"Lien He","https://image.flaticon.com/icons/png/128/15/15886.png"));
                mangloaisp.add(4,new Loaisp(0,"Thong Tin","http://capnuochaiphong.com.vn/images/thongtin.png"));
            }
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               CheckConnection.ShowToast_Short(getApplicationContext(),error.toString());
           }
       });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://cdn.cellphones.com.vn/media/catalog/product/cache/7/small_image/220x175/9df78eab33525d08d6e5fb8d27136e95/m/t/mtp72_vw_34fr_watch-40-alum-gold-nc-5s_vw_34fr_wf_co_geo_sg.jpg");
        mangquangcao.add("https://cdn.cellphones.com.vn/media/catalog/product/cache/7/small_image/220x175/9df78eab33525d08d6e5fb8d27136e95/i/p/iphone11-purple-select-2019.png");
        for (int i =0;i<mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setAnimation(animation_slide_in);
        viewFlipper.setAnimation(animation_slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toobarManHinhChinh);
        viewFlipper = findViewById(R.id.viewlipper);
        recyclerViewmanhinhChinh = (RecyclerView) findViewById(R.id.recy);
        navigationView = findViewById(R.id.navigation);
        listView = findViewById(R.id.listViewHome);
        drawerLayout = findViewById(R.id.drawlayout);
        mangloaisp = new ArrayList<>();
        mangloaisp.add(0,new Loaisp(0,"Trang Chinh","https://cdn2.iconfinder.com/data/icons/jetflat-buildings/90/008_001_home_apartment_house_building-128.png"));
        loaispAdapter = new LoaispAdapter(mangloaisp,getApplicationContext());
        listView.setAdapter(loaispAdapter);
        mangsanpham = new ArrayList<>();
        sanphamAdapter = new SanphamAdapter(getApplicationContext(),mangsanpham);
        recyclerViewmanhinhChinh.setHasFixedSize(true);
        recyclerViewmanhinhChinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewmanhinhChinh.setAdapter(sanphamAdapter);
    }
}
