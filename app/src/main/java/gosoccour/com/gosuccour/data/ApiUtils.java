package gosoccour.com.gosuccour.data;

import gosoccour.com.gosuccour.interfaces.APIService;

/**
 * Created by alguien on 05/17/2018.
 */

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "localhost:8080/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

}
