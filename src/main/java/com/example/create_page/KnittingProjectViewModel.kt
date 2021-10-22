package com.example.create_page

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tgreenberg.core.models.KnitUri
import com.tgreenberg.core.models.UIKnittingProject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KnittingProjectViewModel @Inject constructor() : ViewModel() {

    val knittingProject: MutableState<UIKnittingProject> = mutableStateOf(UIKnittingProject())

    fun setKnittingProjectName(value: String) {
        knittingProject.value = knittingProject.value.copy(name = value)
    }

    fun setKnittingProjectNotes(value: String) {
        knittingProject.value = knittingProject.value.copy(notes = value)
    }

    fun setKnittingProjectDescription(value: String) {
        knittingProject.value = knittingProject.value.copy(description = value)
    }

    fun setKnitProjectImage(value: KnitUri) {
        knittingProject.value.images.toMutableList().apply { add(value) }.also {
            knittingProject.value = knittingProject.value.copy(images = it)
        }
    }

    fun submitProject(){
        viewModelScope.launch {
            KnitPackApi.postProject(knittingProject.value)
        }
    }

}