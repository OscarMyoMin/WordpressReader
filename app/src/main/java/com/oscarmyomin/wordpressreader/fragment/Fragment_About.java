package com.oscarmyomin.wordpressreader.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oscarmyomin.wordpressreader.R;

/**
 * Created by philippe on 9/23/17.
 */

public class Fragment_About extends Fragment {

    Button btnCode, btnFB;
    Context context;
    String facebookId = "100005581200190";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_about, container, false);
        btnCode = (Button) myView.findViewById(R.id.btn_download);
        btnFB = (Button) myView.findViewById(R.id.btn_fb);

        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/OscarMyoMin/WordpressReader/tree/master"));
                startActivity(myIntent);
            }
        });

        btnFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Check if FB app is even installed
                    context.getPackageManager().getPackageInfo("com.facebook.katana", 0);

                    String facebookScheme = "fb://profile/" + facebookId;
                    Intent fb = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookScheme));
                    startActivity(fb);
                }
                catch(Exception e) {

                    // Cache and Open a url in browser
                    String facebookProfileUri = "https://www.facebook.com/" + facebookId;
                    Intent fb = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookProfileUri));
                    startActivity(fb);
                }
            }
        });
        return myView;
    }
}
