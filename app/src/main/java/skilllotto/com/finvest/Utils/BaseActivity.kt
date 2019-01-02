package skilllotto.com.finvest.Utils

import android.app.ActivityManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import skilllotto.com.finvest.R
import skilllotto.com.finvest.view.NoInternet


abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun netConnected() {

    }

    fun netDisconnected() {
        val intent = Intent(this, NoInternet::class.java)
        intent.putExtra("class_ref", "")
        startActivity(intent);
    }
}