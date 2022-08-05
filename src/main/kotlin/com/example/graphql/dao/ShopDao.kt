package com.example.graphql.dao

import com.example.graphql.entity.Shop
import java.math.BigDecimal
import java.util.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShopDao : JpaRepository<Shop, BigDecimal> {
    @EntityGraph(value = "shop.products") override fun findAll(): List<Shop>

    @EntityGraph(value = "shop.products") override fun findAll(paging: Pageable): Page<Shop>

    @EntityGraph(value = "shop.products") override fun findById(id: BigDecimal): Optional<Shop>
}
