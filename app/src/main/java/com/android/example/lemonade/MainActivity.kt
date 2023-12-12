package com.android.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    clickableLemonadeApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun clickableLemonadeApp(modifier: Modifier = Modifier) {
    var clicks by remember { mutableStateOf(0) }
    var check = when (clicks) {
        in 1..4 -> (2..4).random()
        else -> 0
    }
    val imageResource = when (clicks) {
        0 -> R.drawable.lemon_tree
        in 1..4 -> R.drawable.lemon_squeeze
        5 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val textResource = when (clicks) {
        0 -> R.string.lemon_tree
        in 1..4 -> R.string.lemon
        5 -> R.string.drink
        else -> R.string.empty_glass
    }
    val descripResource = when (clicks) {
        0 -> R.string.lemon_tree_desc
        in 1..4 -> R.string.lemon_desc
        5 -> R.string.full_glass_desc
        else -> R.string.empty_glass_desc
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Yellow,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {

            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {

                Image(
                    painter = painterResource(imageResource),
                    contentDescription = stringResource(descripResource),
                    modifier = Modifier
                        .clickable {
                            if (clicks in 1..4) {

                                if (clicks != check) {
                                    clicks++
                                } else clicks = 5
                            } else if (clicks == 0 || clicks == 5) clicks++
                            else if (clicks == 6) clicks = 0

                        }
                        .background(
                            color = Color.Cyan.copy(alpha = 0.7f),
                            shape = RoundedCornerShape(20.dp)
                        )
                )


                Text(
                    text = stringResource(textResource),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun lemonadeapp() {
    LemonadeTheme {
        clickableLemonadeApp( modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))
    }
}