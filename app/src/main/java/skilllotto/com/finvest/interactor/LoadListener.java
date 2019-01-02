package skilllotto.com.finvest.interactor;

/**
 * Created by Deepak Tyagi on 12-10-2017.
 */

public interface LoadListener<T>
{

    void onSuccess(T t);
    void onFailure(String errorMessage);

}
