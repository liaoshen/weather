package activitytest.example.com.coolweather.util;

/**
 * Created by lyx on 2016/4/22.
 */

import android.os.Bundle;



        import android.app.Activity;
        import android.os.Bundle;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
        import android.widget.TextView;

import activitytest.example.com.coolweather.R;


/**
 * Created by lyx on 2016/4/21.
 */
public class SimpleActivity extends Activity {
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String load=getIntent().getStringExtra("cont");

        setContentView(R.layout.simaple_view);
        TextView bb=(TextView)findViewById(R.id.aa);

        webView=(WebView)findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(load );

    }

}

