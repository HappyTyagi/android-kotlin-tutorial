package skilllotto.com.finvest.view.Fragment

import android.os.Bundle;
import android.support.v4.app.Fragment
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import skilllotto.com.finvest.R
import skilllotto.com.finvest.view.MainActivity


class GranterInformation : Fragment() {
    private var loadItems: MainActivity? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.customer_regitration, container, false)
        loadItems = context as MainActivity

        return view;
    }
}