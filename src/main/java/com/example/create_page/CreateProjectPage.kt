package com.example.create_page

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.knitpack_components.BottomSheetUI
import com.example.knitpack_components.KnitFormUI
import com.example.knitpack_components.MulberryButton
import com.example.knitpack_components.patternsList
import com.example.knitpacktheme.theme.Mulberry_Primary
import com.example.knitpacktheme.theme.Off_Black
import com.example.knitpacktheme.theme.Off_White
import com.tgreenberg.core.models.ProjectTxt
import com.tgreenberg.core.models.UIKnittingProject


@Composable
fun CreateProjectPage(
    knittingProjectViewModel: KnittingProjectViewModel,
    launchImage: (String) -> Unit,
    submitProject: () -> Unit
) {

    val project: UIKnittingProject = knittingProjectViewModel.knittingProject.value
    val spaceHeight = 35.dp

    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
    ) {
        val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

        BottomSheetUI.ListSelectDialog(
            showDialog = showDialog,
            choices = patternsList,
            setShowDialog = setShowDialog
        )



        Spacer(modifier = Modifier.height(spaceHeight))

        KnitFormUI.KnittingTextField(
            title = "Project Name",
            hint = "add a project name",
            value = project.name
        ) {
            knittingProjectViewModel.setKnittingProjectName(it)
        }

        Spacer(modifier = Modifier.height(spaceHeight))

        KnitFormUI.KnittingTextField(
            title = "Notes",
            hint = "add notes about your project",
            value = project.notes
        ) { knittingProjectViewModel.setKnittingProjectNotes(it) }

        Spacer(modifier = Modifier.height(spaceHeight))

        KnitFormUI.KnittingTextField(
            title = "Description",
            hint = "add a description about your project",
            value = project.description
        ) { knittingProjectViewModel.setKnittingProjectDescription(it) }

        Spacer(modifier = Modifier.height(spaceHeight))

        KnitFormUI.KnittingDialogLauncher(
            title = "Pattern",
            value = "Chunky Herringbone snood"
        ) {
            setShowDialog(true)
        }

        Spacer(modifier = Modifier.height(spaceHeight))

        KnitFormUI.KnittingDialogLauncher(
            title = "Yarn",
            value = null
        ) {}

        Spacer(modifier = Modifier.height(spaceHeight))

        KnitFormUI.PictureField(knittingProjectViewModel.knittingProject.value.images) {
            launchImage("image/*")
        }

        Spacer(modifier = Modifier.height(spaceHeight))

        MulberryButton(text = "Create!") { submitProject() }

    }
}