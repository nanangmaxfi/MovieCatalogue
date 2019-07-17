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
import id.nanangmaxfi.moviecatalogue.database.Favorite;

public class FavTvAdapter extends RecyclerView.Adapter<FavTvAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Favorite> tvs;

    public FavTvAdapter(Context context, ArrayList<Favorite> tvs) {
        this.context = context;
        this.tvs = tvs;
    }

    public ArrayList<Favorite> getTvs() {
        return tvs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        final String imageURL = "https://image.tmdb.org/t/p/w185";
        holder.txtTitle.setText(getTvs().get(i).getTitle());
        holder.txtDesc.setText(getTvs().get(i).getDescription());
        Glide.with(context).load(imageURL+getTvs().get(i).getPoster()).into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return tvs.size();
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
