package pe.com.master.machines.verpelis.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import pe.com.master.machines.design.theme.VerPelisTheme
import pe.com.master.machines.verpelis.ui.navigation.NavigationWrapperMain

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VerPelisTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationWrapperMain(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

