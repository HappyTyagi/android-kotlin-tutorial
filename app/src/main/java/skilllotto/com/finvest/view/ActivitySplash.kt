package skilllotto.com.finvest.view

import android.animation.Animator
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_splash.*
import skilllotto.com.finvest.R
import skilllotto.com.finvest.Utils.NetworkChangeReceiver
import skilllotto.com.finvest.interfaceClass.InternetConnection


class ActivitySplash : AppCompatActivity(), InternetConnection {
    lateinit var springForce: SpringForce
    val TEXT_REQUEST = 101;
    var class_ref = "";
    private var mNetworkReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        ButterKnife.bind(this)
        mNetworkReceiver = NetworkChangeReceiver(class_ref)
        registerNetworkBroadcastForNougat()
    }

    override fun netConnected() {
        unregisterNetworkChanges();
        animation()
    }

    override fun netDisconnected() {
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, NoInternet::class.java)
            intent.putExtra("class_ref", class_ref)
            startActivityForResult(intent, TEXT_REQUEST)

        }, 2500)
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

    private fun startActivity() {
        try {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            unregisterNetworkChanges();
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }

    }

     fun animation() {
        try {
            Handler().postDelayed({
                //do stuff
                //Like your Background calls and all
                springForce = SpringForce(0f)
                relative_layout.pivotX = 0f
                relative_layout.pivotY = 0f
                val springAnim = SpringAnimation(relative_layout, DynamicAnimation.ROTATION).apply {
                    springForce.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
                    springForce.stiffness = SpringForce.STIFFNESS_VERY_LOW
                }
                springAnim.spring = springForce
                springAnim.setStartValue(80f)
                springAnim.addEndListener(object : DynamicAnimation.OnAnimationEndListener {
                    override fun onAnimationEnd(animation: DynamicAnimation<out DynamicAnimation<*>>?, canceled: Boolean, value: Float, velocity: Float) {
                        val displayMetrics = DisplayMetrics()
                        windowManager.defaultDisplay.getMetrics(displayMetrics)
                        val height = displayMetrics.heightPixels.toFloat()
                        val width = displayMetrics.widthPixels
                        relative_layout.animate()
                                .setStartDelay(1)
                                .translationXBy(width.toFloat() / 2)
                                .translationYBy(height)
                                .setListener(object : Animator.AnimatorListener {
                                    override fun onAnimationRepeat(p0: Animator?) {
                                    }
                                    override fun onAnimationEnd(p0: Animator?) {
                                        val intent = Intent(applicationContext, LoginActivity::class.java)
                                        finish()
                                        startActivity(intent)
                                        overridePendingTransition(0, 0)
                                    }

                                    override fun onAnimationCancel(p0: Animator?) {

                                    }

                                    override fun onAnimationStart(p0: Animator?) {

                                    }

                                })
                                .setInterpolator(DecelerateInterpolator(1f))
                                .start()
                    }
                })
                springAnim.start()
            }, 3000)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }

    }
}