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

import java.util.List;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class ListrikAdapter extends RecyclerView.Adapter<ListrikAdapter.ViewHolder> {

    private List<ModelListrik> items;
    private ListrikAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(ModelListrik modelNews);
    }

    public ListrikAdapter(Context context, List<ModelListrik> items, ListrikAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = (onSelectData) xSelectData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_listrik, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelListrik data = items.get(position);

        //Get Image
        Glide.with(mContext)
                .load(data.getGambar())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgListrik);

        holder.tvNama.setText(data.getTxtNama());
        holder.tvDesk.setText(data.getTxtDeskripsi());
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

        public TextView tvNama,tvDesk;
        public RelativeLayout rlList;
        public ImageView imgListrik;

        public ViewHolder(View itemView) {
            super(itemView);
            rlList = itemView.findViewById(R.id.rlList);
            tvDesk  = itemView.findViewById(R.id.tvDesk);
            tvNama = itemView.findViewById(R.id.tvNama);
            imgListrik = itemView.findViewById(R.id.imgListrik);
        }
    }
}
