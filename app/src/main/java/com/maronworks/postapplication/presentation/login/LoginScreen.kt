package com.maronworks.postapplication.presentation.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.maronworks.postapplication.R
import com.maronworks.postapplication.data.DBHandler
import com.maronworks.postapplication.presentation.Screens
import com.maronworks.postapplication.utils.createFileInAppDirectory
import com.maronworks.postapplication.utils.writeToFile

@Composable
fun LoginScreen(
    navController: NavHostController
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .height(Int.MAX_VALUE.dp)
                .padding(10.dp)
        ) {
            Column {
                TabRow(
                    selectedTabIndex = selectedTab,
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    tabIndex.forEachIndexed { index, item ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(text = item.label, fontFamily = FontFamily.Monospace) },
                            icon = {
                                Image(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = item.label,
                                    colorFilter = ColorFilter.tint(if (selectedTab == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary)
                                )
                            },
                            selectedContentColor = MaterialTheme.colorScheme.primary,
                            unselectedContentColor = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }

                when (selectedTab) {
                    0 -> Login(navController = navController)
                    1 -> Register(onClick = { selectedTab = 0 })
                }
            }
        }
    }
}

@Composable
private fun Login(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val db = DBHandler(context)

    Card(
        modifier = Modifier
            .padding(5.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                },
                placeholder = {
                    Text(
                        text = "Username...",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                },
                placeholder = {
                    Text(
                        text = "Password...",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            )

            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {
                    try {
                        if (db.isUserExist(username, password)) {
                            Log.d(
                                "login",
                                "Login success! value: ${db.isUserExist(username, password)}"
                            )
                            db.setCurrentUser(username)
                            /*
                            // save the current user to a file
                            if (writeToFile(username)) {
                                Log.d("write-to-file", "Written to file successfully!")
                            } else {
                                if (createFileInAppDirectory(context)) {
                                    Log.d("create-file", "File created successfully")
                                } else {
                                    Log.d("create-file", "File creation failed!")
                                }
                                if (writeToFile(username)) {
                                    Log.d("write-to-file2", "Success")
                                } else {
                                    Log.d("write-to-file2", "Failed!")
                                }
                            }*/

                            navController.navigate(Screens.Main.route)
                        } else {
                            Log.d(
                                "login",
                                "Login success! value: ${db.isUserExist(username, password)}"
                            )
                            Log.d("login", "Login Failed! User does not exists!")
                        }
                    } catch (e: Exception) {
                        Log.d("login", "Error: ${e.message}")
                    }
                }
            ) {
                Text(
                    text = " LOGIN ",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
            }
        }
    }
}

@Composable
private fun Register(onClick: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val db = DBHandler(context)

    Card(
        modifier = Modifier
            .padding(5.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                },
                placeholder = {
                    Text(
                        text = "Username...",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                },
                placeholder = {
                    Text(
                        text = "Password...",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            )

            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {
                    // saving data to db
                    try {
                        db.addNewUser(username, password)
                        Log.d("register", "Registration success! Values: $username, $password")

                        // create new table specific for this user
                        // db.createTable(username)
                        onClick()
                    } catch (e: Exception) {
                        Log.d("register", "Error: ${e.message}")
                    }
                }
            ) {
                Text(
                    text = "REGISTER",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
            }
        }
    }
}

private data class TabIndex(
    val label: String,
    val icon: Int
)

private val tabIndex = listOf(
    TabIndex(
        label = "LOGIN",
        icon = R.drawable.login
    ),
    TabIndex(
        label = "REGISTER",
        icon = R.drawable.register
    )
)

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginScreenPrev() {
    LoginScreen(navController = rememberNavController())
}
