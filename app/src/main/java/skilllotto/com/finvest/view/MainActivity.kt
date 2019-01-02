package skilllotto.com.finvest.view

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import skilllotto.com.finvest.R
import skilllotto.com.finvest.Utils.NetworkChangeReceiver
import skilllotto.com.finvest.interfaceClass.InternetConnection
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import skilllotto.com.aepsindifi.controller.PdcFrgOpenHelper
import skilllotto.com.finvest.Model.EmiData
import skilllotto.com.finvest.Utils.ProjectUtil
import skilllotto.com.finvest.dilog.LogoutDialog
import skilllotto.com.finvest.view.Fragment.*
import java.util.ArrayList


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, InternetConnection, PdcFrgOpenHelper {
    override fun emi_no(cust_name: Int, emi_data: EmiData) {
        toolbar_nbame?.setText("PDC");
        orderList.clear()
        orderList.add(emi_data)
        bundle = Bundle()
        bundle?.putParcelableArrayList("datamodel", orderList as ArrayList<out Parcelable>)
        showFragment(PdcFrg(), true)

    }

    internal var orderList: MutableList<EmiData> = ArrayList<EmiData>()
    private var bundle: Bundle? = null
    var fab1: LinearLayout? = null
    var toolbar_nbame: TextView? = null
    var fab2: LinearLayout? = null
    internal lateinit var fab_open: Animation
    internal lateinit var fab_close: Animation
    internal lateinit var rotate_forward: Animation
    internal lateinit var rotate_backward: Animation
    private var isFabOpen: Boolean? = false
    val TEXT_REQUEST = 101;
    var class_ref = "";
    private var mNetworkReceiver: BroadcastReceiver? = null

    override fun netConnected() {

    }

    override fun netDisconnected() {
        val intent = Intent(this, NoInternet::class.java)
        intent.putExtra("class_ref", class_ref)
        startActivityForResult(intent, TEXT_REQUEST)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        ProjectUtil.hideKeyboard(this);
        fab1 = findViewById(R.id.fab1) as LinearLayout
        toolbar_nbame = findViewById(R.id.tool_name) as TextView
        fab2 = findViewById(R.id.fab2) as LinearLayout
        fab_open = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open)
        fab_close = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close)
        rotate_forward = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate_forward)
        rotate_backward = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate_backward)

        class_ref = "LoginActivity.class";
        mNetworkReceiver = NetworkChangeReceiver(class_ref)

        registerNetworkBroadcastForNougat()
        fab.setOnClickListener { view ->
            animateFAB()
        }

        fab1!!.setOnClickListener { view -> contact_us() }
        fab2!!.setOnClickListener { view -> contact_us() }


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        showFragment(CustmerGranterFrgament(), false)
        toolbar_nbame?.setText("Customer Information");
        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            val logoutDialog = LogoutDialog()
            logoutDialog.show(supportFragmentManager, "logout")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_Customer_infotmation -> {
                toolbar_nbame?.setText("Customer Information");
                showFragment(CustmerGranterFrgament(), false)
            }
            R.id.nav_verification -> {
                showFragment(VerificationFrg(), false)
                toolbar_nbame?.setText("Verification");

            }
            R.id.nav_pic_load -> {
                toolbar_nbame?.setText("Picture Upload");

            }
            R.id.nav_pdc -> {
                toolbar_nbame?.setText("PDC");
                showFragment(PdcFrg(), false)
            }

            R.id.nav_emi -> {
                toolbar_nbame?.setText("EMI");
                showFragment(EmiFrg(), false)
            }
            R.id.nav_vehical -> {
                toolbar_nbame?.setText("Vehical Details");
                showFragment(VehicalDetailsFrg(), false)

            }


            R.id.nav_logout -> {
                toolbar_nbame?.setText("Logout");
                finish()
            }

            R.id.nav_contact -> {
                contact_us()
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    fun showFragment(fragment: Fragment, addToBackStack: Boolean) {
        val fm = getSupportFragmentManager();
        val transaction = fm.beginTransaction()
        transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_left, R.anim.in_from_right, R.anim.out_to_right)
        transaction.replace(R.id.frame_container, fragment)
        if (!(bundle == null)) {
            fragment.setArguments(bundle)
        }
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    fun animateFAB() {

        if (this!!.isFabOpen!!) {
            fab.startAnimation(rotate_backward)
            fab1?.startAnimation(fab_close)
            fab2?.startAnimation(fab_close)
            fab1?.setClickable(false)
            fab2?.setClickable(false)
            isFabOpen = false
            Log.d("Raj", "close")

        } else {

            fab.startAnimation(rotate_forward)
            fab1?.startAnimation(fab_open)
            fab2?.startAnimation(fab_open)
            fab1?.setClickable(true)
            fab2?.setClickable(true)
            isFabOpen = true
            Log.d("Raj", "open")

        }
    }

    fun invisiableFloatingButton() {
        isFabOpen = false;
        fab.clearAnimation()
        fab.visibility = View.GONE
    }

    fun visiableFloatingButton() {
        fab.visibility = View.VISIBLE
    }

    fun contact_us() {
        animateFAB()
        invisiableFloatingButton()
        toolbar_nbame?.setText("Contact Us");
        showFragment(ContactUs(), false)
    }


}
