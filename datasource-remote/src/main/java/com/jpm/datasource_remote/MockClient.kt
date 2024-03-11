package com.jpm.datasource_remote

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.buffer
import okio.source
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject

class MockClient@Inject constructor(private val context: Context) :
    Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //val url = chain.request().url

        val modifiedUrl = chain.request().url.newBuilder().apply {
            //addQueryParameter("key", prefUtil.getString("api_key"))
        }.build()

        val newRequest = chain.request().newBuilder().url(modifiedUrl).build()

        val response: String? = readJsonFromAssets(context, "mockData/planet_list.json")

        return Response.Builder()
            .code(200)
            .message(response!!)
            .request(newRequest)
            .protocol(Protocol.HTTP_1_1)
            .body(response.toByteArray().toResponseBody())
            .addHeader("content-type", "application/json")
            .build()
    }

    private fun readJsonFromAssets(context: Context, filePath: String): String? {
        try {
            val source = context.assets.open(filePath).source().buffer()
            return source.readByteString().string(Charset.forName("utf-8"))

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}
