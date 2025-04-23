package com.comet.movieapp.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.comet.movieapp.data.model.MovieDetailDomain

@Composable
fun DetailContent(movie: MovieDetailDomain) {
    Column(Modifier.padding(8.dp)) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth(),
            model = movie.backdrop_path,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.title.orEmpty(),
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.overview.orEmpty(),
            fontWeight = FontWeight.Normal,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        // Additional Details Section
        Spacer(modifier = Modifier.height(16.dp))
        Text("Additional Details", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        // Release Date
        // Release Date with Icon
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.DateRange, contentDescription = "Release Date")
            Spacer(modifier = Modifier.width(4.dp))
            movie.release_date?.let {
                Text("Release Date: $it")
            }
        }

        // Runtime
        movie.runtimeWithMinutes?.let {
            Text("Runtime: $it")
        }
        // Genres
        movie.genres?.let { genres ->
            Text("Genres: ${genres.joinToString { it.name.orEmpty() }}")
        }

        // Rating
        movie.vote_average?.let {
            Text("Rating: $it")
        }

        // Original Language
        movie.original_language?.let {
            Text("Original Language: $it")
        }

        // Production Companies
        movie.production_companies?.let { companies ->
            Text("Production Companies: ${companies.joinToString { it.name.orEmpty() }}")
        }
    }
    HorizontalDivider()
}
