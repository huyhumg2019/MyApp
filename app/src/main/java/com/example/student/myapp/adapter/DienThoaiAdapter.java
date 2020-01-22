package com.example.student.myapp.adapter;

import android.content.Context;
import android.media.Image;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.student.myapp.R;
import com.example.student.myapp.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DienThoaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraydienthoai;

    public DienThoaiAdapter(Context context, ArrayList<Sanpham> arraydienthoai) {
        this.context = context;
        this.arraydienthoai = arraydienthoai;
    }

    @Override
    public int getCount() {
        return arraydienthoai.size();
    }

    @Override
    public Object getItem(int i) {
        return arraydienthoai.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
public class ViewHolder{
        public TextView txttendienthoai,txtmotadienthoai,txtgiadienthoai;
        public ImageView imgdienthoai;

}
    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if(view==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_dienthoai,null);
            viewHolder.txttendienthoai =(TextView)view.findViewById(R.id.textviewdienthoai);
            viewHolder.txtgiadienthoai =(TextView)view.findViewById(R.id.textviewgiadienthoai);
            viewHolder.txtmotadienthoai =(TextView)view.findViewById(R.id.textviewmotadienthoai);
            viewHolder.imgdienthoai =(ImageView) view.findViewById(R.id.imageviewdienthoai);
        }else{
            viewHolder=(ViewHolder) view.getTag();
        }
        Sanpham sanpham = (Sanpham)getItem(i);
        viewHolder.txttendienthoai.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiadienthoai.setText("Gia:"+decimalFormat.format(sanpham.getGiasanpham())+"D");
        viewHolder.txtmotadienthoai.setMaxLines(2);
        viewHolder.txtmotadienthoai.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotadienthoai.setText(sanpham.getMotasanpham());
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).
                placeholder(R.drawable.common_full_open_on_phone).error(R.drawable.common_google_signin_btn_icon_dark).into(viewHolder.imgdienthoai);

        return view;
    }
}
