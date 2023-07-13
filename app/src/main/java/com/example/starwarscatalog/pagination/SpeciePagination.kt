package com.example.starwarscatalog.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwarscatalog.data.FilmDb
import com.example.starwarscatalog.data.SpeciesDb
import com.example.starwarscatalog.red.StarWarsApi
import com.example.starwarscatalog.repository.START_INDEX
import retrofit2.HttpException
import java.io.IOException

class SpeciePagination (private val service: StarWarsApi):
    PagingSource<Int, SpeciesDb>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SpeciesDb> {
        val position = params.key ?: START_INDEX
        return try {
            val response = service.retrofitService.getItemSpecies(position)
            val items = response.results
            val nextKey = if (items.isEmpty()) { null } else { position + 1 }
            LoadResult.Page(
                data = items,
                prevKey = if (position == START_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SpeciesDb>): Int? {
        return null
    }
}