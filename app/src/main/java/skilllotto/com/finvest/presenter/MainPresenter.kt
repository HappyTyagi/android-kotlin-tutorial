package skilllotto.com.finvest.presenter

/**
 * Created by Vishwajeet Verma on 27-09-2017.
 */

interface MainPresenter<T> {
    fun init()
    fun subscribe(t: T)
    fun unSubscribe()
}
