package ru.freask.imgloader;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.freask.imgloader.adapters.ListAdapter;

public class ImagesActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> listAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        String url = b.getString(MainActivity.EXTRAS_KEY);

        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ListAdapter(this);
        listView.setAdapter(listAdapter);

        listAdapter.clear();
        MyTask task = new MyTask(url, context);
        task.execute();
    }

    class MyTask extends AsyncTask<Document, Void, Document> {
        String url;
        Context context;

        private MyTask(String url, Context context) {
            this.url = url;
            this.context = context;
        }
        @Override
        protected Document doInBackground(Document... params) {
            try {
                return Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Document doc) {
            super.onPostExecute(doc);

            if (doc == null)
                return;

            List<String> images = Parser.parse(url, doc);

            if (images.size() == 0)
            {
                Toast.makeText(context, "There are no images or wrong url address", Toast.LENGTH_SHORT).show();
            }
            for (String img:images) {
                listAdapter.add(img);
            }
            //Тут выводим итоговые данные
        }
    }
}
