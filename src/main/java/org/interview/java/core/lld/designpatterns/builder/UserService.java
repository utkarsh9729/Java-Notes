package org.interview.java.core.lld.designpatterns.builder;

public class UserService {
    public static void main(String[] args) {

        User user = User.builder().name("User").id("id").activ(true).salary(100.00).dept("ABCD").build();
        System.out.println(user);
    }
}
