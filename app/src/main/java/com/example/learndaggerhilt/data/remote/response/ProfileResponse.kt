package com.example.learndaggerhilt.data.remote.response
import com.google.gson.annotations.SerializedName


data class ProfileResponse(
    @SerializedName("avatar_url")
    var avatarUrl: String = "",
    @SerializedName("bio")
    var bio: String = "",
    @SerializedName("blog")
    var blog: String = "",
    @SerializedName("company")
    var company: Any? = null,
    @SerializedName("created_at")
    var createdAt: String = "",
    @SerializedName("email")
    var email: Any? = null,
    @SerializedName("events_url")
    var eventsUrl: String = "",
    @SerializedName("followers")
    var followers: Int = 0,
    @SerializedName("followers_url")
    var followersUrl: String = "",
    @SerializedName("following")
    var following: Int = 0,
    @SerializedName("following_url")
    var followingUrl: String = "",
    @SerializedName("gists_url")
    var gistsUrl: String = "",
    @SerializedName("gravatar_id")
    var gravatarId: String = "",
    @SerializedName("hireable")
    var hireable: Any? = null,
    @SerializedName("html_url")
    var htmlUrl: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("location")
    var location: String = "",
    @SerializedName("login")
    var login: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("node_id")
    var nodeId: String = "",
    @SerializedName("organizations_url")
    var organizationsUrl: String = "",
    @SerializedName("public_gists")
    var publicGists: Int = 0,
    @SerializedName("public_repos")
    var publicRepos: Int = 0,
    @SerializedName("received_events_url")
    var receivedEventsUrl: String = "",
    @SerializedName("repos_url")
    var reposUrl: String = "",
    @SerializedName("site_admin")
    var siteAdmin: Boolean = false,
    @SerializedName("starred_url")
    var starredUrl: String = "",
    @SerializedName("subscriptions_url")
    var subscriptionsUrl: String = "",
    @SerializedName("twitter_username")
    var twitterUsername: Any? = null,
    @SerializedName("type")
    var type: String = "",
    @SerializedName("updated_at")
    var updatedAt: String = "",
    @SerializedName("url")
    var url: String = ""
)