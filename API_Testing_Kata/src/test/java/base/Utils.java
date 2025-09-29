package base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    String CONTENT_TYPE;

    public RequestSpecification requestSetup() {
        RestAssured.baseURI = ConfigReader.getproperty("appURL");
        CONTENT_TYPE = ConfigReader.getproperty("content.type");
        return RestAssured.given().contentType(CONTENT_TYPE).accept(CONTENT_TYPE);
    }

    public String generateRandomRoomId() {
        int roomId = ThreadLocalRandom.current().nextInt(1, 4); // 1 (inclusive) to 4 (exclusive)
        return String.valueOf(roomId);
    }
}
