package com.alereavila.series

import android.os.Bundle



import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_json_post.view.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.set
class JsonPostActivity : AppCompatActivity() {
    private var domainName = "http://api.tvmaze.com"
    var serviceURL = "http://api.tvmaze.com/search/shows?q=girls"
    private var locationItemAdapter: LocationAdapter? = null
    private var locationRecyclerView: RecyclerView? = null
    private var searchEditText: EditText? = null
    private var searchButton: Button? = null
    var resultList: ArrayList<LocationModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity_json_layout = layoutInflater.inflate(R.layout.activity_json_post, null)
        setContentView(activity_json_layout)
        title = "Volley In Kotlin - Post Request Demo"
        setupRecyclerView(activity_json_layout)
        searchEditText = activity_json_layout.txt_search as EditText
        searchButton = activity_json_layout.btn_request as Button
        searchButton!!.setOnClickListener {
            var searchStr = ""
            searchStr = searchEditText!!.text.toString()
            sendRequest(searchStr)
        }
    }
    private fun setupRecyclerView(activity_json_layout: View) {
        locationRecyclerView = activity_json_layout.recycler_view_post as RecyclerView
        var mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        locationRecyclerView!!.layoutManager = mLayoutManager
    }
    private fun sendRequest(searchStr: String) {
        if (searchStr.isNullOrEmpty()) {
            Toast.makeText(applicationContext, "Enter Search String!", Toast.LENGTH_SHORT).show();
        } else {
            val oRequest = object : StringRequest(Request.Method.POST, serviceURL, Response.Listener { response ->
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
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["seed"] = "search"
                    params["str"] = searchStr
                    return params
                }
            }
            val requestQ = Volley.newRequestQueue(this)
            requestQ.add(oRequest)
        }
    }
}
