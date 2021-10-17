package com.example.create_page

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tgreenberg.core.models.UIKnittingProject
import dagger.hilt.android.lifecycle.HiltViewModel
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

}