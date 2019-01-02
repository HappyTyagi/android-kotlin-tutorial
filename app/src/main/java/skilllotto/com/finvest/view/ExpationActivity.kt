package skilllotto.com.finvest.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import butterknife.ButterKnife
import skilllotto.com.finvest.R

class ExpationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expation)
        ButterKnife.bind(this)
    }
}
