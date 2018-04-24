package com.shahal.assignmentproject.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shahal.assignmentproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.shahal.assignmentproject.constants.AppConstant.ANNOUNCEMENT_HTML;
import static com.shahal.assignmentproject.constants.AppConstant.ANNOUNCEMENT_TITLE;

/**
 * Created by shahal on 23-04-2018.
 */

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.webview1)
    WebView webView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    String htmlData, title;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        unbinder = ButterKnife.bind(this);
        htmlData = getIntent().getStringExtra(ANNOUNCEMENT_HTML);
        title = getIntent().getStringExtra(ANNOUNCEMENT_TITLE);
        initView();
    }

    private void initView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(htmlData);
        webView.setWebViewClient(new MyWebViewClient());
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        if (TextUtils.isEmpty(htmlData)) {
            finish();
        }
        webView.loadData(htmlData, "text/html; charset=utf-8", "UTF-8");
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(progressBar!=null){
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onNewsBackPressed(View view) {
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
