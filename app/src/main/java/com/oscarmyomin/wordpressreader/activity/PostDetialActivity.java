package com.oscarmyomin.wordpressreader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oscarmyomin.wordpressreader.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class PostDetialActivity extends AppCompatActivity {

    ImageView img, auth;
    TextView title, content, auth_name, time;
    Spanned spannedContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdetial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        img = (ImageView) findViewById(R.id.img_detail);
        auth = (ImageView) findViewById(R.id.img_author);
        content = (TextView)findViewById(R.id.txt_detail_content);
        auth_name = (TextView) findViewById(R.id.txt_detail_author);
        time = (TextView) findViewById(R.id.txt_detail_time);

        final Intent detail = getIntent();

        Picasso.with(this)
                .load(detail.getStringExtra("image"))
                .placeholder(R.drawable.ic_menu_gallery)
                .error(R.drawable.ic_menu_camera)
                .into(img);

        Picasso.with(this)
                .load(detail.getStringExtra("auth_image"))
                .placeholder(R.drawable.ic_menu_gallery)
                .error(R.drawable.ic_menu_camera)
                .into(auth);


        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            time.setText(DateUtils.getRelativeTimeSpanString(Long.parseLong(String.valueOf(simpleDateFormat.parse(detail.getStringExtra("time")).getTime())), System.currentTimeMillis(), 1000));
        }catch (ParseException e){

        }
        ((TextView)findViewById(R.id.txt_detail_title)).setText(detail.getStringExtra("title"));

        auth_name.setText(detail.getStringExtra("auth_name"));
        spannedContent = Html.fromHtml(detail.getStringExtra("content"));
        content.setMovementMethod(LinkMovementMethod.getInstance());
        content.setText(spannedContent);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_share);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = detail.getStringExtra("link");
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, message);

                startActivity(Intent.createChooser(share, "Share"));
            }
        });
    }

}
