package com.example.zivcompose.ui.pages.first

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.zivcompose.databinding.ActivityFirstBinding
import com.example.zivcompose.ui.pages.main.MainActivity

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)

        hideScreenBar()
        lateToMain(1000)
        setContentView(binding.root)


    }


    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    private fun hideScreenBar(){
        //Android各种屏，刘海屏，打孔屏满屏显示: https://blog.csdn.net/cention168/article/details/124037221
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val lp: WindowManager.LayoutParams = window.attributes
            //用在android高版本满屏，穿过摄像头。
            lp.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = lp
        } else {
            window.requestFeature(Window.FEATURE_NO_TITLE)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        val decorView = window.decorView
        //View.SYSTEM_UI_FLAG_HIDE_NAVIGATION用来隐藏底部悬浮条
        //View.SYSTEM_UI_FLAG_HIDE_NAVIGATION用来隐藏底部悬浮条
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    private fun lateToMain(time:Long){
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            val intent = Intent(this,MainActivity().javaClass)
            startActivity(intent)
            finish()
        }, time) //延迟10秒执行

    }


}

