package com.akiapps.petfiner.data

import androidx.annotation.StringDef


@Retention(AnnotationRetention.SOURCE)
@StringDef(MALE, FEMALE)
annotation class GENDER
const val MALE = "Male"
const val FEMALE = "Female"


