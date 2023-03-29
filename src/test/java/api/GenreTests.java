package api;


import io.restassured.response.Response;
import models.QueryOptions;
import models.genre.Genre;
import org.testng.annotations.Test;
import service.GenreService;
import utils.ResponseToModel;
import validator.GenreValidator;
import validator.ResponseValidator;

import static org.apache.http.HttpStatus.*;

public class GenreTests {
    private final GenreService genreService = new GenreService();

    @Test (description = "Positive check for creation a genre")
    public void verifyPostCreateGenre () {
        String testName = "test6";
        // create test data + expected result
        Genre genre = Genre.builder().name(testName).build();
        // send request to create genre
        Response response = genreService.createGenre(genre);
        // validate status code
        new ResponseValidator(response).statusCode(SC_CREATED);
        // validate genre was created properly based on input data
        new GenreValidator(response).genreName(genre.name);
        // retrieve genre model from response to have genreId
        genre = new ResponseToModel().getAsGenreClass(response);
        // check genre was properly saved by the API through GET
        Response responseGetGenre = genreService.getGenreById(genre.genreId);
        new ResponseValidator(responseGetGenre).statusCode(SC_OK);
        new GenreValidator(responseGetGenre).genreIs(genre);
        // delete created genre to cleanup after test
        genreService.deleteGenre(genre.genreId);
    }

    @Test (description = "Positive check for search genres")
    public void verifyGetSearchGenres () {
        int expectedSize = 2;
        Response response = genreService.getGenres(new QueryOptions(1, true, expectedSize));
        new ResponseValidator(response).statusCode(SC_OK);
        new GenreValidator(response).genresCount(expectedSize);
    }

    @Test (description = "Negative check for creation a genre")
    public void verifyPostErrorCreateGenre () {
        Response response = genreService.createGenre(Genre.builder().build());
        new ResponseValidator(response).statusCode(SC_BAD_REQUEST).errorMessage("Value 'name' is required!");
    }

    @Test (description = "Positive check for delete genre")
    public void verifyDeleteGenre () {
        Genre genre = Genre.builder().name("testName").build();
        Response responseCreateEntity = genreService.createGenre(genre);
        new ResponseValidator(responseCreateEntity).statusCode(SC_CREATED);
        genre = new ResponseToModel().getAsGenreClass(responseCreateEntity);

        Integer genreId = genre.genreId;
        Response responseDeleteEntity = genreService.deleteGenre(genreId);
        new ResponseValidator(responseDeleteEntity).statusCode(SC_NO_CONTENT);

        Response responseCheckEntity = genreService.getGenreById(genreId);
        new ResponseValidator(responseCheckEntity).statusCode(SC_NOT_FOUND).errorMessage(String.format("Genre with 'genreId' = '%s' doesn't exist!", genreId));
    }
}
