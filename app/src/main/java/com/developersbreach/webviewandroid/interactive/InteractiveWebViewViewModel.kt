package com.developersbreach.webviewandroid.interactive


import androidx.lifecycle.ViewModel

class InteractiveWebViewViewModel : ViewModel() {

    private var _urlLink = String()
    val urlLink: String
        get() = _urlLink

    init {
        _urlLink = "https://github.com/RajashekarRaju"
    }
}