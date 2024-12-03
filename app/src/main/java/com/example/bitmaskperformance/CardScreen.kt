package com.example.bitmaskperformance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.bitmaskperformance.data.CardEntity

@Composable
fun CardScreen(viewModel: CardViewModel, modifier: Modifier = Modifier) {
    val cardList by viewModel.cardList.collectAsState()

    LazyColumn(modifier = modifier.fillMaxSize()
    ) {
        items(cardList) { card ->
            CardItem(card)
        }
    }
}

@Composable
fun CardItem(card: CardEntity) {
    Column {
        Text(text = "Name: ${card.id}")
        Text(text = "Number: ${card.col1} ${card.col2} ${card.col3}")
    }
}