package ca.kliuk.products

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import com.expediagroup.graphql.server.operations.Query

@SpringBootApplication(proxyBeanMethods = false)
class ProductsApplication

// The GraphQL entry point
class RootQuery: Query {
	fun hello() = "Hello Kotlin"
}

fun main(args: Array<String>) {
	runApplication<ProductsApplication>(*args) {
		addInitializers(beans {
			bean<RootQuery>()
		})
	}
}
