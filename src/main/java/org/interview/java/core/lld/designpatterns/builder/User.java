package org.interview.java.core.lld.designpatterns.builder;

public class User {


    private final String name;

    private final String id;

    private final  String dept;

    private final double salary;

    private final boolean active;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDept() {
        return dept;
    }


    public double getSalary() {
        return salary;
    }

    public boolean isActive() {
        return active;
    }

    public static Builder builder(){
        return new Builder();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                ", active=" + active +
                '}';
    }

    public User(String name, String id, String dept, double salary, boolean active) {
        this.name = name;
        this.id = id;
        this.dept = dept;
        this.salary = salary;
        this.active = active;
    }

    public static final class Builder{
        private String name;

        private String id;

        private String dept;

        private double salary;

        private boolean active;

      public Builder name(String name){
          this.name = name;
          return  this;
      }

      public Builder id(String id){
          this.id = id;
          return this;}
        public Builder  dept(String dept){
          this.dept = dept;
          return this;
        }

        public Builder salary(Double salary){
          this.salary = salary;
          return this;
        }

        public Builder activ( Boolean active){

          this.active = active;
          return  this;
        }

        public User build(){
          return new User(this.name,this.id,this.dept,this.salary,this.active);
        }
    }
}
