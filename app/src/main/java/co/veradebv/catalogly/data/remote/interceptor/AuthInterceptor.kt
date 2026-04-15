package co.veradebv.catalogly.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Add auth headers to every request
        // For now this is a placeholder — swap with real token logic later
        // e.g. read from DataStore, append as Bearer token
        val authenticatedRequest = originalRequest.newBuilder()
            .header("Accept", "application/json")
            //.header("Authorization", "Bearer $token")  ← uncomment when you have auth }
            .build()

        return chain.proceed(authenticatedRequest)
    }
}