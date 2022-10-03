package client;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.BaseModel;
import models.genre.Genre;
import utils.EnvConfig;
import utils.PropertiesReader;

import static io.restassured.RestAssured.given;

public class HttpClient {
    static PropertiesReader env = EnvConfig.getInstance();
    public static Response get(String endpoint) {
        return HttpClient.sendRequest(Method.GET, endpoint);
    }

    public static Response post(String endpoint, Genre body) {
        return HttpClient.sendRequest(Method.POST, endpoint, body);
    }

    public static Response put(String endpoint, Genre body) {
        return HttpClient.sendRequest(Method.PUT, endpoint, body);
    }

    public static Response delete(String endpoint) {
        return HttpClient.sendRequest(Method.DELETE, endpoint);
    }

    private static Response sendRequest(Method method, String endpoint) {
        return HttpClient.sendRequest(method, endpoint, null);
    }

    private static Response sendRequest(Method method, String endpoint, Genre body) {
        String url = env.getProperty("service.host") + endpoint;
        RequestSpecification spec = given().auth().preemptive().basic("admin", "password");
        if (body != null) spec.contentType("application/json").body(body);
        return spec.request(method, url);
    }
}
