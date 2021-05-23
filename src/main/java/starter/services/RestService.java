package starter.services;

import starter.utils.RestResponse;

import java.util.Map;

import static starter.utils.ConfigProvider.getRequestSpec;

public class RestService {

    public RestResponse get(String url, Map<String, Integer> params) {
        return new RestResponse(
                getRequestSpec()
                        .params(params)
                        .when()
                        .get(url)
        );
    }

    public RestResponse get(String url, Map<String, String> params, Object... pathParams) {
        return new RestResponse(
                getRequestSpec()
                        .params(params)
                        .when()
                        .get(url, pathParams)
        );
    }
}
