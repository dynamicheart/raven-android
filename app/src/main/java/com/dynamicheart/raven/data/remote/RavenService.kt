package com.dynamicheart.raven.data.remote

import com.dynamicheart.raven.data.model.house.House
import com.dynamicheart.raven.data.model.house.form.CreateHouseForm
import com.dynamicheart.raven.data.model.house.form.UpdateHouseForm
import com.dynamicheart.raven.data.model.house.member.Member
import com.dynamicheart.raven.data.model.leancloud.Installation
import com.dynamicheart.raven.data.model.leancloud.form.UpdateInstallationForm
import com.dynamicheart.raven.data.model.raven.InRaven
import com.dynamicheart.raven.data.model.raven.Raven
import com.dynamicheart.raven.data.model.raven.form.SendRavenForm
import com.dynamicheart.raven.data.model.reply.Reply
import com.dynamicheart.raven.data.model.reply.form.CreateReplyForm
import com.dynamicheart.raven.data.model.serve.Serve
import com.dynamicheart.raven.data.model.serve.form.CreateServeForm
import com.dynamicheart.raven.data.model.token.Token
import com.dynamicheart.raven.data.model.user.User
import com.dynamicheart.raven.data.model.user.form.CreateUserForm
import com.dynamicheart.raven.data.model.user.form.LoginForm
import com.dynamicheart.raven.data.model.whistleblowing.form.CreateWhistleBlowingForm
import io.reactivex.Observable
import retrofit2.http.*

interface RavenService {
    @GET("user")
    fun getUser(): Observable<User>

    @POST("users")
    fun createNewUser(@Body createUserForm: CreateUserForm): Observable<User>

    @POST("user/installation")
    fun uploadInstallation(@Body updateInstallationForm: UpdateInstallationForm): Observable<Installation>

    @POST("tokens")
    fun login(@Body loginForm: LoginForm): Observable<Token>

    @DELETE("tokens")
    fun logout(): Observable<Void>

    @GET("user/houses")
    fun getYourHouses(): Observable<List<House>>

    @GET("user/inravens")
    fun getYourInRavens(): Observable<List<InRaven>>

    @GET("user/ravens")
    fun getYourRavens(): Observable<List<Raven>>

    @GET("houses/{id}")
    fun findHouse(@Path("id") id: String): Observable<House>

    @POST("houses")
    fun createNewHouse(@Body createHouseForm: CreateHouseForm): Observable<House>

    @PUT("houses/{id}")
    fun updateHouse(@Path("id") id: String, @Body updateHouseForm: UpdateHouseForm): Observable<House>

    @POST("houses/{houseId}/members")
    fun joinPublicHouse(@Path("houseId") houseId: String): Observable<Member>

    @POST("serves")
    fun applyToServeHouse(@Body createServeForm: CreateServeForm): Observable<Serve>

    @GET("houses/{houseId}/serves")
    fun getServeApply(@Path("houseId") houseId: String): Observable<List<Serve>>

    @PUT("serves/{serveId}/{method}")
    fun handleServe(@Path("serveId") serveId: String, @Path("method") method: Int): Observable<Serve>

    @DELETE("houses/{houseId}/members/{userId}")
    fun expelMember(@Path("houseId") houseId: String, @Path("userId") userId: String): Observable<Member>

    @DELETE("houses/{houseId}/members")
    fun leaveHouse(@Path("houseId") houseId: String): Observable<Member>

    @POST("ravens")
    fun sendRaven(@Body sendRavenForm: SendRavenForm): Observable<Raven>

    @GET("inravens/{ravenId}")
    fun getInRaven(@Path("ravenId") id: String): Observable<InRaven>

    @POST("ravens/{ravenId}/replies")
    fun sendReply(@Path("ravenId") id:String, @Body createReplyForm: CreateReplyForm): Observable<Reply>

    @POST("whistleblowings")
    fun sendWhistleBlowing(@Body createWhistleBlowingForm: CreateWhistleBlowingForm): Observable<Void>
}