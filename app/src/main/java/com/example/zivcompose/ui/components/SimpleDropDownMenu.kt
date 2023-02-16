package com.example.zivcompose.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SimpleDropDownMenu(
    values: List<String>,
    selectedIndex: Int=0,
    onChange: (Int) -> Unit={},
    label: @Composable () -> Unit={},
    modifier: Modifier = Modifier
        .width(200.dp)
        .height(40.dp),
    backgroundColor: Color = MaterialTheme.colors.surface.copy(alpha = TextFieldDefaults.BackgroundOpacity),
    shape: Shape = MaterialTheme.shapes.small
) {


    SimpleExposedDropDownMenuImpl(
        values = values,
        selectedIndex = selectedIndex,
        onChange = onChange,
        label = label,
        modifier = modifier,
        backgroundColor = backgroundColor,
        shape = shape,
        decorator = { color, width, content ->
            Box(
                Modifier
                    .border(width, color, shape)
            ) {
                content()
            }
        }
    )
}

@Composable
private fun SimpleExposedDropDownMenuImpl(
    values: List<String>,
    selectedIndex: Int,
    onChange: (Int) -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier,
    backgroundColor: Color,
    shape: Shape,
    decorator: @Composable (Color, Dp, @Composable () -> Unit) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    var selectedIndex by remember { mutableStateOf(selectedIndex) }
    val indicatorColor =
        if (expanded) MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high)
        else MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.UnfocusedIndicatorLineOpacity)
    val indicatorWidth = (if (expanded) 2 else 1).dp
    val labelColor =
        if (expanded) MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high)
        else MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
    val trailingIconColor =
        MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.IconOpacity)

    val rotation: Float by animateFloatAsState(if (expanded) 180f else 0f)

    val focusManager = LocalFocusManager.current
    Column(modifier = modifier.width(IntrinsicSize.Min)) {
        decorator(indicatorColor, indicatorWidth) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(color = backgroundColor, shape = shape)
                    .onGloballyPositioned { textfieldSize = it.size.toSize() }
                    .clip(shape)
                    .clickable {
                        expanded = !expanded
                        focusManager.clearFocus()
                    }
                    .padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 5.dp)
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    ProvideTextStyle(value = MaterialTheme.typography.caption.copy(color = labelColor)) {
                        label()
                    }
                    Text(
                        text = values[selectedIndex]
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Change",
                    tint = trailingIconColor,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .rotate(rotation)
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                .background(MaterialTheme.colors.background)
        ) {
            values.forEachIndexed { i, v ->
                val scope = rememberCoroutineScope()
                DropdownMenuItem(
                    onClick = {
                        selectedIndex = i
                        onChange(i)
                        scope.launch {
                            delay(150)
                            expanded = false
                        }
                    }
                ) {
                    Text(text = v)
                }
            }
        }
    }
}
