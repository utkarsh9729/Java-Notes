package org.interview.java.core.collections.map;

import java.util.ArrayList;
import java.util.Objects;

import static java.util.Objects.isNull;

public class Employee {

    private int id;

    private String name;

    private String department;

    private ArrayList<String> skills;



    @Override
    public int hashCode(){
        return Objects.hash(this.name,this.id);
    }


    @Override
    public boolean equals(Object o){
        if(isNull(o)){
            return false;
        }
        Employee e = (Employee) o;
        return e.getId() == this.id && Objects.equals(e.getName(), this.name);

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public Employee(int id, String name, String department, ArrayList<String> skills) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.skills = skills;
    }
}
