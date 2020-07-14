package com.sametdundar.movieapp.model

import java.io.Serializable

data class ProductionCompanies (
	val id : Int,
	val logo_path : String,
	val name : String,
	val origin_country : String
):Serializable