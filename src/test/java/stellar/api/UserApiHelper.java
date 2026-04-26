package stellar.api;

import io.qameta.allure.Step;
import org.json.JSONObject;
import stellar.model.UserData;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class UserApiHelper {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";

    @Step("Генерация уникального пользователя")
    public UserData generateUniqueUser() {
        return new UserData(
                "test_" + UUID.randomUUID() + "@yandex.ru",
                "password123",
                "Test User"
        );
    }

    @Step("Регистрация пользователя через API")
    public UserData registerUser(UserData user) throws Exception {
        JSONObject body = new JSONObject();
        body.put("email", user.getEmail());
        body.put("password", user.getPassword());
        body.put("name", user.getName());

        HttpURLConnection connection = sendPostRequest("/api/auth/register", body);

        String response = new String(connection.getInputStream().readAllBytes());
        JSONObject jsonResponse = new JSONObject(response);

        if (jsonResponse.has("accessToken")) {
            user.setAccessToken(jsonResponse.getString("accessToken"));
        }

        return user;
    }

    @Step("Логин пользователя через API")
    public UserData loginUser(UserData user) throws Exception {
        JSONObject body = new JSONObject();
        body.put("email", user.getEmail());
        body.put("password", user.getPassword());

        HttpURLConnection connection = sendPostRequest("/api/auth/login", body);

        String response = new String(connection.getInputStream().readAllBytes());
        JSONObject jsonResponse = new JSONObject(response);

        if (jsonResponse.has("accessToken")) {
            user.setAccessToken(jsonResponse.getString("accessToken"));
        }

        return user;
    }

    @Step("Удаление пользователя через API")
    public void deleteUser(String accessToken) {
        try {
            URL url = new URL(BASE_URL + "/api/auth/user");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Authorization", accessToken);
            connection.setRequestProperty("Content-Type", "application/json");

            connection.getResponseCode();
            connection.disconnect();
        } catch (Exception ignored) {
        }
    }

    private HttpURLConnection sendPostRequest(String endpoint, JSONObject body) throws Exception {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(body.toString().getBytes());
            os.flush();
        }

        return connection;
    }
}