package co.veradebv.catalogly.security

import okhttp3.CertificatePinner
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SslPinningConfig @Inject constructor() {

    val certificatePinner: CertificatePinner = CertificatePinner.Builder()
        .add("fakestoreapi.com", "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=")
        .build()
}