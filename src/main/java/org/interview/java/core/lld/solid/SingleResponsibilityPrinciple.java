package org.interview.java.core.lld.solid;

public class SingleResponsibilityPrinciple {

//     A class should only have one single responsibility, and that responsibility should be entirely encapsulated by the class.

//     For example, consider a class that handles both user authentication and user profile management. This class has two responsibilities, which can lead to complications if changes are needed in one area but not the other. Instead, we should separate these concerns into two distinct classes: one for authentication and another for profile management.

    static class UserAuthentication {
        public boolean authenticate(String username, String password) {
            // Logic for authenticating user
            return true; // Placeholder
        }
    }

    static class UserProfile {
        public void updateProfile(String username, String newInfo) {
            // Logic for updating user profile
        }
    }

    public static void main(String[] args) {
        UserAuthentication auth = new UserAuthentication();
        boolean isAuthenticated = auth.authenticate("user", "pass");

        if (isAuthenticated) {
            UserProfile profile = new UserProfile();
            profile.updateProfile("user", "newInfo");
        }
    }
}
