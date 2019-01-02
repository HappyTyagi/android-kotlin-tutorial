package skilllotto.com.aepsindifi.controller

import skilllotto.com.finvest.Model.EmiData

/**
 * Created by Vishwajeet Verma on 03-10-2017.
 */

interface PdcFrgOpenHelper {
    fun emi_no(cust_name: Int, emi_data: EmiData)
}
