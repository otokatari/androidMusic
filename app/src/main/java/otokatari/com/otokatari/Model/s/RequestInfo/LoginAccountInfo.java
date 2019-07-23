package otokatari.com.otokatari.Model.s.RequestInfo;

public class LoginAccountInfo {
    private String identifier;
    private String credentials;
    private int type;

    public int getType() {
        return type;
    }

    public String getCredentials() {
        return credentials;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setType(int type) {
        this.type = type;
    }
}
