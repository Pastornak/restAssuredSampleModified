package validator;

import io.restassured.response.Response;
import models.genre.Genre;
import org.testng.Assert;
import utils.ResponseToModel;

import java.util.List;

public class GenreValidator {
    private Response response;
    private static final ResponseToModel parser = new ResponseToModel();

    public GenreValidator(Response response) {
        this.response = response;
    }

    public GenreValidator genreIs(Genre expectedGenre) {
        Genre actualGenre = GenreValidator.parser.getAsGenreClass(this.response);
        Assert.assertEquals(actualGenre, expectedGenre, String.format("\nGenre is unexpected:\nactual:\n%s\nexpected:\n%s\n", actualGenre.toString(), expectedGenre.toString()));
        return this;
    }

    public GenreValidator genreName(String name) {
        Genre actualGenre = GenreValidator.parser.getAsGenreClass(this.response);
        String actualName = actualGenre.name;
        Assert.assertEquals(actualName, name, String.format("\nGenre name is unexpected:\n%s - actual\n%s - expected\n", actualName, name));
        return this;
    }

    public GenreValidator genresCount(int count) {
        List<Genre> genres = GenreValidator.parser.getAsGenreClassArray(this.response);
        int actualCount = genres.size();
        Assert.assertEquals(actualCount, count, String.format("\nGenres count is unexpected:\n%s - actual\n%s - expected\n", actualCount, count));
        return this;
    }
}
