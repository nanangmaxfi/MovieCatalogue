package id.nanangmaxfi.moviecatalogue.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.R;
import id.nanangmaxfi.moviecatalogue.model.Genre;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Genre> genre;

    public GenreAdapter(Context context, ArrayList<Genre> genre) {
        this.context = context;
        this.genre = genre;
    }

    private ArrayList<Genre> getGenre() {
        return genre;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_genre, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.txtGenre.setText(getGenre().get(i).getName());
    }

    @Override
    public int getItemCount() {
        return genre.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtGenre;
        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtGenre = itemView.findViewById(R.id.item_genre);
        }
    }
}
