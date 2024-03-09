package com.realmdb.realmgym

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.realmdb.realmgym.models.GYM
import com.realmdb.realmgym.ui.theme.RealmDBGYMTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RealmDBGYMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val gyms by viewModel.gyms.collectAsState()

                    LazyColumn {
                        items(gyms){ gym ->
                            ItemGYM(gym)
                        }
                    }


                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}


@Composable
fun ItemGYM(gym: GYM) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {

        Text(text = gym.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = gym.address, fontSize = 18.sp, fontWeight = FontWeight.Normal)

    }
}