package com.example.accesskamera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class playMovActivity extends AppCompatActivity {

    final int PICKFILE_RESULT_CODE=1;

    private VideoView vidply;
    private Button btPlay;
    private MediaController mdc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_mov);

        vidply =(VideoView) findViewById(R.id.videoViewPlaying);
        btPlay = (Button) findViewById(R.id.buttonFile);

        mdc=new MediaController(this);
        vidply.setMediaController(mdc);

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
                fileintent.setType("vido/mp4");
                try {
                    startActivityForResult(fileintent, PICKFILE_RESULT_CODE);
                }catch (ActivityNotFoundException e) {
                    Log.e("tag", "No activity can handle picking  a file. Showing alternatives.");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null){
            return;
        }
        switch (requestCode){
            case PICKFILE_RESULT_CODE:
                if (resultCode==RESULT_OK){
                    try {
                        String FilePath= data.getData().getPath();
                        Uri vid= Uri.parse(FilePath);
                        vidply.setVideoURI(vid);
                        vidply.start();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
