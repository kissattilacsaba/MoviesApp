package hu.bme.aut.movieapp.network

import hu.bme.aut.movieapp.BuildConfig
import okhttp3.*

abstract class RequestInterceptor : Interceptor {
   /* override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            //val uri = chain.request().url().uri().toString()
            val responseString = MovieResponseJson
            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message("Success")
                .body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                    responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
        }
        else {
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes and " +
                    "bound to be used only with DEBUG mode")
        }
    }*/
}

const val MovieResponseJson = """
[{
	"Title": "Jurassic Park",
	"Year": "1993",
	"Director": "Steven Spielberg",
	"Writer": "Michael Crichton, David Koepp"
}]
"""