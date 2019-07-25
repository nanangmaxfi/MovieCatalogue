package id.nanangmaxfi.moviecatalogue.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.AppWidgetTarget;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import id.nanangmaxfi.moviecatalogue.R;
import id.nanangmaxfi.moviecatalogue.database.Favorite;

import static id.nanangmaxfi.moviecatalogue.app.MyApp.db;


public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private List<Favorite> widgetItems;
    private final Context context;

    public StackRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        widgetItems = new ArrayList<>(db.favoriteDao().getAllFavorite());
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return widgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final String imageURL = "https://image.tmdb.org/t/p/w185";
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);

        try {
            Bitmap bitmap = Glide.with(context)
                    .asBitmap()
                    .load(imageURL+widgetItems.get(position).getPoster())
                    .submit().get();
            rv.setImageViewBitmap(R.id.widget_image_view, bitmap);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Bundle extras = new Bundle();
        extras.putString(FavoriteWidget.EXTRA_ITEM, widgetItems.get(position).getTitle());
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.widget_image_view, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
