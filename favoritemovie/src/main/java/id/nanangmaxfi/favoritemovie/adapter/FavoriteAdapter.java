package id.nanangmaxfi.favoritemovie.adapter;

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

import id.nanangmaxfi.favoritemovie.R;
import id.nanangmaxfi.favoritemovie.model.Favorite;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private final ArrayList<Favorite> listFavorite = new ArrayList<>();
    private Context context;

    public FavoriteAdapter(Context context) {
        this.context = context;
    }

    public void setListFavorite(ArrayList<Favorite> listFavorite){
        this.listFavorite.clear();
        this.listFavorite.addAll(listFavorite);
        notifyDataSetChanged();
    }

    private ArrayList<Favorite> getListFavorite(){
        return listFavorite;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        final String imageURL = "https://image.tmdb.org/t/p/w185";
        holder.txtTitle.setText(getListFavorite().get(i).getTitle());
        holder.txtDesc.setText(getListFavorite().get(i).getDescription());

        if ("movie".equals(getListFavorite().get(i).getType())){
            holder.txtType.setText(context.getResources().getString(R.string.movie));
        }
        else{
            holder.txtType.setText(context.getResources().getString(R.string.tv_show));
        }
        Glide.with(context).load(imageURL+getListFavorite().get(i).getPoster()).into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return listFavorite.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle;
        private TextView txtDesc;
        private TextView txtType;
        private ImageView imgPoster;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDesc = itemView.findViewById(R.id.txt_desc);
            txtType = itemView.findViewById(R.id.txt_type);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }
    }
}
