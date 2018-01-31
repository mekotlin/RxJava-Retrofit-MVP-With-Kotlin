package dnyaneshwar.mekotlin.rxjavamvpwithkotlin.model

import dnyaneshwar.mekotlin.rxjavamvpwithkotlin.network.ApiClient
import dnyaneshwar.mekotlin.rxjavamvpwithkotlin.view_presenter.ViewPresenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Response

/**
 * Created by Dnyaneshwar Dalvi on 30/01/18.
 */
class PresenterImplementation : ViewPresenter.MainPresenter {

    var mainView: ViewPresenter.MainView? = null

    @NonNull
    var disposable: Disposable? = null

    constructor(mainView: ViewPresenter.MainView?) {
        this.mainView = mainView
    }

    override fun loadData(pageNumber: Int) {
        mainView!!.showProgressbar()

        if (mainView!!.checkInternet()) {

            disposable = ApiClient.instance
                    .getListOfUser(pageNumber.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ listResponse ->
                        mainView!!.hideProgressbar()
                        val responseCode = listResponse.code()
                        when (responseCode) {
                            200, 201, 202 -> { mainView!!.onSuccess(listResponse) }
                            401 -> { }
                            402 -> { }
                            500 -> { }
                            501 -> { }
                        }
                    }, { error ->
                        mainView!!.hideProgressbar()
                        if (error is HttpException) {
                            val response = (error as HttpException).response()
                            when (response.code()) {
                            //Responce Code
                            }
                        } else {
                            //Handle Other Errors
                        }
                        mainView!!.onError(error)
                    })
        } else {
            mainView!!.hideProgressbar()
            mainView!!.validateError()
        }
    }

    override fun onStop() {
        if (disposable != null) {
            disposable!!.dispose()
        }
    }
}
