package com.example.graphql.query

import com.example.graphql.dao.ProductDao
import com.example.graphql.dao.ShopDao
import com.example.graphql.entity.Shop
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.execution.relay.Page
import io.leangen.graphql.execution.relay.generic.PageFactory
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import java.math.BigDecimal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class ShopQuery {
    @Autowired lateinit var shopDao: ShopDao
    @Autowired lateinit var productDao: ProductDao

    @GraphQLQuery
    fun shops(): List<Shop> {
        return shopDao.findAll()
    }

    @GraphQLQuery
    fun shop(id: BigDecimal): Shop {
        return shopDao.findById(id).get()
    }

    @GraphQLQuery
    fun shopsPage(paging: Pageable): org.springframework.data.domain.Page<Shop> {
        return shopDao.findAll(paging)
    }

    @GraphQLQuery
    fun shopsRelayPage(
        @GraphQLArgument(name = "limit", defaultValue = "10") limit: Int,
        @GraphQLArgument(name = "offset", defaultValue = "0") offset: Int
    ): Page<Shop> {
        val result: List<Shop> = shopDao.findAll()
        val page: List<Shop> = result.subList(offset, Math.min(offset + limit, result.size))

        return PageFactory.createOffsetBasedPage(page, result.size.toLong(), offset.toLong())
    }

    @GraphQLMutation
    fun shop(@GraphQLArgument(name = "shop") shopInput: Shop): Shop {
        val shop = shopDao.save(shopInput)
        if (shopInput.products.isNotEmpty()) {
            shop.products.forEach { product -> product.shop = shop }
            productDao.saveAll(shop.products)
        }
        return shopDao.findById(shop.id!!).get()
    }
}
