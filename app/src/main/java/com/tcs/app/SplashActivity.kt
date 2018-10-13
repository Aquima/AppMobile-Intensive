package com.tcs.app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_splash.*
import io.reactivex.disposables.Disposable

class SplashActivity : AppCompatActivity() {
    val name: String = "Jorge"
    var age: Int = 0
    var estatura:Double = 2.3 //868252
    var address: String? = null
    private lateinit var disposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        age = 4
        var suma = age + estatura
        btnTrace.setOnClickListener {
           Log.v("splash","onClick")
            val intent: Intent = Intent(this,LogInActivity::class.java)
            startActivity(intent)

        }
    }


}
