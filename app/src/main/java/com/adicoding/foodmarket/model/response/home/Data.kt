package com.adicoding.foodmarket.model.response.home


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Data(
    @Expose
    @SerializedName("created_at")
    val createdAt: Int?,
    @Expose
    @SerializedName("deleted_at")
    val deletedAt: String?,
    @Expose
    @SerializedName("description")
    val description: String?,
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("ingredients")
    val ingredients: String?,
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("picturePath")
    val picturePath: String?,
    @Expose
    @SerializedName("price")
    val price: String?,
    @Expose
    @SerializedName("rate")
    val rate: String?,
    @Expose
    @SerializedName("types")
    val types: String?,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: String?
) : Parcelable