package com.brownik.unsplash.data.model

import com.google.gson.annotations.SerializedName

data class PhotoData(
    @SerializedName("id"            ) var id          : String  = "",
    @SerializedName("created_at"    ) var createdAt   : String  = "",
    @SerializedName("updated_at"    ) var updatedAt   : String  = "",
    @SerializedName("width"         ) var width       : Int     = 0,
    @SerializedName("height"        ) var height      : Int     = 0,
    @SerializedName("color"         ) var color       : String  = "",
    @SerializedName("blur_hash"     ) var blurHash    : String  = "",
    @SerializedName("likes"         ) var likes       : Int     = 0,
    @SerializedName("liked_by_user" ) var likedByUser : Boolean = false,
    @SerializedName("description"   ) var description : String?  = "",
    @SerializedName("urls"          ) var urls        : PhotoUrlData = PhotoUrlData(),
    @SerializedName("user"          ) var user        : UserData = UserData()
)

data class PhotoUrlData(
    @SerializedName("raw"     ) var raw     : String = "",
    @SerializedName("full"    ) var full    : String = "",
    @SerializedName("regular" ) var regular : String = "",
    @SerializedName("small"   ) var small   : String = "",
    @SerializedName("thumb"   ) var thumb   : String = ""
)

data class UserData (
    @SerializedName("id"                 ) var id                : String       = "",
    @SerializedName("username"           ) var username          : String       = "",
    @SerializedName("name"               ) var name              : String       = "",
    @SerializedName("portfolio_url"      ) var portfolioUrl      : String       = "",
    @SerializedName("bio"                ) var bio               : String       = "",
    @SerializedName("location"           ) var location          : String       = "",
    @SerializedName("total_likes"        ) var totalLikes        : Int          = 0,
    @SerializedName("total_photos"       ) var totalPhotos       : Int          = 0,
    @SerializedName("total_collections"  ) var totalCollections  : Int          = 0,
    @SerializedName("instagram_username" ) var instagramUsername : String       = "",
    @SerializedName("twitter_username"   ) var twitterUsername   : String       = "",
    @SerializedName("profile_image"      ) var profileImage      : ProfileImageData = ProfileImageData()
)

data class ProfileImageData (
    @SerializedName("small"  ) var small  : String = "",
    @SerializedName("medium" ) var medium : String = "",
    @SerializedName("large"  ) var large  : String = ""
)