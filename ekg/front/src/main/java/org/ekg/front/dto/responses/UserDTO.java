package org.ekg.front.dto.responses;

public class UserDTO {
    private String userName;
    private String roleName;
    private String firstName;
    private String lastName;

    public UserDTO() {}

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getRoleName() {
        return roleName;
    }
    public String getUserName() {
        return userName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
}

