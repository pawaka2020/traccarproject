package com.rrdsolutions.traccarproject.models

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    //Use Response, not Call
    @GET("/api/notifications/types")
    suspend fun getNotificationTypes():Response<List<NotificationType>>

    @GET("/api/devices")
    suspend fun getDevices(): Response<List<Device>>

    @GET("/api/statistics")
    fun getStatistics(@Query("from") from: String,@Query("to") to:String):Call<Statistics>

    @GET("/api/statistics")
    suspend fun getStatistics2(@Query("from") from: String,@Query("to") to:String):Response<Statistics>
}

class BasicAuthInterceptor(username: String, password: String): Interceptor {
    private var credentials: String = Credentials.basic(username, password)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()

        return chain.proceed(request)
    }
}
class RetrofitRepo(val url:String){
    companion object ApiAdapter{ lateinit var client:ApiClient }
    init{
        val clientWithAuth = OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor("traccar123@gmail.com", "qwerty12345"))
            .build()

        client = Retrofit.Builder()
            .baseUrl(url)
            .client(clientWithAuth)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }

    fun getNotification(callback:(List<NotificationType>)->Unit) {
        lateinit var result:List<NotificationType>
        GlobalScope.launch{
            client.getNotificationTypes().let{
                if (it.isSuccessful && it.body()!= null){
                    Log.d("_Retrofit", "Successful")
                    result = it.body()!!
                }
                else{
                    Log.d("_Retrofit", "Fail")
                    result = listOf(NotificationType("null"))
                }
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }

    fun getDevices(callback:(String)->Unit){
        var result=listOf<Device>()
        var result2 = ""
        GlobalScope.launch{
            client.getDevices().let{

                if (it.isSuccessful){
                    Log.d("_Retrofit", "Successful")
                    result = it.body()!!
                }
                else{
                    Log.d("_Retrofit", "Fail")
                }
            }
            withContext(Dispatchers.Main) {
                for (element in result){
                    result2 += element.toString()
                }
                callback(result2)
            }
        }
    }

    fun getStatistics(callback:(Statistics)->Unit){
        val to = "2020-08-03T07:57:53.078637"
        val from = "2020-08-02T07:57:53.078637"

        val statistics = client.getStatistics(from, to)
        statistics.enqueue(object: Callback<Statistics>{
            override fun onFailure(call: Call<Statistics>, t: Throwable) {
                Log.d("_Retrofit", "Failure")
            }

            override fun onResponse(call: Call<Statistics>, response: Response<Statistics>) {
                if (response.code() == 200) {
                    Log.d("_Retrofit", "Successful")
                    callback(response.body()!!)
                }
            }

        })
    }

    
}

