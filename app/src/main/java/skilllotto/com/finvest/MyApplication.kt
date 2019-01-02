package skilllotto.com.finvest

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.multidex.MultiDex
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatDelegate


/**
 * Created by Deepak Tyagi on 30-10-2017.
 */

class MyApplication : Application() {


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        //  FirebaseCrash.report(new Exception("App Name : My first Android non-fatal error"));
        instance = this
        checkPermission()
        //SQLiteDatabase.loadLibs(getApplicationContext());

    }

    fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    companion object {
        private var instance: MyApplication? = null

        @Synchronized
        fun getInstance(): MyApplication {
            if (instance == null) {
                instance = MyApplication()
            }
            return instance as MyApplication
        }
    }

}
