package org.uts.powercoil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.uts.powercoil.R;
import org.uts.powercoil.model.ModelListrik;
import org.uts.powercoil.model.Pengkajian;

import java.util.List;

public class PengkajianAdapter extends RecyclerView.Adapter<PengkajianAdapter.ViewHolder> {

    private List<Pengkajian> items;
    private PengkajianAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(Pengkajian modelNews);
    }

    public PengkajianAdapter(Context context, List<Pengkajian> items, PengkajianAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = (PengkajianAdapter.onSelectData) xSelectData;
    }

    @Override
    public PengkajianAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pengkajian, parent, false);
        return new PengkajianAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pengkajian data = items.get(position);

        holder.tvId.setText(data.getId());
        holder.tvNama.setText(data.getNama());
        holder.tvAlamat.setText(data.getAlamat());
        holder.tvPelaksana.setText(data.getPelaksana());
        holder.tvTanggal.setText(data.getTanggal());
        holder.rlPengkajian.setOnClickListener(new View.OnClickListener() {
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


        public TextView tvId, tvNama,tvAlamat, tvPelaksana, tvTanggal;
        public RelativeLayout rlPengkajian;

        public ViewHolder(View itemView) {
            super(itemView);
            rlPengkajian = itemView.findViewById(R.id.rlPengkajian);
            tvId  = itemView.findViewById(R.id.id);
            tvNama = itemView.findViewById(R.id.name);
            tvAlamat = itemView.findViewById(R.id.alamat);
            tvPelaksana  = itemView.findViewById(R.id.pelaksana);
            tvTanggal  = itemView.findViewById(R.id.tanggal);

        }
    }
}
