package com.undead.nosavvy.labcalificado1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OfertaActivity extends AppCompatActivity {
    @BindView(R.id.video)
    VideoView videoView;
    @OnClick(R.id.deliver)
    public void click(){
        Intent i = new Intent(getApplicationContext(),PedidoActivity.class);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta);
        ButterKnife.bind(this);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(Uri.parse("https://www.youtube.com/watch?v=f9Slq2ck7xQ"));
        videoView.start();
    }
}
