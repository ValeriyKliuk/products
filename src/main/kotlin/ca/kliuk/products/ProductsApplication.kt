package ca.kliuk.products

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import com.expediagroup.graphql.server.operations.Query

@SpringBootApplication(proxyBeanMethods = false)
class ProductsApplication

// The GraphQL entry point
class RootQuery: Query {
	@GraphQLDescription("Product list")
	fun products() = jsonData.products
}

fun main(args: Array<String>) {
	runApplication<ProductsApplication>(*args) {
		addInitializers(beans {
			bean<RootQuery>()
		})
	}
}
