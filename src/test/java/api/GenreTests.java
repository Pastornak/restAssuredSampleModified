package api;


import io.restassured.response.Response;
import models.QueryOptions;
import models.genre.Genre;
import org.testng.annotations.Test;
import service.GenreService;
import utils.ResponseToModel;

import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class GenreTests extends BaseApiTest {

    @Test (description = "Positive check for creation a genre")
    public void verifyPostCreateGenre () {
        String testName = "test6";
        Response responseCreateEntity =  new GenreService().createGenre(Genre.builder().name("test6").build());
        Genre response = new ResponseToModel().getAsGenreClass(responseCreateEntity);
        validator
                .validateStatusCode(responseCreateEntity.getStatusCode(),SC_CREATED)
                .validateGenreName(response.name, testName);

        new GenreService().deleteGenre(response.genreId);
    }

    @Test (description = "Positive check for search genres")
    public void verifyGetSearchGenres () {
        List<String> expectedGenres = List.of("test1", "test2", "test3", "test4", "test5");
        Response response = new GenreService().getGenres(new QueryOptions());
        validator
                .validateStatusCode(response.getStatusCode(),SC_OK)
                .validateMultipleGenresResponseByName(response, expectedGenres);
    }
}
