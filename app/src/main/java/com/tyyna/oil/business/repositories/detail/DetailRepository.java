package com.tyyna.oil.business.repositories.detail;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;


import java.io.File;
import java.io.FileOutputStream;

import io.reactivex.Single;

public class DetailRepository implements IDetailRepository {




    @Override
    public Single<Uri> getImg(Bitmap bmp, Context context) {
        return Single.create(subscriber -> {
            Uri bmpUri = null;
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
            subscriber.onSuccess(bmpUri);
        });
    }
}
