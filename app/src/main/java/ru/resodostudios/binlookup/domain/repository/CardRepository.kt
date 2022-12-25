package ru.resodostudios.binlookup.domain.repository

import dagger.hilt.android.scopes.ActivityScoped
import ru.resodostudios.binlookup.data.model.Card
import ru.resodostudios.binlookup.data.remote.BinApi
import ru.resodostudios.binlookup.util.Resource
import javax.inject.Inject

@ActivityScoped
class CardRepository @Inject constructor(
    private val api: BinApi
) {
    suspend fun getCardInfo(bin: String): Resource<Card> {
        val response = try {
            api.getCardInfo(bin)
        } catch (e: Exception) {
            return Resource.Error("Unknown Error")
        }
        return Resource.Success(response)
    }
}