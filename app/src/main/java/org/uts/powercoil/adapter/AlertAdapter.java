package org.uts.powercoil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.uts.powercoil.R;
import org.uts.powercoil.model.ModelListrik;

import java.util.List;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.ViewHolder> {

    private List<ModelListrik> items;
    private AlertAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(ModelListrik modelNews);
    }

    public AlertAdapter(Context context, List<ModelListrik> items, AlertAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = (onSelectData) xSelectData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_alert, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelListrik data = items.get(position);

        //Get Image
//        Glide.with(mContext)
//                .load(data.getGambar())
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.imgListrik);

//
//        if (vol <= volterisi){
//            holder.tvKet.setText("rusak");
//        }else{
//            holder.tvKet.setText("");
//        }

        String angin = data.getTxtRadiasi();
        String matahari = data.getTxtRadiasi();

        Double angin1 = Double.parseDouble(angin);
        Double matahari1 = Double.parseDouble(matahari);

        if (data.getTxtDeskripsi().equals("Pembangkit Listrik Tenaga Bayu")){
            if (angin1 > 7) {
//                holder.itemView.setVisibility(View.GONE);
                holder.ketAlert.setText("Angin Terlalu Kencang");
                holder.ketAlert.setTextColor(RED);
                holder.lottieAnimationView.setVisibility(View.VISIBLE);
            } else{
                holder.ketAlert.setText("Normal");
                holder.ketAlert.setTextColor(GREEN);
                holder.lottieAnimationView1.setVisibility(View.VISIBLE);

            }
        }else{
            if (matahari1 > 4.8) {
                holder.ketAlert.setText("Radiasi Matahari Tinggi");
                holder.ketAlert.setTextColor(RED);
                holder.lottieAnimationView.setVisibility(View.VISIBLE);
            }else{
                holder.ketAlert.setText("Normal");
                holder.ketAlert.setTextColor(GREEN);
                holder.lottieAnimationView1.setVisibility(View.VISIBLE);

            }
        }

        ///// terisi

        String kapasitas = data.getTxtKapasitas();
        String terisi = data.getTxtTerisi();
        Integer vol = Integer.parseInt(kapasitas);
        Integer volterisi = Integer.parseInt(terisi);

        if ( vol == volterisi) {
            holder.ketKapasitas.setText("Full Capacity");
            holder.ketKapasitas.setTextColor(RED);
            holder.lottieAnimationView.setVisibility(View.VISIBLE);
        } else{
            holder.ketKapasitas.setText("");
            holder.lottieAnimationView1.setVisibility(View.VISIBLE);

        }



        holder.namaAlert.setText(data.getTxtNama());
//        holder.tvDesk.setText(data.getTxtDeskripsi());
        holder.rlalert.setOnClickListener(new View.OnClickListener() {
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

        public TextView namaAlert,ketAlert, ketKapasitas;
        public RelativeLayout rlalert;
        public ImageView imgListrik;
        public LottieAnimationView lottieAnimationView, lottieAnimationView1;

        public ViewHolder(View itemView) {
            super(itemView);
            rlalert = itemView.findViewById(R.id.rlalert);
            ketAlert  = itemView.findViewById(R.id.ketAlert);
            ketKapasitas  = itemView.findViewById(R.id.ketKapasitas);
            namaAlert = itemView.findViewById(R.id.namaAlert);
            lottieAnimationView = itemView.findViewById(R.id.square);
            lottieAnimationView1 = itemView.findViewById(R.id.square1);

            lottieAnimationView.animate();
//            imgListrik = itemView.findViewById(R.id.imgListrik);
        }
    }
}
