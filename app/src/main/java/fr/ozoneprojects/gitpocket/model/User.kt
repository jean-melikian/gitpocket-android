package fr.ozoneprojects.gitpocket.model

import com.squareup.moshi.Json
import java.io.Serializable

data class User(
    @Json(name = "login")
    var login: String,
    @Json(name = "id")
    var id: Int,
    @Json(name = "avatar_url")
    var avatarUrl: String,
    @Json(name = "url")
    var url: String,
    @Json(name = "html_url")
    var htmlUrl: String,
    @Json(name = "repos_url")
    var reposUrl: String,
    @Json(name = "name")
    var name: String,
    @Json(name = "company")
    var company: Any?,
    @Json(name = "location")
    var location: String,
    @Json(name = "email")
    var email: String?,
    @Json(name = "bio")
    var bio: String,
    @Json(name = "public_repos")
    var publicRepos: Int,
    @Json(name = "public_gists")
    var publicGists: Int,
    @Json(name = "followers")
    var followers: Int,
    @Json(name = "following")
    var following: Int,
    @Json(name = "created_at")
    var createdAt: String,
    @Json(name = "updated_at")
    var updatedAt: String
) : Serializable