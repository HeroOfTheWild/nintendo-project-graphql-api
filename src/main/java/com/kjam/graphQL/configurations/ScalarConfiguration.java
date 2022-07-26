package com.kjam.graphQL.configurations;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.language.StringValue;
import graphql.scalars.ExtendedScalars;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

@Configuration
public class ScalarConfiguration {

    private final static String NINTENDO_GUID_REGEX = "(n1nt3nd0)-[a-zA-z0-9]{4}-[a-zA-z0-9]{4}-[a-zA-z0-9]{4}-[a-zA-z0-9]{12}";
    private final static String NINTENDO_TEAM_REGEX = "(nintendo)[a-zA-z0-9]{2}";

    @Bean
    public GraphQLScalarType dateTime() {
        return ExtendedScalars.DateTime;
    }

    @Bean
    public GraphQLScalarType nintendoGuid() {
        return GraphQLScalarType.newScalar()
        .name("NintendoGuid")
        .description("A Nintendo GUID")
        .coercing(new Coercing<String,String>() {

            @Override
            public String serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
                return Optional.ofNullable(dataFetcherResult)
                .filter(String.class::isInstance)
                .map(Object::toString)
                .orElseThrow(() -> new CoercingSerializeException("Unable to serialize object"));
            }

            @Override
            public @NotNull String parseValue(@NotNull Object input) throws CoercingParseValueException {
                if(input instanceof String inputString) {
                    return validateId(inputString, NINTENDO_GUID_REGEX);
                } else {
                    throw new CoercingParseValueException("Nintendo ID input not valid");
                }
            }

            @Override
            public @NotNull String parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
                if(input instanceof StringValue inputStringValue) {
                    return validateId(inputStringValue.getValue(), NINTENDO_GUID_REGEX);
                } else {
                    throw new CoercingParseLiteralException("Nintendo ID input not valid");
                }
            }
            
        })
        .build();
    }

    @Bean
    public GraphQLScalarType teamId() {
        return GraphQLScalarType.newScalar()
        .name("NintendoTeamId")
        .description("A Nintendo Team ID")
        .coercing(new Coercing<String,String>() {

            @Override
            public String serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
                return Optional.ofNullable(dataFetcherResult)
                .filter(String.class::isInstance)
                .map(Object::toString)
                .orElseThrow(() -> new CoercingSerializeException("Unable to serialize object"));
            }

            @Override
            public @NotNull String parseValue(@NotNull Object input) throws CoercingParseValueException {
                if(input instanceof String inputString) {
                    return validateId(inputString, NINTENDO_TEAM_REGEX);
                } else {
                    throw new CoercingParseValueException("Nintendo Team ID input not valid");
                }
            }

            @Override
            public @NotNull String parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
                if(input instanceof StringValue inputStringValue) {
                    return validateId(inputStringValue.getValue(), NINTENDO_TEAM_REGEX);
                } else {
                    throw new CoercingParseLiteralException("Nintendo Team ID input not valid");
                }
            }
            
        })
        .build();
    }

    protected static String validateId(final String input, final String regex) {
        if(input.matches(regex)) {
            return input;
        } else {
            throw new CoercingParseLiteralException("INVALID NINTENDO GUID");
        }
    }

}
