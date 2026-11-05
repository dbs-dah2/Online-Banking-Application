# Online-Banking-Application

## Running on AWS Lambda

The Spring Boot REST API runs unchanged on AWS Lambda through
[aws-serverless-java-container](https://github.com/aws/serverless-java-container).
API Gateway forwards every request to `com.dbs.bank.lambda.StreamLambdaHandler`,
which dispatches it to the existing controllers under `/api/v1`.

Requirements: Java 21 and Maven 3.6.3 or later (the bundled `mvnw` uses 3.9.11).
The app uses Spring Boot 3.5, Hibernate 6 and Jakarta EE (`jakarta.*`).

Running locally with `mvn spring-boot:run` still works as before. API docs are
served by springdoc at `/swagger-ui.html` and `/v3/api-docs`.

### Build

```bash
cd BankingApplication
mvn -Plambda clean package
# produces target/BankingApplication-0.0.1-SNAPSHOT-lambda.zip
```

### Deploy

Prerequisites: AWS SAM CLI, a MySQL database reachable from the chosen VPC
subnets (Amazon RDS, ideally behind RDS Proxy), and a Secrets Manager secret
with `username` and `password` keys.

```bash
cd BankingApplication
sam deploy --guided
```

SAM prompts for `DatabaseUrl`, `DatabaseSecretArn`, `SubnetIds` and
`SecurityGroupIds`. The `ApiBaseUrl` stack output is the new API root, for
example `GET <ApiBaseUrl>/branch`.

### Notes

- Runtime is `java21` (Amazon Linux 2023).
- The zip is larger than Lambda's 50 MB direct-upload limit, so deploy it
  through S3, which `sam deploy` does automatically.
- Hibernate 6 generates IDs from per-entity `<entity>_seq` tables instead of
  the single `hibernate_sequence` table used before; start from a fresh
  schema or migrate existing ID sequences.
- Database settings come from `SPRING_DATASOURCE_*` environment variables,
  which override `application.properties`.
- The JSP view is not served from Lambda; only the REST API is.
