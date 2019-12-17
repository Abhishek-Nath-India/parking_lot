package com.abhishek.parking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Parking {


    private static TreeMap<Integer, String> parkingSpace = new TreeMap();
    private static TreeSet<Integer> availableSlots = new TreeSet<Integer>();
    private static boolean parkingInitialized = false;
    public static void main(String... args){
        System.out.println("This is test");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(Parking.class.getResourceAsStream("file.txt")));
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                execute(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("parkingSpace: "+parkingSpace.size());
        System.out.println("availableSlots: "+availableSlots.size());
    }

    private static void execute(String command1){

       // StringTokenizer cmd = new StringTokenizer(command1," ");
        //String command = cmd.nextToken();
        String[] command2 = command1.split(" ");
        //System.out.println(result);
        String command = command2[0];
        if(command.equals("leave")){
            //System.out.println(command);
        }else if(command.equals("status")){
            //System.out.println(command);
            displayStatus();
        }else if(command.equals("park")){
            //System.out.println(command);
            parkCar(command2[1]);
        }else if(command.equals("create_parking_lot")){
            createParking("6");
        }
    }

    private static void displayStatus(){
        System.out.println("Slot No.  Registration No.");
        parkingSpace.forEach((slot, car) -> System.out.println((slot + "      " + car)));
    }
    private static void parkCar(String carNumber){
        System.out.println("Car Number: "+carNumber);
        if(isParkingAvailable()){
            Integer allocatedSlot = availableSlots.pollFirst();
            System.out.println("Car availableSlots: "+availableSlots.size());
            parkingSpace.put(allocatedSlot,carNumber);
            System.out.println("Allocated slot number: "+allocatedSlot);
        }else{
            System.out.println("Sorry, parking lot is full");
        }
    }

    private static boolean isParkingAvailable(){
        return availableSlots.size()>0;
    }
    private static void createParking(String slots){
        if(!parkingInitialized){
            IntStream.rangeClosed(1,Integer.valueOf(slots)).forEach(availableSlots::add);
            parkingInitialized =Boolean.TRUE;
            System.out.println("Parking initialized : "+ availableSlots.size());
        }else{
            System.out.println("Parking cannot be reinitialized");
        }
    }
}
