package skilllotto.com.finvest.Utils


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import skilllotto.com.finvest.interfaceClass.InternetConnection

class NetworkChangeReceiver(class_ref: String) : BroadcastReceiver() {
    private var loadItems: InternetConnection? = null


    override fun onReceive(context: Context, intent: Intent) {
        run {
            try {

                loadItems = context as InternetConnection

                if (isOnline(context)) {
                    loadItems!!.netConnected()
                    Log.e("keshav", "Online Connect Intenet ")
                } else {
                    loadItems!!.netDisconnected()
                    Log.e("keshav", "Conectivity Failure !!! ")
                }


            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
    }

    private fun isOnline(context: Context): Boolean {
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            //should check null because in airplane mode it will be null
            return netInfo != null && netInfo.isConnected
        } catch (e: NullPointerException) {
            e.printStackTrace()
            return false
        }

    }
}



