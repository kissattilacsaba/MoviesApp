package hu.bme.aut.movieapp.network

import okhttp3.*
import java.net.SocketTimeoutException


class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            return chain.proceed(chain.request())
        } catch (exception: SocketTimeoutException) {
            exception.printStackTrace()
        }

        return chain.proceed(chain.request())
    }
}