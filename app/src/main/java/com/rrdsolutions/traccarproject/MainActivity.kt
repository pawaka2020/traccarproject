package com.rrdsolutions.traccarproject
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rrdsolutions.traccarproject.models.RetrofitRepo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            val url = "http://demo.traccar.org"

            RetrofitRepo(url).getDevices{
                textview.text = it
            }
        }



    }
}