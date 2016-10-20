package com.moviejsonparsingusingparcelable;

import android.content.Context;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aalishan on 20/10/16.
 */
public class MovieAdapter extends BaseAdapter {
    private List<MovieModel> movieModelList;
    private Context ctx;
    private LayoutInflater inflater;

    public MovieAdapter(Context ctx, List<MovieModel> movieModelList) {
        this.movieModelList = movieModelList;
        this.ctx = ctx;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return movieModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.single_row, null);
            viewHolder.tvMovieName = (TextView) convertView.findViewById(R.id.tv_movie_name);
            viewHolder.tvMoviYear = (TextView) convertView.findViewById(R.id.tv_year);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.tvMovieName.setText(movieModelList.get(position).movie);
            viewHolder.tvMoviYear.setText(" " + movieModelList.get(position).year);

        }
        return convertView;
    }

    class ViewHolder {
        TextView tvMovieName;
        TextView tvMoviYear;
    }
}
