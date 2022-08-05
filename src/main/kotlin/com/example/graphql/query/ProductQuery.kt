package com.example.graphql.query

import com.example.graphql.dao.ProductDao
import com.example.graphql.dao.ShopDao
import com.example.graphql.entity.Product
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import java.math.BigDecimal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class ProductQuery {
    @Autowired lateinit var productDao: ProductDao
    @Autowired lateinit var shopDao: ShopDao

    @GraphQLQuery
    fun products(): List<Product> {
        return productDao.findAll()
    }

    @GraphQLQuery
    fun product(id: BigDecimal): Product {
        return productDao.findById(id).get()
    }

    @GraphQLQuery
    fun productsPage(paging: Pageable): Page<Product> {
        return productDao.findAll(paging)
    }

    @GraphQLMutation
    fun product(
        @GraphQLArgument(name = "shopId") shopId: BigDecimal,
        @GraphQLArgument(name = "products") productsInput: List<Product>
    ): List<Product> {
        val shop = shopDao.findById(shopId).get()
        productsInput.forEach { product -> product.shop = shop }
        productDao.saveAll(productsInput)

        return productDao.findAllByShopId(shopId)
    }
}
