package ca.kliuk.products

import com.expediagroup.graphql.generator.hooks.SchemaGeneratorHooks
import graphql.language.StringValue
import graphql.schema.Coercing
import graphql.schema.GraphQLScalarType
import graphql.schema.GraphQLType
import kotlinx.datetime.LocalDateTime
import kotlin.reflect.KClass
import kotlin.reflect.KType

class ScalarSchemaGeneratorHooks: SchemaGeneratorHooks {
    override fun willGenerateGraphQLType(type: KType): GraphQLType? =
        when (type.classifier as? KClass<*>) {
            LocalDateTime::class -> LocalDateTimeScalar
            else -> null
        }
}

object LocalDateTimeCoercing: Coercing<LocalDateTime, String> {
    override fun parseValue(input: Any) = LocalDateTime.parse(input.toString())
    override fun parseLiteral(input: Any) = LocalDateTime.parse((input as StringValue).value)
    override fun serialize(dataFetcherResult: Any) = dataFetcherResult.toString()
}

val LocalDateTimeScalar = GraphQLScalarType.newScalar()
    .name("LocalDateTime")
    .description("A type representing a formatted kotlinx.datetime.LocalDateTime")
    .coercing(LocalDateTimeCoercing)
    .build()