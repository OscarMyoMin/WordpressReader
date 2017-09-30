package com.oscarmyomin.wordpressreader.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.oscarmyomin.wordpressreader.Adapter_Post;
import com.oscarmyomin.wordpressreader.AppController;
import com.oscarmyomin.wordpressreader.R;
import com.oscarmyomin.wordpressreader.URL;
import com.oscarmyomin.wordpressreader.model.Post;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by philippe on 9/30/17.
 */

public class PostByCategory extends AppCompatActivity {

    SuperRecyclerView recyclerView;
    AVLoadingIndicatorView loader;
    RelativeLayout rlview;
    private LinearLayoutManager layoutManager;
    Context context;
    Activity activity;
    Adapter_Post adapter;
    List<Post> postList = new ArrayList<Post>();
    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recyclerview);

        Intent post = getIntent();
        id = post.getStringExtra("id");
        loader = (AVLoadingIndicatorView) findViewById(R.id.loader);
        recyclerView = (SuperRecyclerView) findViewById(R.id.item_list);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter_Post(this, postList);
        recyclerView.setAdapter(adapter);
        startAnim();
        json();
    }

    public void json(){
        JsonArrayRequest movieReq = new JsonArrayRequest(URL.category_post+id,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        stopAnim();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Post item = new Post();

                                JSONObject title = obj.getJSONObject("title");
                                item.setTitle(title.getString("rendered"));

                                JSONObject content = obj.getJSONObject("content");
                                item.setContent(content.getString("rendered"));

                                JSONObject excerpt = obj.getJSONObject("excerpt");

                                item.setImage(obj.getString("featured_image_big_url"));
                                item.setAuthor_image(obj.getString("author_image_url"));
                                item.setAuthor_name(obj.getString("author_name"));
                                item.setTime(obj.getString("date"));
                                item.setLink(obj.getString("link"));
                                postList.add(item);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("DATA :::", "Error: " + error.getMessage());
                stopAnim();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
    }



    void startAnim(){
        loader.show();
        // or avi.smoothToShow();
    }

    void stopAnim(){
        loader.hide();
    }
}
