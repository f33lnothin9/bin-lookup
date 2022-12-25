package ru.resodostudios.binlookup.data.model

import ru.resodostudios.binlookup.data.remote.responses.Bank
import ru.resodostudios.binlookup.data.remote.responses.Country
import ru.resodostudios.binlookup.data.remote.responses.Number

data class Card(
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: Number,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)