package com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.achromatopsia.achromatopsiaScheme
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.achromatopsia.darkSchemeAchromatopsia
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.deuteranopia.darkSchemeDeuteranopia
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.deuteranopia.deuteranopiaScheme
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.oficial.darkScheme
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.oficial.lightScheme
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.protanopia.darkSchemeProtanopia
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.protanopia.protanopiaScheme
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tritanopia.darkSchemeTritanopia
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.theme.tritanopia.tritanopiaScheme

@Composable
fun ThemeColorsScreen(
    modifier: Modifier = Modifier
) {
    var selectedColorScheme by remember { mutableStateOf(lightScheme) }
    var expanded by remember { mutableStateOf(false) }

    val colorSchemes = listOf(
        "Default" to lightScheme,
        "Default Dark" to darkScheme,
        "Deuteranopia" to deuteranopiaScheme,
        "Deuteranopia Dark" to darkSchemeDeuteranopia,
        "Protanopia" to protanopiaScheme,
        "Protanopia Dark" to darkSchemeProtanopia,
        "Tritanopia" to tritanopiaScheme,
        "Tritanopia Dark" to darkSchemeTritanopia,
        "Achromatopsia" to achromatopsiaScheme,
        "Achromatopsia Dark" to darkSchemeAchromatopsia
    )

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Select Theme: ")
            Spacer(modifier = Modifier.width(8.dp))
            Box {
                Text(
                    text = colorSchemes.first { it.second == selectedColorScheme }.first,
                    modifier = Modifier
                        .background(selectedColorScheme.primary)
                        .padding(8.dp)
                        .clickable { expanded = !expanded }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    colorSchemes.forEach { (name, scheme) ->
                        DropdownMenuItem(
                            text = { Text(name) },
                            onClick = {
                                selectedColorScheme = scheme
                                expanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(selectedColorScheme.toColorList()) { colorItem ->
                ColorItem(colorItem)
            }
        }
    }
}

data class ColorItemData(val name: String, val color: Color)

fun ColorScheme.toColorList(): List<ColorItemData> {
    return listOf(
        ColorItemData("Primary", primary),
        ColorItemData("On Primary", onPrimary),
        ColorItemData("Primary Container", primaryContainer),
        ColorItemData("On Primary Container", onPrimaryContainer),
        ColorItemData("Secondary", secondary),
        ColorItemData("On Secondary", onSecondary),
        ColorItemData("Secondary Container", secondaryContainer),
        ColorItemData("On Secondary Container", onSecondaryContainer),
        ColorItemData("Tertiary", tertiary),
        ColorItemData("On Tertiary", onTertiary),
        ColorItemData("Tertiary Container", tertiaryContainer),
        ColorItemData("On Tertiary Container", onTertiaryContainer),
        ColorItemData("Error", error),
        ColorItemData("On Error", onError),
        ColorItemData("Error Container", errorContainer),
        ColorItemData("On Error Container", onErrorContainer),
        ColorItemData("Background", background),
        ColorItemData("On Background", onBackground),
        ColorItemData("Surface", surface),
        ColorItemData("On Surface", onSurface),
        ColorItemData("Surface Variant", surfaceVariant),
        ColorItemData("On Surface Variant", onSurfaceVariant),
        ColorItemData("Outline", outline),
        ColorItemData("Outline Variant", outlineVariant),
        ColorItemData("Scrim", scrim),
        ColorItemData("Inverse Surface", inverseSurface),
        ColorItemData("Inverse On Surface", inverseOnSurface),
        ColorItemData("Inverse Primary", inversePrimary),
        ColorItemData("Surface Dim", surfaceDim),
        ColorItemData("Surface Bright", surfaceBright),
        ColorItemData("Surface Container Lowest", surfaceContainerLowest),
        ColorItemData("Surface Container Low", surfaceContainerLow),
        ColorItemData("Surface Container", surfaceContainer),
        ColorItemData("Surface Container High", surfaceContainerHigh),
        ColorItemData("Surface Container Highest", surfaceContainerHighest)
    )
}

@Composable
fun ColorItem(colorItem: ColorItemData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(colorItem.color)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = colorItem.name)
    }
}

@Preview(showBackground = true)
@Composable
fun ThemeColorsScreenPreview() {
    ThemeColorsScreen()
}
