package com.example.captureimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btn;
    private static final int RC_PIC_CODE=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=(Button)findViewById(R.id.btn);
        imageView=(ImageView)findViewById(R.id.imageview);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Capture();
            }
        });
    }

    private void Capture() {
        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,RC_PIC_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data); // super islia use kiya hai k oper ki jitni cheezay hen is variable is related wo yaha uth k a jay
        if (requestCode == RC_PIC_CODE){
            if (resultCode == RESULT_OK){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data"); //Bitmap image ko ly kr ata hai
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setImageBitmap(bitmap);
            }
        }else if(requestCode == RESULT_CANCELED){
            Toast.makeText(this, "CANCELLED", Toast.LENGTH_SHORT).show();
        }
    }
}
