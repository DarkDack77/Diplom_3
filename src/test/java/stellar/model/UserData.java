package stellar.model;

public class UserData {

    private String email;
    private String password;
    private String name;
    private String accessToken;

    public UserData() {
    }

    public UserData(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public UserData(String email, String password, String name, String accessToken) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}