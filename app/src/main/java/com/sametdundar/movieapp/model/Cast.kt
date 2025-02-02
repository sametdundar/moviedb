package com.sametdundar.movieapp.model

import java.io.Serializable


data class Cast (

	val cast_id : Int?,
	val character : String,
	val credit_id : String,
	val gender : Int,
	val id : Int,
	val name : String,
	val order : Int,
	val profile_path : String
):Serializable