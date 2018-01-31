package dnyaneshwar.mekotlin.rxjavamvpwithkotlin.beans

/**
 * Created by Dnyaneshwar Dalvi on 30/01/18.
 */

data class ResponseModel(var page: Int, var per_page: Int, var total: Int, var total_pages: Int, var `data`: ArrayList<Datum>) {
    inner class Datum(var id: Int, var first_name: String, var last_name: String, var avatar: String)
}