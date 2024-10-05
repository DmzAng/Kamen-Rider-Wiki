package com.dimaz.kamenriderapps

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Series(
    val nameSeries: String,
    val theme: String,
    val synopsis: String,
    val image: String,

): Parcelable
