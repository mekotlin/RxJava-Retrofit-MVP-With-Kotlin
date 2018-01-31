package dnyaneshwar.mekotlin.rxjavamvpwithkotlin.view_presenter

import dnyaneshwar.mekotlin.rxjavamvpwithkotlin.beans.ResponseModel
import retrofit2.Response

/**
 * Created by Dnyaneshwar Dalvi on 30/01/18.
 */
interface ViewPresenter {

    interface MainView {

        fun validateError()
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(reposnseModel: Response<ResponseModel>)
        fun onError(throwable: Throwable)
        fun checkInternet(): Boolean
    }

    interface MainPresenter {
        fun loadData(pageNumber: Int)
        fun onStop()
    }
}