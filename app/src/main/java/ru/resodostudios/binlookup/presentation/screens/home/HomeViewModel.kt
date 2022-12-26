package ru.resodostudios.binlookup.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.resodostudios.binlookup.data.model.Card
import ru.resodostudios.binlookup.domain.repository.CardRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: CardRepository) : ViewModel() {

    private val _card = MutableLiveData<Card>()
    private val _isLoading = MutableStateFlow(true)

    val card: LiveData<Card>
        get() = _card
    val isLoading = _isLoading.asStateFlow()

    fun getCardInfo(bin: String) {
        viewModelScope.launch {
            repository.getCardInfo(bin).let {
                _card.value = it.data
            }
        }

    }
}