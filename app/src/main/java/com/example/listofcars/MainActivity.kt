package com.example.listofcars

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

val CARS_ENDPONT = "https://development.espark.lt/api/mobile/public/availablecars"


class MainActivity : AppCompatActivity() {
    private val cars = ArrayList<Car>()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter:  CarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        adapter = CarAdapter(cars)
        recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        getCars()
    }

    fun getCars() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, CARS_ENDPONT,
            Response.Listener<String> { response ->

                Log.d("test", "good")
                var strResp = response.toString()
                Log.d("test", strResp)
                val carArray: JSONArray = JSONArray(strResp)
                var car: Car?
                for (i in 0 until carArray.length()) {
                    var jsonInner: JSONObject = carArray.getJSONObject(i)
                    var location = JSONObject(jsonInner["location"].toString())
                    var model = JSONObject(jsonInner["model"].toString())
                    car = Car(
                        jsonInner["id"].toString().toInt(),
                        jsonInner["plateNumber"].toString(),
                        location["latitude"].toString().toFloat(),
                        location["longitude"].toString().toFloat(),
                        jsonInner["batteryPercentage"].toString().toInt(),
                        model["photoUrl"].toString()
                    )
                    cars.add(car)
                    adapter.notifyItemInserted(cars.size)
                }
            },
            Response.ErrorListener {response ->
                Log.d("test", response.toString())
            })
        queue.add(stringReq)
    }
}
