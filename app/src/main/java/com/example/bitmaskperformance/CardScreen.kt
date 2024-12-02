package com.example.bitmaskperformance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.bitmaskperformance.data.CardEntity

@Composable
fun CardScreen(viewModel: CardViewModel, modifier: Modifier = Modifier) {
    val cardList by viewModel.cardList.collectAsState()

    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
    ) {
        cardList.forEach { card ->
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