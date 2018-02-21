package com.benny.app.dynamicview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.benny.library.dynamicview.DynamicViewEngine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView vContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        vContainer = findViewById(R.id.container);
        vContainer.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends BaseAdapter {
        private String layoutXml = "<Grid sn=\"12345678\" dataSource=\"{items}\"><TitleView title=\"{title}\" logo=\"{logo}\" /></Grid>";
        private Map<String, String> props = new HashMap<>();

        public MyAdapter() {
            try {
                JSONArray items = new JSONArray();
                JSONObject item = new JSONObject();
                item.put("logo", "http://avatar.csdn.net/8/B/B/1_sinyu890807.jpg");
                item.put("title", "Title 0");
                items.put(item);
                item = new JSONObject();
                item.put("logo", "http://avatar.csdn.net/8/B/B/1_sinyu890807.jpg");
                item.put("title", "Title 1");
                items.put(item);
                props.put("items", items.toString());
            }
            catch (JSONException ignored) {
            }
        }

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Map<String, String> getItem(int position) {
            //props.put("title", "Title " + position);
            return props;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = DynamicViewEngine.getInstance().createView(MainActivity.this, layoutXml);
            }
            DynamicViewEngine.getInstance().bindView(convertView, getItem(position));
            return convertView;
        }
    }
}