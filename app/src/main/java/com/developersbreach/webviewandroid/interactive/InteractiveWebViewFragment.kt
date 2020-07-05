package com.developersbreach.webviewandroid.interactive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.developersbreach.webviewandroid.R

class InteractiveWebViewFragment : Fragment() {

    companion object {
        fun newInstance() = InteractiveWebViewFragment()
    }

    private lateinit var viewModel: InteractiveWebViewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_interactive_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar_interactive_fragment).setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InteractiveWebViewViewModel::class.java)

    }

}