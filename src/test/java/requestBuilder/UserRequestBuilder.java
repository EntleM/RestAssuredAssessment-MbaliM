package requestBuilder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import commons.Paths;

public class UserRequestBuilder {

    public static RequestSpecification getUserSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Paths.BASE_URL)
                .addHeader("accept", "*/*")
                .addHeader("Content-Type", "application/json")
                .build();
    }
}