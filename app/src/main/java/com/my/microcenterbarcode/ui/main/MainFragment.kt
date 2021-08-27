package com.my.microcenterbarcode.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.my.microcenterbarcode.R

import kotlinx.android.synthetic.main.main_fragment_linear_layout.*

import com.aspose.barcode.generation.BarcodeGenerator
import com.aspose.barcode.*

class MainFragment : Fragment() {

    val userKey = "USER"
    val pwKey = "PW"

    companion object {
        fun newInstance() = MainFragment()
    }

//    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment_linear_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userName = activity?.getPreferences(Context.MODE_PRIVATE)?.getString(userKey, null) ?: userNameField.text.toString()
        val pw = activity?.getPreferences(Context.MODE_PRIVATE)?.getString(pwKey, null) ?: passwordField.text.toString()
        userNameField.setText(userName)
        passwordField.setText(pw)
//        if (userName != null && pw != null) {
//            userNameField.setText(userName)
//            passwordField.setText(pw)
//        }
        button.setOnClickListener(
            View.OnClickListener { updateCredentials() }
        )
        generateBarcode(userName, pw)

//        view.findViewById<ImageView>(R.id.floatingActionButton).setOnClickListener {
//            viewModel.getUsers().observe(viewLifecycleOwner, Observer<List<String>>{ users ->
//                view.findViewById<TextView>(R.id.message).text = users[0]
//            })
//        }
//        view.findViewById<ImageView>(R.id.floatingActionButton).setOnClickListener {
//            view.findViewById<TextView>(R.id.message).text = viewModel.getuserName()
//        }


    }

    fun updateCredentials() {
        val userName = userNameField.text.toString()
        val pw = passwordField.text.toString()
        activity?.getPreferences(Context.MODE_PRIVATE)?.edit()?.putString(userKey, userName)?.apply()
        activity?.getPreferences(Context.MODE_PRIVATE)?.edit()?.putString(pwKey, pw)?.apply()
        generateBarcode(userName, pw)
    }

    fun generateBarcode(userName: String, pw: String) {
        userNameBarcode.setImageBitmap(
            BarcodeGenerator(EncodeTypes.CODE_128, userName).generateBarCodeImage()
        )
        passwordBarcode.setImageBitmap(
            BarcodeGenerator(EncodeTypes.CODE_128, pw).generateBarCodeImage()
        )
    }
}