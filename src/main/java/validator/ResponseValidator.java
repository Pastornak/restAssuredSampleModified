package validator;

import io.restassured.response.Response;
import models.ResponseError;
import org.testng.Assert;
import utils.ResponseToModel;

public class ResponseValidator {
    private Response response;
    private static final ResponseToModel parser = new ResponseToModel();

    public ResponseValidator(Response response) {
        this.response = response;
    }

    public ResponseValidator statusCode(int expectedCode) {
        int actualCode = this.response.statusCode();
        Assert.assertEquals(actualCode, expectedCode, String.format("\nStatus code is: '%s'.\nExpected: %s", actualCode, expectedCode));
        return this;
    }

    public ResponseValidator errorMessage(String expectedMessage) {
        ResponseError actualError = ResponseValidator.parser.getError(this.response);
        String actualMessage = actualError.errorMessage;
        Assert.assertEquals(actualMessage, expectedMessage, String.format("\nError message is unexpected:\n%s - actual\n%s - expected\n", actualMessage, expectedMessage));
        return this;
    }
}
