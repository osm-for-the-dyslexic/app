package org.osm4dys.app;

import android.app.*;
import android.os.*;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;

public class MainOsmForDysActivity extends Activity 
{
    private WebView mWebView;
    private static final int MENU_GO_TO_MAP = 1;
    private static final int MENU_GO_TO_ABOUT = 2;
    private static final String STARTING_URL = "http://www.osm4dys.org/viewer";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mWebView = (WebView) findViewById(R.id.mainWebView);
        mWebView.setWebViewClient(new OsmForDysWebClient());        
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl(STARTING_URL);
        
    }
    
    private class OsmForDysWebClient extends WebViewClient {
    }
    
}
