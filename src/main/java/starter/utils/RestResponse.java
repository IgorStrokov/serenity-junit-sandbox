package starter.utils;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse {
    private Response response;

    public RestResponse(Response response) {
        this.response = response;
    }

    public <T> T as(Class<T> bodyClass) {
        return this.response.getBody().as(bodyClass);
    }

    public int getStatusCode() {
        return this.response.getStatusCode();
    }

    public Boolean isError() {
        return getStatusCode() / 100 >= 4;
    }
}