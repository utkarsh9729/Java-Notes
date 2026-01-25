package org.interview.java.core.lld.designpatterns;

public class User {

    private String username;
    private String email;
    private String role; // e.g., "admin", "user"
    private boolean isActive;
    private String phoneNumber;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static Builder builder(){
        return new Builder();
    }
    public static final class Builder{


        private String username;
        private String email;
        private String role; // e.g., "admin", "user"
        private boolean isActive;
        private String phoneNumber;
        private String address;

        public Builder withUsername(String username){
            this.username = username;
            return this;
        }
        public Builder withEmail(String email){
            this.email = email;
            return this;
        }
        public Builder withRole(String role){
            this.role = role;
            return this;
        }
        public Builder withIsActive(boolean isActive){
            this.isActive = isActive;
            return this;
        }
        public Builder withPhoneNumber(String phoneNumber){
            this.phoneNumber= phoneNumber;
            return this;
        }
        public Builder withAddress(String address){
            this.address = address;
            return this;
        }
        public User build(){
            User user = new User();
            user.username = this.username;
            user.email = this.email;
            user.role = this.role;
            user.isActive = this.isActive;
            user.phoneNumber = this.phoneNumber;
            user.address = this.address;
            return user;
        }
    }
}
