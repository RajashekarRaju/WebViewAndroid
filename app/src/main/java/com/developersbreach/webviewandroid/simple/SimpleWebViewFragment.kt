package com.developersbreach.webviewandroid.simple

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import com.developersbreach.webviewandroid.MainActivity.Companion.WEB_URL_LINK
import com.developersbreach.webviewandroid.R


class SimpleWebViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar_simple_fragment).setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        val webView: WebView = view.findViewById(R.id.simple_web_view)
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar_simple_view)

        webView.loadUrl(WEB_URL_LINK)

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }
        }
    }
}