package com.example.dongho1.Activity.AdapterCustom;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.example.dongho1.Activity.Activity1.TrangChu;
import com.example.dongho1.Activity.Activity1.TrangThaiDonHang;
import com.example.dongho1.Activity.Object.ObjectWatchTtdh;
import com.example.dongho1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class WatchTtdhAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private LayoutInflater layoutInflater;
    ArrayList<ObjectWatchTtdh> listTtdh;
    private ObjectWatchTtdh objectWatchTtdh;



    public WatchTtdhAdapter(Context context, int layout, ArrayList<ObjectWatchTtdh> listTtdh) {
        this.context = context;
        this.layout=layout;
        this.listTtdh=listTtdh;
    }


    @Override
    public int getCount() {
        return listTtdh.size();
    }

    @Override
    public Object getItem(int i) {
        return listTtdh.get(i);
    }


    public long getItemId(int i) {
        return listTtdh.get(i).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_listview_trangthaidonhang, null);
        }

        ImageView imageView = view.findViewById(R.id.imgWatch_trangthaidonhang);
        TextView date = view.findViewById(R.id.date_trangthaidonhang);
        TextView price = view.findViewById(R.id.Price_trangthaidonhang);
        TextView trangthai = view.findViewById(R.id.txtTrangThai_trangthaidonhang);

        objectWatchTtdh = listTtdh.get(position);
        objectWatchTtdh.set_idOder(objectWatchTtdh.get_idOder());
        Picasso.get().load(TrangChu.linkhttp+objectWatchTtdh.getImgWatch()).into(imageView);
        date.setText(objectWatchTtdh.getDate());
        price.setText('$'+String.valueOf(objectWatchTtdh.getPrice()));
        trangthai.setText(objectWatchTtdh.getTxtTrangThai());

        if(trangthai.getText().equals("Completed"))
            trangthai.setTextColor(Color.parseColor("#29844C"));
        if(trangthai.getText().equals("Aborting") || trangthai.getText().equals("Aborted"))
            trangthai.setTextColor(Color.parseColor("#B93838"));
        if(trangthai.getText().equals("Progressing") || trangthai.getText().equals("Purchasing"))
            trangthai.setTextColor(Color.parseColor("#C09E57"));

        return view;

//          Có 5 trạng thái:
        // 0:Progressing
        // 1:Purchasing
        // 2:Completed
        // 3:Arborting
        // 4:Arborted
//
//        final int currentPos = position;
//        // Set the card background color based on whether the current item is selected
//        if (dsLTCduocchon.contains(currentPos)) {
//            cardviewCancel.setCardBackgroundColor(Color.parseColor("#313131"));
//        } else {
//            cardviewCancel.setCardBackgroundColor(Color.parseColor("#A63535"));
//        }
//        // Tô lại màu cho 2 trạng thái Completed và Waiting For Purchase
//        if(trangthai.getText().equals("Completed") || trangthai.getText().equals("Waiting For Purchase"))
//            cardviewCancel.setCardBackgroundColor(Color.parseColor("#313131"));
//        // Add or remove the current item from the selected list when it is clicked
//        cardviewCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder thongbao = new AlertDialog.Builder((TrangThaiDonHang) v.getContext());
//                thongbao.setTitle("Cancel Oder");
//                if(listTtdh.get(currentPos).getTxtTrangThai().equals("Waiting For Progressing"))
//                    thongbao.setMessage("Are you sure you want to cancel\n"+listTtdh.get(currentPos).getWatchName() + " order?");
//                thongbao.setIcon(R.drawable.ic_thongbao);
//
//                thongbao.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int i) {
//                            // Add or remove the current item from the selected list when it is clicked
//                            if (dsLTCduocchon.contains(currentPos)) {
//                                dsLTCduocchon.remove(Integer.valueOf(currentPos));
//                            } else {
//                                dsLTCduocchon.add(currentPos);
//                            }
//                            // Update the card background color
//                            if (dsLTCduocchon.contains(currentPos)) {
//                                cardviewCancel.setCardBackgroundColor(Color.parseColor("#313131"));
//
//                                listTtdh.get(currentPos).setTxtTrangThai("Aborted");
//                                notifyDataSetChanged();
//                                //trangthai.setText("Aborted");
//                            }
//                             else {
//                                cardviewCancel.setCardBackgroundColor(Color.parseColor("#A63535"));
//                            }
//                        }
//                    });
//
//                thongbao.setNegativeButton("Abort", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                        }
//                    });
//
//                if(listTtdh.get(currentPos).getTxtTrangThai().equals("Waiting For Progressing"))
//                    thongbao.show();
//            }
//        });


    }
}
