package com.dynamicheart.raven.data

import com.dynamicheart.raven.data.local.DatabaseHelper
import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.data.model.house.form.CreateHouseForm
import com.dynamicheart.raven.data.model.house.form.UpdateHouseForm
import com.dynamicheart.raven.data.model.house.member.Member
import com.dynamicheart.raven.data.model.raven.InRaven
import com.dynamicheart.raven.data.model.raven.Raven
import com.dynamicheart.raven.data.model.raven.form.SendRavenForm
import com.dynamicheart.raven.data.model.reply.Reply
import com.dynamicheart.raven.data.model.reply.form.CreateReplyForm
import com.dynamicheart.raven.data.model.serve.Serve
import com.dynamicheart.raven.data.model.serve.form.CreateServeForm
import com.dynamicheart.raven.data.model.whistleblowing.form.CreateWhistleBlowingForm
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

    fun getRavens(): Observable<List<Raven>>{
        return ravenService.getYourRavens()
    }

    fun getHouses(): Observable<List<House>> {
        return ravenService.getYourHouses()
    }

    fun createHouse(createHouseForm: CreateHouseForm): Observable<House>{
        return ravenService.createNewHouse(createHouseForm)
    }

    fun updateHouse(id: String, updateHouseForm: UpdateHouseForm): Observable<House>{
        return ravenService.updateHouse(id, updateHouseForm)
    }

    fun findHouse(id: String): Observable<House>{
        return ravenService.findHouse(id)
    }

    fun joinPublicHouse(houseId: String): Observable<Member>{
        return ravenService.joinPublicHouse(houseId)
    }

    fun applyTOServePrivateHouse(createServeForm: CreateServeForm): Observable<Serve>{
        return ravenService.applyToServeHouse(createServeForm)
    }

    fun expelMember(houseId: String, userId: String): Observable<Member>{
        return ravenService.expelMember(houseId, userId)
    }

    fun leaveHouse(houseId: String): Observable<Member>{
        return ravenService.leaveHouse(houseId)
    }

    fun sendRaven(sendRavenForm: SendRavenForm): Observable<Raven>{
        return ravenService.sendRaven(sendRavenForm)
    }

    fun getInRaven(id: String): Observable<InRaven>{
        return ravenService.getInRaven(id)
    }

    fun getServeApply(houseId: String): Observable<List<Serve>>{
        return ravenService.getServeApply(houseId)
    }

    fun sendReply(id: String, createReplyForm: CreateReplyForm): Observable<Reply>{
        return ravenService.sendReply(id, createReplyForm)
    }

    fun handleServe(id: String, method: Int): Observable<Serve>{
        return ravenService.handleServe(id, method)
    }

    fun sendWhistleBlowing(createWhistleBlowingForm: CreateWhistleBlowingForm): Observable<Void>{
        return ravenService.sendWhistleBlowing(createWhistleBlowingForm)
    }
}
