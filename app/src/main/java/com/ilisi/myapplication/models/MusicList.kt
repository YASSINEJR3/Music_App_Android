package com.ilisi.myapplication.models

import com.google.gson.annotations.SerializedName

data class MusicList(
    val entry: List<MusicItem>
)

data class MusicItem(
    @SerializedName("title")
    val title: ImTitle,
    @SerializedName("im:image")
    val images: List<ImImage>,
    @SerializedName("im:price")
    val price: ImPrice,
    @SerializedName("id")
    val link: ImId,
    @SerializedName("im:artist")
    val artist: ImArtist,
    @SerializedName("category")
    val category: ImCategory,
    @SerializedName("im:releaseDate")
    val releaseDate: ImReleaseDate
)

data class ImTitle(
    val label: String
)

data class ImImage(
    val label: String,
    var attributes: ImImageAttributes
)

data class ImImageAttributes(
    val height: String
)

data class ImId(
    val label: String
)

data class ImPrice(
    val label: String
)

data class ImArtist(
    val label: String
)

data class ImCategory(
    val attributes: ImCategoryAttributes
)

data class ImCategoryAttributes(
    val label: String
)

data class ImReleaseDate(
    val attributes: ImReleaseDateAttributes
)

data class ImReleaseDateAttributes(
    val label: String
)