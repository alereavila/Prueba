package com.alereavila.series

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.view.*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainLayout = layoutInflater.inflate(R.layout.activity_main, null)
        setContentView(mainLayout)
        mainLayout.btn_get_request.setOnClickListener { startGetJsonActivity() }
        mainLayout.btn_post_request.setOnClickListener { startPostJsonActivity() }
    }
    private fun startGetJsonActivity() {
        val intent = Intent(applicationContext, JsonGetActivity::class.java)
        startActivity(intent)
    }
    private fun startPostJsonActivity() {
        val intent = Intent(applicationContext, JsonPostActivity::class.java)
        startActivity(intent)
    }
}
