package com.demo.data.repository

import android.util.Log
import com.demo.vdemotask.core.common.ExampleErrorResponse
import com.demo.vdemotask.core.common.Resource
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

import java.io.IOException

abstract class BaseRepo() {

    suspend fun <T> safeApiCall(apiToBeCalled: suspend() -> Response<T>): Resource<T> {

        // Returning api response
        // wrapped in Resource class
        return withContext(Dispatchers.IO) {
            try {

                // Here we are calling api lambda
                // function that will return response
                // wrapped in Retrofit's Response class
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful) {
                    Log.d(TAG, "safeApiCall: success" )
                    // In case of success response we
                    // are returning Resource.Success object
                    // by passing our data in it.
                    Resource.Success(data = response.body()!!)
                } else {
                    Log.d(TAG, "safeApiCall: fail" )
                    // parsing api's own custom json error
                    // response in ExampleErrorResponse pojo
                    val errorResponse: ExampleErrorResponse? = convertErrorBody(response.errorBody())
                    // Simply returning api's own failure message
                    Resource.Error(Exception(errorResponse?.failureMessage ?: "Something went wrong"))
                }

            } catch (e: HttpException) {
                Log.d(TAG, "safeApiCall: exception")

                // Returning HttpException's message
                // wrapped in Resource.Error
                Resource.Error(Exception( e.message ?: "HttpException"))
            } catch (e: IOException) {
                // Returning no internet message
                // wrapped in Resource.Error
                Resource.Error(Exception( e.message ?: "IOException"))
            } catch (e: Exception) {
                // Returning 'Something went wrong' in case
                // of unknown error wrapped in Resource.Error
                Resource.Error(Exception( e.message ?: "Exception"))
            }
        }
    }

    // If you don't wanna handle api's own
    // custom error response then ignore this function
    private fun convertErrorBody(errorBody: okhttp3.ResponseBody?): ExampleErrorResponse? {
        return try {
            errorBody?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ExampleErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }

    companion object {
        // we'll use this function in all
        // repos to handle api errors.
        private const val TAG = "BaseRepo"
    }
}