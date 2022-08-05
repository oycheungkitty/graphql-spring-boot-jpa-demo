package com.example.graphql.dao

import com.example.graphql.entity.Product
import java.math.BigDecimal
import java.util.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductDao : JpaRepository<Product, BigDecimal> {
    @EntityGraph(value = "product.shop") override fun findAll(): List<Product>

    @EntityGraph(value = "product.shop") override fun findAll(paging: Pageable): Page<Product>

    @EntityGraph(value = "product.shop") override fun findById(id: BigDecimal): Optional<Product>

    @EntityGraph(value = "product.shop") fun findAllByShopId(shopId: BigDecimal): List<Product>
}
