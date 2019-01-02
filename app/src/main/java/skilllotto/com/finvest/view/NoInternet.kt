package skilllotto.com.finvest.view

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Button
import android.widget.TextView
import butterknife.ButterKnife
import skilllotto.com.finvest.R
import skilllotto.com.finvest.Utils.NetworkChangeReceiver
import skilllotto.com.finvest.interfaceClass.InternetConnection

class NoInternet : AppCompatActivity(), InternetConnection {
    override fun netConnected() {
        unregisterNetworkChanges();
        finish();
    }

    override fun netDisconnected() {
    }

    var submit: Button? = null
    val TEXT_REQUEST = 101;
    var class_ref = "";
    private var mNetworkReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)
        ButterKnife.bind(this)

        submit = findViewById(R.id.search) as Button
        submit!!.setOnClickListener { view ->

            val snack = Snackbar.make(view, "Internet unavailable ! please check your internet setting and try again", Snackbar.LENGTH_LONG)

            // change action button text color
            snack.setActionTextColor(Color.parseColor("#BB4444"))

            // snackbar background color
            snack.view.setBackgroundColor(Color.parseColor("#FFFFFF"))
            snack.view.setPadding(1,1,1,1);
            val textView = snack.view.findViewById(android.support.design.R.id.snackbar_text) as TextView
            // change Snackbar text color
            textView.setTextColor(Color.parseColor("#000000"))

            snack.show()
//        }
//    }

        }


        mNetworkReceiver = NetworkChangeReceiver(class_ref)
        registerNetworkBroadcastForNougat()
    }

    override fun onBackPressed() {

    }
    private fun registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    protected fun unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }

    }
}
