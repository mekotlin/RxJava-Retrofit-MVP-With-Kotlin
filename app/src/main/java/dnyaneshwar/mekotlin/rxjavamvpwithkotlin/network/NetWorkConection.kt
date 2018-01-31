package dnyaneshwar.mekotlin.rxjavamvpwithkotlin.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Dnyaneshwar Dalvi on 30/01/18.
 */
class NetWorkConection {

    companion object {
        @SuppressLint("MissingPermission")
        fun isNEtworkConnected(context: Context): Boolean {

            var connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectionManager.activeNetworkInfo

            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }
    }
}
