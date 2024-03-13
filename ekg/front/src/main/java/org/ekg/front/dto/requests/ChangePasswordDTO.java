package org.ekg.front.dto.requests;

public class ChangePasswordDTO {
    private String username;
    private String oldPassword;
    private String newPassword;

    public ChangePasswordDTO(){}
    
    public String getNewPassword() {
        return newPassword;
    }
    public String getOldPassword() {
        return oldPassword;
    }
    public String getUsername() {
        return username;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
}
