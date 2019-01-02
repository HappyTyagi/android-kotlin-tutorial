package skilllotto.com.finvest.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable


interface PosApiInterface {

    @FormUrlEncoded
    @POST("user.php")
    fun signup(@FieldMap title: Map<String, String>): Observable<Response<ResponseBody>>

}
