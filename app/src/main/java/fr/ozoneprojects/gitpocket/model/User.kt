package fr.ozoneprojects.gitpocket.model

import com.google.gson.annotations.SerializedName

data class UserJson(
    @SerializedName("login")
    var login: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("html_url")
    var htmlUrl: String,
    @SerializedName("repos_url")
    var reposUrl: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("company")
    var company: Any?,
    @SerializedName("location")
    var location: String,
    @SerializedName("email")
    var email: String?,
    @SerializedName("bio")
    var bio: String,
    @SerializedName("public_repos")
    var publicRepos: Int,
    @SerializedName("public_gists")
    var publicGists: Int,
    @SerializedName("followers")
    var followers: Int,
    @SerializedName("following")
    var following: Int,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("updated_at")
    var updatedAt: String
)