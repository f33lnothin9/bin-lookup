package ru.resodostudios.binlookup.presentation.screens.history

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.resodostudios.binlookup.R
import ru.resodostudios.binlookup.presentation.navigation.Screen
import ru.resodostudios.binlookup.presentation.screens.home.HomeViewModel
import ru.resodostudios.binlookup.presentation.ui.theme.AppTypography
import java.util.*

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val card = viewModel.card.observeAsState().value
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.History.route) }) {
                        Icon(
                            imageVector = Icons.Outlined.History,
                            contentDescription = "History"
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    var text by remember { mutableStateOf("") }

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Enter the first digits of a card number") },
                        value = text,
                        maxLines = 1,
                        onValueChange = {
                            text = it
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                viewModel.getCardInfo(text)
                                keyboardController?.hide()
                            }
                        )
                    )

                    Headline(title = "Scheme")

                    CapitalizedText(text = card?.scheme ?: "-")

                    Headline(title = "Type")

                    CapitalizedText(text = card?.type ?: "-")

                    Headline(title = "Brand")

                    CapitalizedText(text = card?.brand ?: "-")

                    Headline(title = "Prepaid")

                    CapitalizedText(text = card?.prepaid.toString())

                    Headline(title = "Country")

                    Row {
                        CapitalizedText(text = card?.country?.emoji ?: "")
                        CapitalizedText(text = card?.country?.name ?: "")
                    }

                    Headline(title = "Bank")

                    Row {
                        CapitalizedText(text = (card?.bank?.name + ", "))
                        CapitalizedText(text = card?.bank?.city ?: "-")
                    }
                    Text(
                        text = card?.bank?.url ?: "-",
                        modifier = Modifier.clickable {
                            if (card?.bank?.url != null) {
                                val uri = Uri.parse(("https://" + card.bank.url))
                                val intent = Intent(Intent.ACTION_VIEW, uri)

                                try {
                                    context.startActivity(intent)
                                } catch (s: SecurityException) {

                                    Toast.makeText(context, "An error occurred", Toast.LENGTH_LONG)
                                        .show()
                                }
                            }
                        }
                    )
                    Text(
                        text = card?.bank?.phone ?: "-",
                        modifier = Modifier.clickable {
                            if (card?.bank?.url != null) {
                                val uri = Uri.parse(("tel:" + card.bank.phone))
                                val intent = Intent(Intent.ACTION_DIAL, uri)

                                try {
                                    context.startActivity(intent)
                                } catch (s: SecurityException) {

                                    Toast.makeText(context, "An error occurred", Toast.LENGTH_LONG)
                                        .show()
                                }
                            }
                        }
                    )
                }
            )
        }
    )
}

@Composable
fun Headline(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.SemiBold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = AppTypography.labelLarge,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colorScheme.onSecondaryContainer
    )
}

@Composable
fun CapitalizedText(text: String) {
    Text(text = text.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
}