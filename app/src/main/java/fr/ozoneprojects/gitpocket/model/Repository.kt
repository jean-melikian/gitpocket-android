package fr.ozoneprojects.gitpocket.model

import com.squareup.moshi.Json
import java.io.Serializable

data class Repository(
    @Json(name = "name")
    val name: String = "Repository name",
    @Json(name = "language")
    val language: String
) : Serializable