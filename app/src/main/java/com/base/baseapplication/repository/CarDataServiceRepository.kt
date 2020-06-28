package com.base.baseapplication.repository

import com.base.baseapplication.data.network.ApiService
import com.base.baseapplication.data.network.models.Availability
import com.base.baseapplication.data.network.models.Car
import com.base.baseapplication.data.network.models.CarList
import io.reactivex.Observable
import javax.inject.Inject

class CarDataServiceRepository @Inject constructor(private val apiService : ApiService): CarDataService {

    override fun getAvailability(id: Int): Observable<Availability> {
        when(id) {
            1 -> return Observable.just(Availability.IN_DEALERSHIP)
            2 -> return Observable.just(Availability.OUT_OF_STOCK)
            3 -> return Observable.just(Availability.UNAVAILABLE)

            4 -> return Observable.just(Availability.IN_DEALERSHIP)
            5 -> return Observable.just(Availability.OUT_OF_STOCK)
            6 -> return Observable.just(Availability.UNAVAILABLE)

            7 -> return Observable.just(Availability.IN_DEALERSHIP)
            8 -> return Observable.just(Availability.OUT_OF_STOCK)
            9 -> return Observable.just(Availability.UNAVAILABLE)

            10 -> return Observable.just(Availability.IN_DEALERSHIP)
        }

        return Observable.just(Availability.UNAVAILABLE)
//        return apiService.getAvailability()
    }

    override fun getCars(): Observable<CarList> {

        val carList = CarList()
        carList.carList.add(Car(1, "https://media.wired.com/photos/5d09594a62bcb0c9752779d9/master/w_2560%2Cc_limit/Transpo_G70_TA-518126.jpg",
                "Car name 1",
                "Car maker 1",
                "Car model 1",
                "2018",
                Availability.OUT_OF_STOCK))

        carList.carList.add(Car(2, "https://article.images.consumerreports.org/f_auto/prod/content/dam/CRO%20Images%202018/Cars/November/CR-Cars-InlineHero-2019-Honda-Insight-driving-trees-11-18",
                "Car name 2",
                "Car maker 2",
                "Car model 2",
                "2018",
                Availability.IN_DEALERSHIP))

        carList.carList.add(Car(3, "https://media.wired.com/photos/5d09594a62bcb0c9752779d9/master/w_2560%2Cc_limit/Transpo_G70_TA-518126.jpg",
                "Car name 3",
                "Car maker 3",
                "Car model 3",
                "2018",
                Availability.IN_DEALERSHIP))

        carList.carList.add(Car(4, "https://article.images.consumerreports.org/f_auto/prod/content/dam/CRO%20Images%202018/Cars/November/CR-Cars-InlineHero-2019-Honda-Insight-driving-trees-11-18",
                "Car name 4",
                "Car maker 4",
                "Car model 4",
                "2018",
                Availability.OUT_OF_STOCK))

        carList.carList.add(Car(5, "https://media.wired.com/photos/5d09594a62bcb0c9752779d9/master/w_2560%2Cc_limit/Transpo_G70_TA-518126.jpg",
                "Car name 5",
                "Car maker 5",
                "Car model 5",
                "2018",
                Availability.UNAVAILABLE))

        carList.carList.add(Car(6, "https://article.images.consumerreports.org/f_auto/prod/content/dam/CRO%20Images%202018/Cars/November/CR-Cars-InlineHero-2019-Honda-Insight-driving-trees-11-18",
                "Car name 6",
                "Car maker 6",
                "Car model 6",
                "2017",
                Availability.IN_DEALERSHIP))

        carList.carList.add(Car(7, "https://media.wired.com/photos/5d09594a62bcb0c9752779d9/master/w_2560%2Cc_limit/Transpo_G70_TA-518126.jpg",
                "Car name 7",
                "Car maker 7",
                "Car model 7",
                "1950",
                Availability.UNAVAILABLE))

        carList.carList.add(Car(8, "https://article.images.consumerreports.org/f_auto/prod/content/dam/CRO%20Images%202018/Cars/November/CR-Cars-InlineHero-2019-Honda-Insight-driving-trees-11-18",
                "Car name 8",
                "Car maker 8",
                "Car model 8",
                "2018",
                Availability.IN_DEALERSHIP))

        carList.carList.add(Car(9, "https://media.wired.com/photos/5d09594a62bcb0c9752779d9/master/w_2560%2Cc_limit/Transpo_G70_TA-518126.jpg",
                "Car name 9",
                "Car maker 9",
                "Car model 9",
                "2018",
                Availability.OUT_OF_STOCK))

        carList.carList.add(Car(10, "https://article.images.consumerreports.org/f_auto/prod/content/dam/CRO%20Images%202018/Cars/November/CR-Cars-InlineHero-2019-Honda-Insight-driving-trees-11-18",
                "Car name 10",
                "Car maker 10",
                "Car model 10",
                "2018",
                Availability.UNAVAILABLE))

        return Observable.just(carList)
//        return apiService.getCars()
    }

}