package skilllotto.com.finvest.view.Fragment


import android.os.Bundle;
import android.support.v4.app.Fragment
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver
import android.widget.ArrayAdapter
import android.widget.ScrollView
import android.widget.Spinner
import skilllotto.com.finvest.R
import skilllotto.com.finvest.view.MainActivity


class VehicalDetailsFrg : Fragment() {
    var vehical_type: Spinner? = null
    private var loadItems: MainActivity? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.vehical_details_frg, container, false)
        loadItems = context as MainActivity

        val scroll_view = view?.findViewById(R.id.scroll_view) as ScrollView
        vehical_type = view?.findViewById(R.id.vehical_type) as Spinner
        vehical_type?.adapter = ArrayAdapter(activity, R.layout.drop_down, resources.getStringArray(R.array.vehical_type))
        scroll_view.getViewTreeObserver().addOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener {
            val scrollY = scroll_view.getScrollY() //for verticalScrollView
            if (scrollY == 0)
                loadItems!!.visiableFloatingButton();
            else
                loadItems!!.invisiableFloatingButton();
        })


        return view;
    }
}