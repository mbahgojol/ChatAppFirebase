package com.bpdsulteng.androidtvsliderimage.data.realm

import com.fasterxml.jackson.annotation.JsonProperty
import io.realm.RealmObject
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
open class UserLoginObject : RealmObject() {

    @field:JsonProperty("is_business")
    var isBusiness: Boolean? = null

    @field:JsonProperty("website")
    var website: String? = null

    @field:JsonProperty("full_name")
    var fullName: String? = null

    @field:JsonProperty("bio")
    var bio: String? = null

    @field:JsonProperty("profile_picture")
    var profilePicture: String? = null

    @field:JsonProperty("id")
    var id: String? = null

    @field:JsonProperty("username")
    var username: String? = null

    override fun toString(): String {
        return "UserLoginObject(isBusiness=$isBusiness, website=$website, fullName=$fullName, bio=$bio, profilePicture=$profilePicture, id=$id, username=$username)"
    }
}