package com.ashu.retrofitexperiment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class Webviewactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webviewactivity)
        var detailwebview = findViewById<WebView>(R.id.detailwebview)
        var progressbar = findViewById<ProgressBar>(R.id.progressBar2)
        val url = intent.getStringExtra("URL")
        if (url != null){

            detailwebview.settings.javaScriptEnabled = true
            detailwebview.settings.userAgentString = "Mozilla/5.0 (iphone; U; CPU like Mac Os X; en)AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"
            detailwebview.webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    progressbar.visibility = View.GONE
                    detailwebview.visibility = View.VISIBLE


                }
            }
            detailwebview.loadUrl(url)



        }
    }
}