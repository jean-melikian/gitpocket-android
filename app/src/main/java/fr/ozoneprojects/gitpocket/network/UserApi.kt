package fr.ozoneprojects.gitpocket.network

import fr.ozoneprojects.gitpocket.model.UserJson
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("/user")
    fun getSelf(): Observable<UserJson>

    @GET("/user/{nickname}")
    fun getUserByName(@Path("nickname") nickname: String): Observable<UserJson>
}