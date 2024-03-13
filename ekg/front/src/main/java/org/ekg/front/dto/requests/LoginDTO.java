package org.ekg.front.dto.requests;

public class LoginDTO {

    private String username;
    private String password;

    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public LoginDTO(){}
    public LoginDTO(String username, String password){

        this.username = username;
        this.password = password;
    }
    
}
