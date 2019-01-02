package fr.ozoneprojects.gitpocket.network

import fr.ozoneprojects.gitpocket.model.Repository
import fr.ozoneprojects.gitpocket.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi {

    @GET("/users")
    fun getSelf(): Observable<User>

    @GET("/users/{nickname}")
    fun getUserByName(@Path("nickname") nickname: String): Observable<User>

    @GET("/users/{nickname}/repos")
    fun getUserRepositories(@Path("nickname") nickname: String): Observable<List<Repository>>
}