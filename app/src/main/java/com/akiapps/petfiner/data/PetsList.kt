package com.akiapps.petfiner.data

import android.os.Parcelable
import com.akiapps.petfiner.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(val name: String, val occupation: String) : Parcelable

@Parcelize
data class Pet(
    val petName: String,
    val petYear: Int,
    val petImage: Int,
    @GENDER val gender: String,
    val distance: Long,
    val owner: Owner
) : Parcelable

fun getPetsList(): Array<Pet> {
    return arrayOf(
        Pet("Shockey", 2, R.drawable.blue_dog, MALE, 221, Owner("mike", "Software Developer")),
        Pet("Bruno", 1, R.drawable.orange_dog, FEMALE, 225, Owner("anurag", "Business")),
        Pet("Short", 4, R.drawable.white_dog, MALE, 220, Owner("akhil", "Real Estate Developer")),
        Pet("Lab", 2, R.drawable.red_dog, FEMALE, 2, Owner("sai", "Investment Banker"))
    )
}