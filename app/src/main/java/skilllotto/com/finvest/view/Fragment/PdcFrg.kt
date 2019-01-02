package skilllotto.com.finvest.view.Fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle;
import android.os.Parcelable
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button
import android.widget.Toast
import skilllotto.com.finvest.Model.EmiData
import skilllotto.com.finvest.R
import skilllotto.com.finvest.adapter.PdcAdapter
import skilllotto.com.finvest.view.MainActivity
import android.content.Intent.getIntent


class PdcFrg : Fragment() {
    internal var orderList: ArrayList<EmiData> = ArrayList<EmiData>()

    private var loadItems: MainActivity? = null
    var submit: Button? = null
    private var recyclerView: RecyclerView? = null
    private var bundle: Bundle? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.pdc_frg, container, false)

        loadItems = context as MainActivity
        loadItems!!.invisiableFloatingButton();

        bundle = this.arguments
        if (bundle != null) {
            orderList = bundle?.getParcelableArrayList<EmiData>("datamodel")!!
        }

        recyclerView = view?.findViewById(R.id.recyclerView)
        var adapter = PdcAdapter(generateData())
        val layoutManager = LinearLayoutManager(activity)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.itemAnimator = DefaultItemAnimator()

        recyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()

        return view;
    }

    private fun generateData(): ArrayList<EmiData> {
        var result = ArrayList<EmiData>()
        var qty = 1;

        if (bundle != null) {
            qty = Integer.parseInt(orderList.get(0).total_emi)
        } else {
            qty = 1;
        }


        for (i in 1..qty) {
            var user: EmiData = EmiData("Bett", "Awesome work", "Awesome work", "Awesome work",
                    "Awesome work", "Awesome work")
            result.add(user)
        }

        return result
    }
}