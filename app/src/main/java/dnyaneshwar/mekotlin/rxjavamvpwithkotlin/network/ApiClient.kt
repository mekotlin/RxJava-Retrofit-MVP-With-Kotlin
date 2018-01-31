package dnyaneshwar.mekotlin.rxjavamvpwithkotlin.network


import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dnyaneshwar.mekotlin.rxjavamvpwithkotlin.beans.ResponseModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Dnyaneshwar Dalvi on 30/01/18.
 */
class ApiClient {

    private val myAppService: GetDataServices

    init {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val clientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        clientBuilder.addInterceptor(loggingInterceptor)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        myAppService = retrofit.create(GetDataServices::class.java)
    }

    companion object {
        private val BASE_URL = "https://reqres.in/"
        private var apiClient: ApiClient? = null
        /**
         * Gets my app client.
         *
         * @return the my app client
         */
        val instance: ApiClient get() {
                if (apiClient == null) {
                    apiClient = ApiClient()
                }
                return apiClient as ApiClient
            }
    }

    /**
     * Gets list of user.
     *
     * @return the list of user
     */
    fun getListOfUser(pageNumber: String): Observable<Response<ResponseModel>> {
        return myAppService.getList(pageNumber)
    }
}
