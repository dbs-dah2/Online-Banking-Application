# Online-Banking-Application

## Running on AWS Lambda

The Spring Boot REST API runs unchanged on AWS Lambda through
[aws-serverless-java-container](https://github.com/aws/serverless-java-container).
API Gateway forwards every request to `com.dbs.bank.lambda.StreamLambdaHandler`,
which dispatches it to the existing controllers under `/api/v1`.

Running locally with `mvn spring-boot:run` still works as before.

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

- Runtime is `java8.al2023`, so the Java 8 code base is unchanged.
- Database settings come from `SPRING_DATASOURCE_*` environment variables,
  which override `application.properties`.
- The JSP view is not served from Lambda; only the REST API is.
