package com.tcs.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.tcs.app.Api.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {
    private var disposable: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        editTextEmail.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }
//cmF1bC5xdWlzcGVAbGl2ZS5jb206b2xva2I3aTg4
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })
        btnLogin.setOnClickListener{
            this.beginAuthorization()
        }

    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
    private fun beginAuthorization() {
        val mail:String = editTextEmail.text.toString()
        val pass:String = editTextPassword.text.toString()
        val apiServe = ApiClient.create(mail,pass)
        disposable = apiServe.authenticate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> Log.v("LogIn","Success ${result.email}") },
                        { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
                )
    }

}
