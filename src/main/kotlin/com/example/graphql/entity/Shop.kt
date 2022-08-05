package com.example.graphql.entity

import io.leangen.graphql.annotations.GraphQLId
import io.leangen.graphql.annotations.GraphQLInputField
import java.math.BigDecimal
import javax.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "shop")
@NamedEntityGraph(name = "shop.products", attributeNodes = [NamedAttributeNode("products")])
class Shop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @GraphQLId var id: BigDecimal? = null
    @NotNull @GraphQLInputField var name: String? = null
    @NotNull @GraphQLInputField var email: String? = null
    @OneToMany(mappedBy = "shop") val products: List<Product> = mutableListOf()
}
