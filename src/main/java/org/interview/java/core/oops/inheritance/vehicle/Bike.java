package org.interview.java.core.oops.inheritance.vehicle;

public class Bike extends Vehicle{

    public void Vehicle(){

    }

    private boolean standOff= false;
    public static String honk(String s){
        return  s+ " Bike Honk!";
    }

    public void setStandOff(boolean standOff){

        this.standOff = standOff;
    }
    @Override
    public void start(){

        if(standOff){
            System.out.println("Starting the bike");
        }
        else{

            System.out.println("Stand is still on, please off it");
        }
    }
}
