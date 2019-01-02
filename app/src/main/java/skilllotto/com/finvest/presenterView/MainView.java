package skilllotto.com.finvest.presenterView;

/**
 * Created by Deepak Tyagi on 27-09-2017.
 */

public interface MainView
{
    void showLoadingLayout();
    void hideLoadingLayout();
    void showSuccess(Object object);
    void showFailure(String error);
}

