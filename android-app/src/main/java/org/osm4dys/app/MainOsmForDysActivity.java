package org.osm4dys.app;

import android.app.*;
import android.os.*;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Bitmap;


public class MainOsmForDysActivity extends Activity 
{
    private WebView mWebView = null;
    private static final int MENU_GO_TO_MAP = 10;
    private static final int MENU_GO_TO_ABOUT = 20;
    private static final String VIEWER_URL = "http://www.osm4dys.org/viewer/";
    private static final String ABOUT_URL = "http://www.osm4dys.org/about/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mWebView = (WebView) findViewById(R.id.mainWebView);
        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
        }else{
            mWebView.setWebViewClient(new OsmForDysWebClient());        
            mWebView.getSettings().setBuiltInZoomControls(false);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.setWebChromeClient(new WebChromeClient());
            mWebView.loadUrl(VIEWER_URL);
        }
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState ) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }

    //@Override
    //protected void onRestoreInstanceState(Bundle savedInstanceState) {
    //    super.onRestoreInstanceState(savedInstanceState);
    //    mWebView.restoreState(savedInstanceState);
    //}
   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean retval = updateOptionsMenu(menu);
        return retval;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean retval = updateOptionsMenu(menu);
        return retval;
	}

    private boolean updateOptionsMenu(Menu menu){
		menu.clear();
        menu.add(Menu.NONE, MENU_GO_TO_MAP, 1, "MAP");
		menu.add(Menu.NONE, MENU_GO_TO_ABOUT, 2, "ABOUT");
        return true;
    }
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
            case MENU_GO_TO_MAP:
                mWebView.loadUrl(VIEWER_URL);
            break;
            case MENU_GO_TO_ABOUT:
                mWebView.loadUrl(ABOUT_URL);
            break;
        }
        return true;
    }    
   
    private class OsmForDysWebClient extends WebViewClient {
		ProgressDialog progressDialog = ProgressDialog.show(MainOsmForDysActivity.this, "", "LOADING..." ,true);

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageFinished(WebView view, final String url) {
			progressDialog.cancel();
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			progressDialog.show();
		}	    
    
    }

}
