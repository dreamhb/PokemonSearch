package com.hilton.jobsearch.ui

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hilton.jobsearch.data.Job
import com.hilton.jobsearch.data.JobSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.reflect.Type
import javax.inject.Inject

/**
 * The view model for HomeFragment, the main logic is here.
 * We use AndroidViewModel here since we use SharedPreference to store the last 5 search
 */
class HomeViewModel @Inject constructor(
    private val repo: JobSearchRepository,
    app: Application
): AndroidViewModel(app) {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Default)

    private val _last5Search: MutableStateFlow<List<String>> = MutableStateFlow(
        emptyList()
    )

    val last5SearchState: StateFlow<List<String>> = _last5Search.asStateFlow()

    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val sharedPreference = app.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)

    suspend fun searchJob(type: String) {
        viewModelScope.launch {
            saveLast5Search(type)

            try {
                _uiState.value = UiState.Loading
                val jobs = repo.searchJob(type)
                _uiState.value = UiState.Success(jobs)
                _uiState.value = UiState.Default
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message)
            }

            _last5Search.update {
                getLast5Search()
            }
        }
    }

    //since the last5search is simple, so could save them in SharedPreference
    private suspend fun saveLast5Search(search: String) {
            withContext(Dispatchers.IO) {
                val values = sharedPreference.getString(SP_KEY, "")
                val gType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
                sharedPreference.edit {
                    val finalValues: ArrayList<String> = arrayListOf()
                    if (values?.isEmpty() == true) {
                        finalValues.add(search)
                    } else {
                        val oldValues: MutableList<String> = Gson().fromJson<List<String>?>(values, gType).toMutableList()
                        if (oldValues.contains(search)) {
                            oldValues.remove(search)
                        }
                        oldValues.add(0, search)

                        if(oldValues.size > 5) {
                            finalValues.addAll(oldValues.take(5))
                        } else {
                            finalValues.addAll(oldValues)
                        }
                    }
                    putString(SP_KEY, Gson().toJson(finalValues))
                    commit()
                }
        }
    }


    private suspend fun getLast5Search(): List<String> =
        withContext(Dispatchers.IO) {
            val values = sharedPreference.getString(SP_KEY, "")
            val gType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
            Gson().fromJson(values, gType)
        }


    companion object {
        const val SP_NAME = "sp_search_text"
        const val SP_KEY = "sp_search_last5"
    }
}

sealed class UiState {
    data class Success(
        val result: List<Job> = emptyList()
    ) : UiState()

    data class Error(val message: String?): UiState()

    data object Loading : UiState()

    data object Default : UiState()
}