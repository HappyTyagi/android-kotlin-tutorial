package skilllotto.com.finvest.dilog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window

import butterknife.ButterKnife
import butterknife.OnClick
import skilllotto.com.finvest.R
import skilllotto.com.finvest.interfaceClass.InternetConnection
import skilllotto.com.finvest.view.LoginActivity

//import skilllotto.com.retailposmobile.R;
//import skilllotto.com.retailposmobile.activity.LoginActivity;
//import skilllotto.com.retailposmobile.util.ProjectUtil;

/**
 * Created by Vishwajeet Verma on 05-01-2018.
 */

class LogoutDialog : DialogFragment(), InternetConnection {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.logout, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    @OnClick(R.id.btn_cancel, R.id.btn_ok)
    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_cancel -> dismiss()
            R.id.btn_ok -> {


                val intent = Intent(activity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                activity.finish()
            }
        }
    }

    override fun netConnected() {

    }

    override fun netDisconnected() {

    }
}
