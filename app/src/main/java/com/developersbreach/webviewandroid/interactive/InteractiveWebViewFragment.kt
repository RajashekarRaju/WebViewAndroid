package com.developersbreach.webviewandroid.interactive

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.developersbreach.webviewandroid.R
import com.google.android.material.bottomappbar.BottomAppBar

class InteractiveWebViewFragment : Fragment() {

    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var webSettings: WebSettings
    private lateinit var viewModel: InteractiveWebViewViewModel

    companion object {
        fun newInstance() = InteractiveWebViewFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_interactive_web_view, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomAppBar = view.findViewById(R.id.web_view_bottom_app_bar)
        webView = view.findViewById(R.id.interactive_web_view)
        progressBar = view.findViewById(R.id.progress_bar_interactive_web)

        webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        setWebViewMenu(bottomAppBar)
    }

    private fun setWebViewMenu(bottomAppBar: BottomAppBar) {
        bottomAppBar.replaceMenu(R.menu.bottom_app_bar_menu)
        bottomAppBar.hideOnScroll = true
        invalidateMenuOptions()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(InteractiveWebViewViewModel::class.java)
        webView.loadUrl(viewModel.urlLink)

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

    private fun invalidateMenuOptions() {
        val item: MenuItem = bottomAppBar.menu.getItem(3)
        item.isVisible = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }
}