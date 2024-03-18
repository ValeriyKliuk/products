package ca.kliuk.products

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

@Serializable
class Product(
    val productID: Int,
    val price: Int,
    val productName: String,
    val productShortName: String,
    val productDescription: String,
    val productShortDescription: String,
    val colors: List<String>,
    val height: Int,
    val width: Int,
    val depth: Int,
    val rating: Int,
    val quantity: Int,
    val shipping: String,
    val imageName: String,
    val bigImageName: String,
    val storeID: Int
) {
    fun store(): Store = jsonData.stores.single { it.id == storeID}
}

@Serializable
class Store(
    val id: Int,
    val name: String,
    private val open: String,
    private val close: String,
) {
    fun open() = LocalDateTime.parse(open)
    fun close() = LocalDateTime.parse(close)
}

@Serializable
class Data(
    val products: List<Product>,
    val stores: List<Store>
)

val jsonData: Data by lazy {
    Data::class.java.classLoader.getResourceAsStream("data.json")!!.use {
        Json.decodeFromStream(it)
    }
}