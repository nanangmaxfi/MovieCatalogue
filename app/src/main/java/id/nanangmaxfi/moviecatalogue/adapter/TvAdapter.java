package id.nanangmaxfi.moviecatalogue.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.R;
import id.nanangmaxfi.moviecatalogue.model.GetTv;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {
    private Context context;
    private ArrayList<GetTv> Tvs;

    public TvAdapter(Context context, ArrayList<GetTv> Tvs) {
        this.context = context;
        this.Tvs = Tvs;
    }

    private ArrayList<GetTv> getTvs() {
        return Tvs;
    }

    @NonNull
    @Override
    public TvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        return new TvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.ViewHolder holder, int i) {
        final String imageURL = "https://image.tmdb.org/t/p/w185";
        holder.txtTitle.setText(getTvs().get(i).getTitle());
        holder.txtDesc.setText(getTvs().get(i).getOverview());
        Glide.with(context).load(imageURL+getTvs().get(i).getPoster()).into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return Tvs.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle;
        private TextView txtDesc;
        private ImageView imgPoster;
        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDesc = itemView.findViewById(R.id.txt_desc);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }
    }
}
