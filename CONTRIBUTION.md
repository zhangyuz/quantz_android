##  生成 graphql schema.json
``` shell script
./gradlew :app:downloadApolloSchema -Pcom.apollographql.apollo.endpoint=http://127.0.0.1:5000/graphql -Pcom.apollographql.apollo.schema=src/main/graphql/com/zhangyuzheng/quantz/schema.json
```