package org.interview.java.core.lld.designpatterns;

public class SecurityMain {

    public static void main(String[] args) {
        User user = User.builder().withUsername("Usernanme").withAddress("Address").withEmail("email").build();
        System.out.println(user);
    }
}
