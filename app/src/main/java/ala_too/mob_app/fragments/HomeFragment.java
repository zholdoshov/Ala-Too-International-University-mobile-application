package ala_too.mob_app.fragments;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ala_too.mob_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    WebView v;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //WebView
        String url = "http://iaau.edu.kg/view/public/news/list.xhtml;jsessionid=Kn6cIZghdWlJlI6p3b-XAtqx8BgGxADaNHuJOF3o.sites";
        v = view.findViewById(R.id.webViewMain);
        WebSettings webSettings = v.getSettings();
        v.getSettings().setJavaScriptEnabled(true);
        v.setWebViewClient(new WebViewClient(){
            ProgressDialog pd = new ProgressDialog(getActivity());

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pd.setTitle("Please Wait...");
                pd.setMessage("Loading...");
                pd.show();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pd.cancel();
                super.onPageFinished(view, url);
            }
        });

        //improve webView performance
        v.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        v.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        v.getSettings().setAppCacheEnabled(true);
        v.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);

        v.loadUrl(url);
        //End of WebView
    }
}
