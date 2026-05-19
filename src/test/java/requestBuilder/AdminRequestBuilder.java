package requestBuilder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import commons.Paths;

public class AdminRequestBuilder {

    private static final String ADMIN_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIxMmFlNTZkZi1iNGMyLTRlYTUtYmI3ZS1lMzNiMThiN2IyYjkiLCJlbWFpbCI6ImFkbWluQGdtYWlsLmNvbSIsInJvbGUiOiJhZG1pbiIsImlhdCI6MTc3OTE3NTk4NywiZXhwIjoxNzc5MjYyMzg3fQ.jBNUA1cj8X3Ib6PAWJl38Bq9w291RUTLTuTBmSHLKM0";

    public static RequestSpecification getAdminSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Paths.BASE_URL)
                .addHeader("Authorization", "Bearer " + ADMIN_TOKEN)
                .addHeader("Content-Type", "application/json") // Using a plain string avoids dependency errors
                .build();
    }
}