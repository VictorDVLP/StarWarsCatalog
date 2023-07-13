package com.example.starwarscatalog.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwarscatalog.data.PeopleDb
import com.example.starwarscatalog.red.StarWarsApi
import com.example.starwarscatalog.repository.START_INDEX
import retrofit2.HttpException
import java.io.IOException

class PeoplePagination(private val service: StarWarsApi) :
    PagingSource<Int, PeopleDb>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PeopleDb> {
        val position = params.key ?: START_INDEX
        return try {
            val response = service.retrofitService.getItemPerson(position)
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

    override fun getRefreshKey(state: PagingState<Int, PeopleDb>): Int? {
        return null
    }
}