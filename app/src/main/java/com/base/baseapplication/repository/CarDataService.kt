package com.base.baseapplication.repository

import com.base.baseapplication.data.network.models.Availability
import com.base.baseapplication.data.network.models.CarList
import io.reactivex.Observable

interface CarDataService {

    fun getAvailability(id: Int): Observable<Availability>

    fun getCars(): Observable<CarList>

}