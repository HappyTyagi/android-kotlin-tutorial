package skilllotto.com.finvest.view.Fragment

import android.os.Bundle;
import android.support.v4.app.Fragment
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import skilllotto.com.finvest.R


class CustmerGranterFrgament : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        showFragment(CustomerInformation(), true)
        return inflater?.inflate(R.layout.main_frg, container, false)

    }


    fun showFragment(fragment: Fragment, addToBackStack: Boolean) {
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()
        transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_left, R.anim.in_from_right, R.anim.out_to_right)
        transaction.replace(R.id.container, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }
}