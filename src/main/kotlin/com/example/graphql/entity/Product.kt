package com.example.graphql.entity

import io.leangen.graphql.annotations.GraphQLId
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "product")
@NamedEntityGraph(name = "product.shop", attributeNodes = [NamedAttributeNode("shop")])
class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @GraphQLId var id: BigDecimal? = null
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "shop_id") var shop: Shop? = null
    var name: String? = null
    var price: BigDecimal? = null
    //    var currency: Currency? = null
}
