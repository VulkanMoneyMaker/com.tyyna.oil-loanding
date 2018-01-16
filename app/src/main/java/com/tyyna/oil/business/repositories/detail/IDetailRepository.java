package com.tyyna.oil.business.repositories.detail;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import io.reactivex.Single;

public interface IDetailRepository {

    Single<Uri> getImg(Bitmap bmp, Context context);
}
