package skilllotto.com.finvest.view.Fragment

import android.os.Bundle;
import android.support.v4.app.Fragment
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import skilllotto.com.finvest.R


class PicUploadFrg : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.pic_upload, container, false)
    }
}