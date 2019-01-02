package skilllotto.com.finvest.view.Fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle;
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button
import android.widget.Toast
import skilllotto.com.finvest.R


class ContactUs : Fragment() {

    var submit: Button? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.contact_us, container, false)
        submit = view?.findViewById(R.id.call) as Button
        submit!!.setOnClickListener(View.OnClickListener {
            val i = Intent(Intent.ACTION_CALL)
            i.data = Uri.parse("tel:+911244371600")
            Toast.makeText(activity, "Calling...", Toast.LENGTH_SHORT).show()
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                return@OnClickListener
            }
            startActivity(i)
        })
        return view;
    }
}