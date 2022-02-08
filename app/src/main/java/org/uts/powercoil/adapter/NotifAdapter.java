package org.uts.powercoil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.uts.powercoil.R;
import org.uts.powercoil.model.ModelListrik;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class NotifAdapter extends RecyclerView.Adapter<NotifAdapter.ViewHolder> {

    private List<ModelListrik> items;
    private NotifAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(ModelListrik modelNews);
    }

    public NotifAdapter(Context context, List<ModelListrik> items, NotifAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = (onSelectData) xSelectData;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_notif, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelListrik data = items.get(position);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //Get Image
//        Glide.with(mContext)
//                .load(data.getGambar())
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.imgListrik);
        String kapasitas = data.getTxtKapasitas();
        String terisi = data.getTxtTerisi();
        Integer vol = Integer.parseInt(kapasitas);
        Integer volterisi = Integer.parseInt(terisi);

//        if (vol == volterisi){
//            holder.tvKet2.setText("Full Capacity");
//            holder.itemView.setVisibility(View.VISIBLE);
//        }else{
////            holder.tvKet2.setText("");
//            holder.itemView.setVisibility(View.GONE);
//            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
//            params.height = 0;
//            holder.itemView.setLayoutParams(params);
//        }

        String angin = data.getTxtRadiasi();
        String matahari = data.getTxtRadiasi();

        Double angin1 = Double.parseDouble(angin);
        Double matahari1 = Double.parseDouble(matahari);

        if (data.getTxtDeskripsi().equals("Pembangkit Listrik Tenaga Bayu")){
            if (angin1 > 7) {
                holder.tvKet.setText("Angin Terlalu Kencang");
                if (vol == volterisi){
                    holder.tvKet2.setText("Full Capacity");
                    holder.itemView.setVisibility(View.VISIBLE);
                }
            }else if (vol == volterisi) {
                holder.tvKet.setText("Full Capacity");
                holder.itemView.setVisibility(View.VISIBLE);
            }else{
//                holder.tvKet.setText("");
                holder.itemView.setVisibility(View.GONE);
                ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
                params.height = 0;
                holder.itemView.setLayoutParams(params);

            }
        }else{
            if (matahari1 > 4.8) {
                holder.tvKet.setText("Radiasi Matahari Tinggi");
                if (vol == volterisi){
                    holder.tvKet2.setText("Full Capacity");
                    holder.itemView.setVisibility(View.VISIBLE);
                }
            }else if (vol == volterisi){
                holder.tvKet.setText("Full Capacity");
                holder.itemView.setVisibility(View.VISIBLE);
            }
            else{
//                holder.tvKet.setText("");
                holder.itemView.setVisibility(View.GONE);
                ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
                params.height = 0;
                holder.itemView.setLayoutParams(params);

            }
        }


        holder.tvNamaNotif.setText(data.getTxtNama());
//        holder.tvDesk.setText(data.getTxtDeskripsi());
        holder.rlList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNamaNotif,tvKet, tvKet2;
        public RelativeLayout rlList;
        public ImageView imgListrik;

        public ViewHolder(View itemView) {
            super(itemView);
            rlList = itemView.findViewById(R.id.rlnotif);
            tvKet  = itemView.findViewById(R.id.tvKeterangan);
            tvKet2  = itemView.findViewById(R.id.tvKeterangan2);
            tvNamaNotif = itemView.findViewById(R.id.tvNamaNotif);
//            imgListrik = itemView.findViewById(R.id.imgListrik);
        }
    }
}
