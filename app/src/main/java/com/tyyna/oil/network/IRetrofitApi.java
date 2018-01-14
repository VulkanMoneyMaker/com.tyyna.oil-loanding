package com.tyyna.oil.network;




import com.tyyna.oil.network.model.IpModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IRetrofitApi {
    @GET
    Call<ResponseBody> getHtmlContent(@Url String str);

    @GET("json/")
    Call<IpModel> getIp();
}
