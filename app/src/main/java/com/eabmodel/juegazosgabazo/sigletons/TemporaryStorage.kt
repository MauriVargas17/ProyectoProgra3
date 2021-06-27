package com.eabmodel.juegazosgabazo.sigletons

import com.eabmodel.juegazosgabazo.objects.Product

object TemporaryStorage {
    val cart = mutableListOf<Product>()
}