package dnyaneshwar.mekotlin.rxjavamvpwithkotlin.network

import dnyaneshwar.mekotlin.rxjavamvpwithkotlin.beans.ResponseModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Dnyaneshwar Dalvi on 30/01/18.
 */
interface GetDataServices {

    @GET("api/users?")
    fun getList(@Query("page") pages: String): Observable<Response<ResponseModel>>
}