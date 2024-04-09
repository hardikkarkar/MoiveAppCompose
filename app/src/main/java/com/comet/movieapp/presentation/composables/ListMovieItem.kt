package com.comet.movieapp.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.comet.movieapp.data.model.IMAGE_URL
import com.comet.movieapp.data.model.MovieDomain

@Composable
fun ListMovieItem(movieDomain: MovieDomain, onClick: () -> Unit) {
    Row(Modifier.padding(8.dp).clickable(onClick = onClick)) {
        AsyncImage(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(0.3f),
            model = movieDomain.poster_path,
            contentDescription = null,
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = movieDomain.title,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movieDomain.overview,
                fontWeight = FontWeight.Normal,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
    HorizontalDivider()
}
