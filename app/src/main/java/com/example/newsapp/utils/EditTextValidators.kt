package com.example.newsapp.utils

import com.example.newsapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.validate(editText: TextInputEditText, chekPasswordLength:Boolean = false): Boolean{
   return if(editText.text.isNullOrEmpty()){
        error = context.getString(R.string.cannot_be_empty)
        false
    }else if(editText.text.toString().length<6&&chekPasswordLength){
       error = context.getString(R.string.too_short)
       false
    }else{
        true
    }
}