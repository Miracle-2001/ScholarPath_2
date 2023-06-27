package com.example.bjtuview.ui.query.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class GetPicture {
    private static int RESULT_LOAD_IMAGE = 1,REQUEST_CODE_CAMERA = 2;

    public static void upload(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
    }

    public static Object[] openCamera(Activity activity, Context context, String imagePath, Uri imageUri) {
        //创建File对象，用于存储拍照后的图片
        File outputImage=new File(context.getExternalCacheDir(),"outputImage.jpg");
        imagePath = outputImage.getAbsolutePath();
        System.out.println(imagePath);
        try {
            if (outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT>=24){
            imageUri = FileProvider.getUriForFile(context,
                    "com.example.camerralbumtest.fileprovider",outputImage);
        }else {
            imageUri = Uri.fromFile(outputImage);
        }
        //启动相机程序
        Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        activity.startActivityForResult(intent,REQUEST_CODE_CAMERA);
        return new Object[]{imagePath,imageUri};
    }
}
