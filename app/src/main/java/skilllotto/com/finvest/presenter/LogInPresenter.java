package skilllotto.com.finvest.presenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import skilllotto.com.finvest.interactor.LoadListener;
import skilllotto.com.finvest.interactor.MainInteractor;
import skilllotto.com.finvest.network.ApiClient;
import skilllotto.com.finvest.network.PosApiInterface;


public class LogInPresenter implements MainInteractor {

  String hashparam;


    public LogInPresenter(String param) {

        this.hashparam = param;
    }

    @Override
    public void loadItems(final LoadListener<Object> loadListener) {

        StringBuilder sb = null;
        PosApiInterface posApiInterface = ApiClient.getClient1(ApiClient.BASE_URL).create(PosApiInterface.class);
        posApiInterface.userLogin("application/json",hashparam)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<ResponseBody>>() {
                    @Override
                    public void call(Response<ResponseBody> defaultResponse) {
                        String finallyError = defaultResponse.body().toString();
                        try {
                            try {
                                BufferedReader br = new BufferedReader(new InputStreamReader(defaultResponse.body().byteStream()));
                                StringBuilder sb = new StringBuilder();
                                String read;
                                while ((read = br.readLine()) != null) {
                                    sb.append(read);
                                }

                                JSONObject jsonObj = null;
                                DefaultResponse defaultresponse;

                                jsonObj = new JSONObject(String.valueOf(sb));

                                try {
                                    defaultresponse = new DefaultResponse();
                                    defaultresponse.setLogin(jsonObj.getString("login"));
                                    defaultresponse.setError(jsonObj.getString("error"));


                                    if (sb.toString().contains(AppConstants.STATUS)) {
                                        loadListener.onSuccess(defaultresponse);
                                    } else {
                                        loadListener.onFailure(defaultresponse.getError());
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } catch (FileNotFoundException fnfe) {
                        } catch (IOException excpt) {
                        }


                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        loadListener.onFailure("Some error occurred");
                    }
                });
    }
}


//    StringBuilder sb = null;
//    PosApiInterface posApiInterface = ApiClient.getClient1(ApiClient.BASE_URL).create(PosApiInterface.class);
//        posApiInterface.login(hashparam)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<DefaultResponse>() {
//@Override
//public void call(DefaultResponse defaultResponse) {
//
//        if (defaultResponse.getLogin().equalsIgnoreCase(AppConstants.STATUS)) {
//        loadListener.onSuccess(defaultResponse);
//        } else {
//        loadListener.onFailure(defaultResponse.getError());
//        }
//        }
//        }, new Action1<Throwable>() {
//@Override
//public void call(Throwable throwable) {
//        loadListener.onFailure("Some error occurred");
//        }
//        });