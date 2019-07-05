package com.alereavila.series
import android.os.Bundle



import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_json_get.view.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*



class JsonGetActivity : AppCompatActivity() {
    private var domainName = "http://api.tvmaze.com"
    var serviceURL = "http://api.tvmaze.com/search/shows?q=girls"
    private var locationItemAdapter: LocationAdapter? = null
    private var locationRecyclerView: RecyclerView? = null
    var resultList: ArrayList<LocationModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity_json_layout = layoutInflater.inflate(R.layout.activity_json_get, null)
        setContentView(activity_json_layout)
        title = "Volley In Kotlin - Get Request Demo"
        locationRecyclerView = activity_json_layout.recycler_view as RecyclerView
        var mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        locationRecyclerView!!.layoutManager = mLayoutManager
        sendRequest("400001")
    }
    private fun sendRequest(searchStr: String) {
        serviceURL += searchStr //append search string to the querystring
        val oRequest = object : StringRequest(Request.Method.GET, serviceURL, Listener { response ->
            try {
                val locationArray = JSONArray(response)
                resultList = ArrayList(locationArray.length())
                for (i in 0 until locationArray.length()) {
                    val loc = locationArray.get(i) as JSONObject
                    resultList.add(LocationModel(
                        loc.getString("id"),
                        loc.getString("name"),
                        loc.getString("season"),
                        loc.getString("number"),
                        loc.getString("airtime"),
                        loc.getString("airdate")
                    ))
                }
                locationItemAdapter = LocationAdapter(applicationContext, resultList)
                locationRecyclerView!!.setAdapter(locationItemAdapter)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(applicationContext, "Error: " + (error.toString()), Toast.LENGTH_SHORT).show();
        }) {
            //No params
        }
        val requestQ = Volley.newRequestQueue(this)
        requestQ.add(oRequest)
    }
}