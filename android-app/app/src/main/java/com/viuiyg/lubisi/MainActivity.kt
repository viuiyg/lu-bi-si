package com.viuiyg.lubisi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppRoot()
        }
    }
}

@Composable
fun AppRoot() {
    var selected by remember { mutableStateOf<String?>(null) }
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        if (selected == null) {
            ProductList(onSelect = { selected = it })
        } else {
            ProductDetail(id = selected!!, onBack = { selected = null })
        }
    }
}

@Composable
fun ProductList(onSelect: (String) -> Unit) {
    val products = remember { sampleProducts() }
    LazyColumn {
        items(products) { p ->
            Column(modifier = Modifier
                .clickable { onSelect(p.id) }
                .padding(16.dp)) {
                Text(p.name, style = MaterialTheme.typography.h6)
                Text(p.description, style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Composable
fun ProductDetail(id: String, onBack: () -> Unit) {
    val product = remember { sampleProducts().firstOrNull { it.id == id } }
    Column(modifier = Modifier.padding(16.dp)) {
        Text("< 返回", modifier = Modifier.clickable { onBack() }.padding(bottom = 8.dp))
        if (product != null) {
            Text(product.name, style = MaterialTheme.typography.h5)
            Text(product.description, style = MaterialTheme.typography.body1)
        } else {
            Text("Product not found")
        }
    }
}

data class Product(val id: String, val name: String, val description: String)

fun sampleProducts(): List<Product> = listOf(
    Product("1", "示例产品 A", "这是产品 A 的描述。"),
    Product("2", "示例产品 B", "这是产品 B 的描述。"),
    Product("3", "示例产品 C", "这是产品 C 的描述。"),
)
