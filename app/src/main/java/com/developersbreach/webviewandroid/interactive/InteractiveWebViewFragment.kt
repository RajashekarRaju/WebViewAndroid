package com.developersbreach.webviewandroid.interactive

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.developersbreach.webviewandroid.MainActivity.Companion.WEB_URL_LINK
import com.developersbreach.webviewandroid.R
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class InteractiveWebViewFragment : Fragment() {

    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var webSettings: WebSettings

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

        loadWebView()

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

    private fun setWebViewMenu(bottomAppBar: BottomAppBar) {
        bottomAppBar.replaceMenu(R.menu.bottom_app_bar_menu)
        bottomAppBar.setOnMenuItemClickListener(onWebMenuClickListener())
        bottomAppBar.hideOnScroll = true
        invalidateMenuOptions()
    }

    private fun onWebMenuClickListener(): Toolbar.OnMenuItemClickListener {
        return object : MenuItem.OnMenuItemClickListener,
            Toolbar.OnMenuItemClickListener {
            @SuppressLint("NewApi")
            override fun onMenuItemClick(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.close_fragment_menu_item -> closeWebView()
                    R.id.refresh_page_detail_fragment_menu_item -> loadWebView()
                    R.id.search_detail_content_menu_item -> searchWebView()
                    R.id.change_theme_detail_fragment_menu_item -> changeWebViewTheme()
                    R.id.find_top_web_view_menu_item -> findPreviousQuery()
                    R.id.find_bottom_web_view_menu_item -> findNextQuery()
                    R.id.clear_matches_web_view_menu_item -> clearMatchesAndSwitchMenu()
                }
                return true
            }
        }
    }

    private fun closeWebView() {
        requireActivity().onBackPressed()
    }

    private fun loadWebView() {
        webView.loadUrl(WEB_URL_LINK)
    }

    private fun searchWebView() {
        val dialog: AlertDialog =
            MaterialAlertDialogBuilder(requireContext(), R.style.MaterialDialog)
                .setTitle(R.string.search_query_dialog_title)
                .setView(R.layout.search_edit_text)
                .setPositiveButton(R.string.search_query_dialog_positive_button, null)
                .setNegativeButton(R.string.search_query_dialog_negative_button, null)
                .create()

        dialog.setOnShowListener { alertDialog ->
            alertDialog as AlertDialog
            val positiveButton: Button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val negativeButton: Button = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            val editText: TextInputEditText = alertDialog.findViewById(R.id.text_input_layout)!!

            positiveButton.setOnClickListener {
                val query: String = editText.text.toString()
                if (query.isEmpty()) {
                    editText.error = getString(R.string.search_query_error_message)
                } else {
                    webView.findAllAsync(query)
                    dialog.dismiss()
                    bottomAppBar.replaceMenu(R.menu.web_view_menu)
                }
            }

            negativeButton.setOnClickListener {
                alertDialog.dismiss()
            }
        }
        dialog.show()
        setDialogGravity(dialog)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    var themeMode = WebSettings.FORCE_DARK_OFF

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun changeWebViewTheme() {
        if (themeMode == WebSettings.FORCE_DARK_ON) {
            webSettings.forceDark = WebSettings.FORCE_DARK_OFF
            themeMode = WebSettings.FORCE_DARK_OFF
        } else if (themeMode == WebSettings.FORCE_DARK_OFF) {
            webSettings.forceDark = WebSettings.FORCE_DARK_ON
            themeMode = WebSettings.FORCE_DARK_ON
        }
    }

    private fun findPreviousQuery() {
        webView.findNext(false)
    }

    private fun findNextQuery() {
        webView.findNext(true)
    }

    private fun clearMatchesAndSwitchMenu() {
        webView.clearMatches()
        bottomAppBar.replaceMenu(R.menu.bottom_app_bar_menu)
        invalidateMenuOptions()
    }

    private fun invalidateMenuOptions() {
        val item: MenuItem = bottomAppBar.menu.getItem(3)
        item.isVisible = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }

    private fun setDialogGravity(dialog: AlertDialog) {
        val params = dialog.window?.attributes
        params!!.gravity = Gravity.TOP
        dialog.window!!.attributes = params
    }
}