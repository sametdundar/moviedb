package com.sametdundar.movieapp.model

import java.io.Serializable


data class Networks (

	val name : String,
	val id : Int,
	val logo_path : String,
	val origin_country : String
):Serializable