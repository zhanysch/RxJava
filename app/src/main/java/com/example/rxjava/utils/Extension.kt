package com.example.rxjava.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

// этот клас для того чтобы не было полотно длинное в MainActivity
// поэтому код прописываеться для mainActivity  EditTexrtс помошью Extension


fun EditText.doAfterTextChanged(
  listener : (text: String)-> Unit   // замыкание создаетс для того чтобы вызвать этот метод в MainActivity
){

     val textWatcher = object : TextWatcher {   // слушатель listenera
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
         override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
         override fun afterTextChanged(s: Editable?) {
             s?.let { listener.invoke(it.toString()) } //invoke для замыкания , он активирует замыкание там где замыкание будет вызываться
         }
     }

     addTextChangedListener(textWatcher)    // присваивание листенера в edittext
}