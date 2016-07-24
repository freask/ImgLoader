package ru.freask.imgloader.adapters;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.freask.imgloader.R;

public class ListAdapter extends ArrayAdapter<String> {
    Context context;

    public ListAdapter(Context context) {
        super(context, R.layout.image_item);
        this.context = context;
    }


    static class ViewHolder{
        TextView text;
        ImageView img;
        ProgressBar progressBar;

        void populateItem(final String url, Context context) {
            text.setText(url);
            img.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            Picasso.with(context).load(url).placeholder(R.drawable.image_placeholder).into(img,  new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    progressBar.setVisibility(View.GONE);
                    img.setVisibility(View.VISIBLE);
                }

                @Override
                public void onError() {
                    progressBar.setVisibility(View.GONE);
                    img.setVisibility(View.VISIBLE);
                }
            });
        }
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.image_item, parent, false);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.img = (ImageView) convertView.findViewById(R.id.image);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String item = getItem(position);
        holder.populateItem(item, context);
        return convertView;
    }
}
