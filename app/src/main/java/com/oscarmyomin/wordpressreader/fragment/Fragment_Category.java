package com.oscarmyomin.wordpressreader.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.oscarmyomin.wordpressreader.adapter.Adapter_Category;
import com.oscarmyomin.wordpressreader.model.Category;
import com.oscarmyomin.wordpressreader.model.Post;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by philippe on 9/23/17.
 */

public class Fragment_Category extends Fragment {

    SuperRecyclerView recyclerView;
    AVLoadingIndicatorView loader;
    RelativeLayout rlview;
    private LinearLayoutManager layoutManager;
    Context context;
    Activity activity;
    Adapter_Category adapter;
    List<Category> postList = new ArrayList<Category>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        recyclerView = myView.findViewById(R.id.item_list);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter_Category(getActivity(), postList);
        recyclerView.setAdapter(adapter);
        json();
        return myView;
    }

    public void json(){
        JsonArrayRequest movieReq = new JsonArrayRequest(URL.category,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("DAtA :::", response.toString());
                        System.out.println("HELLO ====> "+ response.toString());
                        //hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Category item = new Category();

                                item.setId(obj.getInt("id"));
                                item.setName(obj.getString("name"));
                                item.setCount(obj.getInt("count"));

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
                //hidePDialog();

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
