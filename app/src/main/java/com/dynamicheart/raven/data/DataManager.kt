package com.dynamicheart.raven.data

import com.dynamicheart.raven.data.local.DatabaseHelper
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.data.model.house.form.CreateHouseForm
import com.dynamicheart.raven.data.model.raven.InRaven
import com.dynamicheart.raven.data.remote.RavenService
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager
@Inject constructor(private val ravenService: RavenService,
                    private val databaseHelper: DatabaseHelper) {

    fun syncAndGetInRavens(): Observable<List<InRaven>> {
        return ravenService.getYourInRavens()
                .flatMap { databaseHelper.setInRavens(it) }
                .flatMap { databaseHelper.getInRavens() }
    }

    fun getLocalInRavens(): Observable<List<InRaven>> {
        return databaseHelper.getInRavens()
    }

    fun getHouses(): Observable<List<House>> {
        return ravenService.getYourHouses()
    }

    fun createHouse(createHouseForm: CreateHouseForm): Observable<House>{
        return ravenService.createNewHouse(createHouseForm)
    }
}
