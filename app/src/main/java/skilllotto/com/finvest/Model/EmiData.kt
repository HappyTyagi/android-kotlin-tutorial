package skilllotto.com.finvest.Model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
/**
 * Created by Deepak Tyagi on 22-11-2017.
 */

class EmiData : Parcelable{
    override fun writeToParcel(p0: Parcel?, p1: Int) {
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    constructor() {}
    constructor(case_date: String, total_cost: String, total_emi: String, imstallment_amount: String?, due_date: String, pay_mode: String) {
        this.case_date = case_date
        this.total_cost = total_cost
        this.total_emi = total_emi
        this.imstallment_amount = imstallment_amount
        this.due_date = due_date
        this.pay_mode = pay_mode

    }

    var case_date: String = ""
        var total_cost: String = ""
        var total_emi: String = ""
        var imstallment_amount: String? = ""
        var due_date: String = ""
        var pay_mode: String = ""
}
