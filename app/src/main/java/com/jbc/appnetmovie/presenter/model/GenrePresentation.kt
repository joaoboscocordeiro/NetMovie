package com.jbc.appnetmovie.presenter.model

import android.os.Parcelable
import com.jbc.core.domain.model.Movie
import kotlinx.parcelize.Parcelize

/*
 * Created by Joao Bosco on 09/02/24.
 */
@Parcelize
data class GenrePresentation(
    val id: Int?,
    val name: String?,
    val movies: List<Movie>?
) : Parcelable
