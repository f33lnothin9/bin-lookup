package ru.resodostudios.binlookup.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import ru.resodostudios.binlookup.data.model.Card

interface BinApi {
    @GET("{bin}")
    suspend fun getCardInfo(
        @Path("bin") bim: String
    ): Card
}