package dnyaneshwar.mekotlin.rxjavamvpwithkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import dnyaneshwar.mekotlin.rxjavamvpwithkotlin.adapter.UserListAdapter
import dnyaneshwar.mekotlin.rxjavamvpwithkotlin.beans.ResponseModel
import dnyaneshwar.mekotlin.rxjavamvpwithkotlin.model.PresenterImplementation
import dnyaneshwar.mekotlin.rxjavamvpwithkotlin.network.NetWorkConection
import dnyaneshwar.mekotlin.rxjavamvpwithkotlin.view_presenter.ViewPresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Response

class MainActivity : AppCompatActivity(), ViewPresenter.MainView {

    var presenterImplementation: PresenterImplementation? = null
    var linearLayoutManager: LinearLayoutManager? = null
    var mAdapter: UserListAdapter? = null
    var arrayList: ArrayList<ResponseModel.Datum>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        presenterImplementation = PresenterImplementation(this)
        presenterImplementation!!.loadData(1)

        btnTry.setOnClickListener { recreate() }
    }

    fun initUI() {
        linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemAnimator(DefaultItemAnimator())
        recyclerView.setLayoutManager(linearLayoutManager)
        recyclerView.setFitsSystemWindows(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterImplementation!!.onStop()
    }

    open override fun validateError() {
        Snackbar.make(llView, "Please check your internet connection", Snackbar.LENGTH_SHORT)
                .setAction("OK", View.OnClickListener { /*Take Action*/ }).show()

        llNoConnection.visibility = View.VISIBLE
    }

    override fun showProgressbar() {
        progressbar.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
        progressbar.visibility = View.GONE
    }

    override fun onSuccess(reposnseModel: Response<ResponseModel>) {
        if (reposnseModel.body() != null) {
            llNoConnection.visibility = View.GONE
            arrayList = reposnseModel.body()!!.data
            mAdapter = UserListAdapter(arrayList!!, this)
            recyclerView.adapter = mAdapter
        }
    }

    override fun onError(throwable: Throwable) {
        llNoConnection.visibility = View.VISIBLE
        toast("Something went wrong")
    }

    override fun checkInternet(): Boolean {
        return NetWorkConection.isNEtworkConnected(applicationContext)
    }
}
