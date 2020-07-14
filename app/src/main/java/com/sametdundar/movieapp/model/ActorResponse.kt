package com.sametdundar.movieapp.model

import java.io.Serializable

data class ActorResponse (

	val id : Int,
	val cast : List<Cast>,
	val crew : List<Crew>?
):Serializable