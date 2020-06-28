package com.base.baseapplication.data.network

import com.base.baseapplication.data.network.models.Availability
import com.base.baseapplication.data.network.models.CarList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("availability")
    fun getAvailability(@Query("id") id: Int): Observable<Availability>


    @GET("cars")
    fun getCars(): Observable<CarList>

}