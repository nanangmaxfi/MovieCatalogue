package id.nanangmaxfi.moviecatalogue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.R;
import id.nanangmaxfi.moviecatalogue.model.Movie;

public class MovieAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        Movie movie = (Movie)getItem(position);
        viewHolder.bind(movie);
        return view;
    }

    private class ViewHolder {
        private TextView txtTitle;
        private TextView txtDesc;
        private ImageView imgPoster;

        ViewHolder(View view){
            txtTitle = view.findViewById(R.id.txt_title);
            txtDesc = view.findViewById(R.id.txt_desc);
            imgPoster = view.findViewById(R.id.img_poster);
        }

        void bind(Movie movie){
            txtTitle.setText(movie.getTitle());
            txtDesc.setText(movie.getDesc());
            Glide.with(context).load(movie.getPoster()).into(imgPoster);
        }
    }
}
