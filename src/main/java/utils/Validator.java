package utils;

import io.restassured.response.Response;
import models.genre.Genre;
import org.testng.Assert;

import java.util.List;

public class Validator {
        public EndpointBuilder endpointBuilder = new EndpointBuilder();

        public Validator validateStatusCode(int actualCode, int expectedCode) {
                Assert.assertEquals(actualCode, expectedCode, String.format(
                        "\nStatus code is: '%s'.\nExpected: %s", actualCode, expectedCode));
                return this;
        }

        public Validator validateGenreName(String actualName, String expectedName) {
                Assert.assertEquals(actualName, expectedName, String.format(
                        "\nGenre Name is: '%s'.\nExpected: %s", actualName, expectedName));
                return this;
        }

        public Validator validateMultipleGenresResponseByName(Response response, List<String> expected) {
                List<Genre> genres = new ResponseToModel().getAsGenreClassArray(response);
                Assert.assertEquals(genres.size(), expected.size());
                genres.forEach(genre -> Assert.assertTrue(expected.contains(genre.getName())));
                return this;
        }
}