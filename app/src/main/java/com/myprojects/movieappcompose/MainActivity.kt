package com.myprojects.movieappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.myprojects.movieappcompose.api.Events
import com.myprojects.movieappcompose.ui.theme.MovieAppComposeTheme
import com.myprojects.movieappcompose.viewmodel.MovieViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppComposeTheme {
                val nowPlayingList by movieViewModel.nowPlayingList.observeAsState(Events.Loading())

                when (val state = nowPlayingList) {
                    is Events.Loading -> {
                        CircularProgressIndicator()
                    }
                    is Events.Error -> {
                        Text(text = "Error: ${state.msg}")
                    }
                    is Events.Success -> {
                        LazyRow(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp)
                        ) {
                            val movieList = state.data ?: emptyList()
                            items(movieList) { item ->
                                NowPlayingElement(
                                    imageUrl = item.poster_path,
                                    title = item.title
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        }
                    }
                    else -> {
                        Text(text = "No data available.")
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text("Search")
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
fun NowPlayingElement(
    imageUrl: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun PopularCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier.width(380.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun NowPlayingRow(
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel
) {
    val nowPlayingList by movieViewModel.nowPlayingList.observeAsState(Events.Loading())

    when (val state = nowPlayingList) {
        is Events.Loading -> {
            CircularProgressIndicator()
        }
        is Events.Error -> {
            Text(text = "Error: ${state.msg}")
        }
        is Events.Success -> {
            LazyRow(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(state.data ?: emptyList()) { item ->
                    NowPlayingElement(
                        imageUrl = item.poster_path,
                        title = item.title
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
        else -> {
            Text(text = "No data available.")
        }
    }
}


@Composable
fun PopularRowList() {

}

@Composable
fun HomeSection() {

}

@Composable
fun HomeScreen() {

}

@Composable
fun BottomNavigation() {

}

@Composable
fun MovieAppPortrait() {

}

@Preview
@Composable
fun SearchBarPreview() {
    MovieAppComposeTheme { SearchBar(Modifier.padding(8.dp)) }
}

@Preview
@Composable
fun NowPlayingElementPreview() {
    MovieAppComposeTheme {
        NowPlayingElement(
            modifier = Modifier.padding(8.dp),
            imageUrl = "R.drawable.ic_launcher_background",
            title = "R.string.app_name"
        )
    }
}

@Preview
@Composable
fun PopularCardPreview() {
    MovieAppComposeTheme {
        PopularCard(
            modifier = Modifier.padding(8.dp),
            drawable = R.drawable.ic_launcher_background,
            text = R.string.app_name
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NowPlayingRowPreview() {
    MovieAppComposeTheme {
    }
}

@Preview
@Composable
fun PopularRowListPreview() {
    MovieAppComposeTheme { PopularRowList() }
}

@Preview
@Composable
fun HomeSectionPreview() {
    HomeSection()
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Preview
@Composable
fun BottomNavigationPreview() {
    BottomNavigation()
}

@Preview
@Composable
fun MovieAppPortraitPreview() {
    MovieAppPortrait()
}