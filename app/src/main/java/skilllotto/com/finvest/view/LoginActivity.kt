package skilllotto.com.finvest.view

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import skilllotto.com.finvest.R
import android.content.BroadcastReceiver
import skilllotto.com.finvest.Utils.NetworkChangeReceiver
import android.net.ConnectivityManager
import android.content.IntentFilter
import android.os.Build
import android.widget.Toast
import butterknife.ButterKnife
import skilllotto.com.finvest.interfaceClass.InternetConnection
import android.content.SharedPreferences
import skilllotto.com.finvest.MyApplication
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.os.Handler
import android.support.v7.app.AlertDialog
import skilllotto.com.finvest.Custom_dilog.SweetAlertDialog
import dmax.dialog.SpotsDialog


class LoginActivity : AppCompatActivity(), InternetConnection {

    override fun netConnected() {

    }

    override fun netDisconnected() {
        val intent = Intent(this, NoInternet::class.java)
        intent.putExtra("class_ref", class_ref)
        startActivityForResult(intent, TEXT_REQUEST)
    }

    private val MY_PERMISSIONS_REQUEST = 1001
    var permissionsRequired = arrayOf<String>(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_PHONE_STATE)


    private var permissionStatus: SharedPreferences? = null
    val TEXT_REQUEST = 101;
    var class_ref = "";
    private var mNetworkReceiver: BroadcastReceiver? = null
    lateinit var progressDialog: AlertDialog
    private lateinit var pDialog: SweetAlertDialog
    private var i = -1

    @BindView(R.id.username)
    internal var user_name: EditText? = null

    @BindView(R.id.password)
    internal var password: EditText? = null

    @BindView(R.id.userlogin)
    internal var userlogin: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)
        pDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading")


        class_ref = "LoginActivity.class";
        mNetworkReceiver = NetworkChangeReceiver(class_ref)
        registerNetworkBroadcastForNougat()

//        userlogin?.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
        permissionStatus = getSharedPreferences("permissionStatus", MODE_PRIVATE);
        if (!MyApplication.getInstance().checkPermission()) {
            callPermissionGranted();
        }
        (findViewById(R.id.userlogin) as Button).setOnClickListener {
            //            pDialog.setTitleText("Login Sucessfully").changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
            dialog()
            pDialog.show();

            val handler = Handler()
            handler.postDelayed({
                pDialog.setTitleText("Sucess Done").changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
                pDialog.dismiss()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }, 2500)
        }
    }

    private fun registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    companion object {
        val info = "This is info"
        fun getMoreInfo(): String {
            return "This is more fun"
        }
    }

    protected fun unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    fun callPermissionGranted() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this@LoginActivity, permissionsRequired[0])
                || ActivityCompat.shouldShowRequestPermissionRationale(this@LoginActivity, permissionsRequired[1])
                || ActivityCompat.shouldShowRequestPermissionRationale(this@LoginActivity, permissionsRequired[2])
                || ActivityCompat.shouldShowRequestPermissionRationale(this@LoginActivity, permissionsRequired[3])) {

        } else if (permissionStatus!!.getBoolean(permissionsRequired[0], false)) {
        } else {
            ActivityCompat.requestPermissions(this, permissionsRequired, MY_PERMISSIONS_REQUEST)
        }
        val editor = permissionStatus?.edit()
        editor?.putBoolean(permissionsRequired[0], true)
        editor?.commit()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_REQUEST) {
            var allgranted = false
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    allgranted = true
                } else {
                    allgranted = false
                    break
                }
            }

            if (allgranted) {
                //proceedAfterPermission();
//                MyApplication.getInstance().setDbDataPermission()

            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this@LoginActivity, permissionsRequired[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(this@LoginActivity, permissionsRequired[1])
                    || ActivityCompat.shouldShowRequestPermissionRationale(this@LoginActivity, permissionsRequired[2])
                    || ActivityCompat.shouldShowRequestPermissionRationale(this@LoginActivity, permissionsRequired[3])) {

            } else {
                Toast.makeText(baseContext, "Unable to get Permission", Toast.LENGTH_LONG).show()
            }
        }
    }


    fun dialog() {
        pDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE).setTitleText("Loading")
        pDialog.setCancelable(false)
//      progressDialog = SpotsDialog(this, R.style.Custom)
        i++
        when (i) {
            0 -> pDialog.progressHelper.barColor = resources.getColor(R.color.blue_btn_bg_color)
            1 -> pDialog.progressHelper.barColor = resources.getColor(R.color.material_deep_teal_50)
            2 -> pDialog.progressHelper.barColor = resources.getColor(R.color.success_stroke_color)
            3 -> pDialog.progressHelper.barColor = resources.getColor(R.color.material_deep_teal_20)
            4 -> pDialog.progressHelper.barColor = resources.getColor(R.color.material_blue_grey_80)
            5 -> pDialog.progressHelper.barColor = resources.getColor(R.color.warning_stroke_color)
            6 -> pDialog.progressHelper.barColor = resources.getColor(R.color.success_stroke_color)
        }

    }
}
