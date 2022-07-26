# Fun with GraphQL
This is just a simple Spring GraphQL API that is part of a schema stitching exercise over on [Fun with GraphQL Schema Stitching](https://github.com/HeroOfTheWild/Fun-with-GraphQL-Schema-Stitching).

## Next Steps
- Mutations will be added next and we'll see how that comes into play with [Fun with GraphQL Schema Stitching](https://github.com/HeroOfTheWild/Fun-with-GraphQL-Schema-Stitching).

## Interesting things to learn from this Project
- Defining Schema Types in `schema.graphql`
- Custom GraphQL Scalar Types for validation
- QueryResolvers and how they work with our GraphQL Queries 
- Async Threads for executing GraphQL Queries
- R2DBC Query By Example (QBE)
- Custom Pagination
- Integration Test with Spring GraphQL
- Working with a local H2 Database

## Running this application.
`./gradlew clean build`
`./gradlew bootRun`

Application will be exposed on port 8083. You can send your POST request against http://localhost:8083/nintendo/project/graphql.

## Playing with this API 
Checkout the attached Postman Collection for more examples. Below is one for querying all project data based of criteria passed

```graphql
query lookUpByCriteria($teamId: NintendoTeamId!, $franchiseId: NintendoGuid!, $status: ProjectStatus!){
    projectsByCriteria(teamId: $teamId, franchiseId: $franchiseId, status: $status) {
        projectId
        franchiseId
        projectName
        teamId
        status
        lastModified
    }
}
```

```json 
{
    "teamId" : "nintendo01",
    "franchiseId" : "n1nt3nd0-11a8-4d5b-beda-franch1s3001",
    "status": "COMPLETED"
}
```