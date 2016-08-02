package com.sheng.imagedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.bither.util.NativeUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testJpeg();
    }


    private void testJpeg() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    int quality = 90;//original
                    quality=50;//同学们可以与原生的压缩方法对比一下，同样设置成50效果如何

                    InputStream in = getResources().getAssets().open("test2.jpg");
                    Bitmap bit = BitmapFactory.decodeStream(in);
                    File dirFile = new File(Environment.getExternalStorageDirectory() + "/Test");

                    if (!dirFile.exists()) {
                        dirFile.mkdirs();
                    }

                    File originalFile = new File(dirFile, "original.jpg");
                    FileOutputStream fileOutputStream = new FileOutputStream(originalFile);
                    bit.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);

                    File jpegTrueFile = new File(dirFile, "jpegtrue.jpg");
                    File jpegFalseFile = new File(dirFile, "jpegfalse.jpg");

                    NativeUtil.compressBitmap(bit, quality,jpegTrueFile.getAbsolutePath(), true);
                    NativeUtil.compressBitmap(bit, quality,jpegFalseFile.getAbsolutePath(), false);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }






}






