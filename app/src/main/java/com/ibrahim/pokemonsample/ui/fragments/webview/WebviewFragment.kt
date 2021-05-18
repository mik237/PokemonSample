package com.ibrahim.pokemonsample.ui.fragments.webview

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.webkit.WebSettings
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.ibrahim.pokemonsample.R
import com.ibrahim.pokemonsample.util.Constants
import kotlinx.android.synthetic.main.fragment_webview.*

class WebviewFragment : Fragment(R.layout.fragment_webview){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initClickListeners()
    }

    private fun initClickListeners() {
        ivBack.setOnClickListener {
            if(webView.canGoBack())
                webView.goBack()
        }

        ivNext.setOnClickListener {
            if(webView.canGoForward())
                webView.goForward()
        }

        ivRefresh.setOnClickListener { webView.reload() }
    }

    private fun initViews() {
        webView.apply {
            webViewClient = MyWebviewClient(webViewListener = object : WebViewListener{
                override fun onPageStarted() {
                    progressBar.isVisible = true
                }

                override fun onPageFinished() {
                    progressBar.isVisible = false
                }
            })

            isSaveFromParentEnabled = true
            settings.apply {
                javaScriptEnabled = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
        }
        webView.loadUrl(Constants.URL)
    }


    override fun onDestroyView() {
        webView.destroy()
        super.onDestroyView()
    }


}