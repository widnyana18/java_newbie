package com.example.accesskamera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.telecom.VideoProfile;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.security.PublicKey;
import java.util.Calendar;

public class RecVidActivity extends AppCompatActivity {

    private int VIDEO_RESULT=1;
    private VideoView myViewVid;
    private Button btRecord;
    private Uri movUri;
    private MediaController mdc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_vid);

        myViewVid=(VideoView) findViewById(R.id.videoView);
        btRecord=(Button) findViewById(R.id.buttonRecVid);

        mdc=new MediaController(this);

        btRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordVideo();
            }
        });
    }

    public void RecordVideo(){
        String dir= Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM) .getAbsolutePath();
        File folder=new File(dir+File.separator+"MELIVID");
        boolean success=true;

        if(!folder.exists()){
            try {
                success = folder.mkdirs();
                Toast.makeText(this, "Folder Create Successfull",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                success=!success;
                e.printStackTrace();
            }
            if (success){
                dir=dir+File.separator+"MELIVID";
            }

            Calendar calendar=Calendar.getInstance();
            File file = new File(dir, "MELIVID"+(calendar.getTimeInMillis()+"mp4"));
            if (!file.exists()){
                try {
                    file.createNewFile();
                    movUri= FileProvider.getUriForFile(RecVidActivity.this, BuildConfig.APPLICATION_ID, file);
                    Intent intent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,movUri);
                    startActivityForResult(intent,VIDEO_RESULT);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                try {
                    file.delete();
                    file.createNewFile();
                    movUri= FileProvider.getUriForFile(RecVidActivity.this, BuildConfig.APPLICATION_ID, file);
                    Intent intent =new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,movUri);
                    startActivityForResult(intent,VIDEO_RESULT);
                }catch (Exception e){
                    e.printStackTrace();
                }
                }
            }
        }
    @Override
    protected void onActivityResult(int requstCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requstCode, resultCode, data);
        if (requstCode==VIDEO_RESULT){
            if (resultCode==RESULT_OK){
                Uri vidUri=data.getData();
                myViewVid.setMediaController(mdc);
                myViewVid.setVideoURI(vidUri);
                myViewVid.start();
            }
        }
    }
}
