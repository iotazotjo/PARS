package t8.pars;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class PARSController {

    private static PARSDisplayer display;
    public static ArrayList<Flight> flightList;
    private static String db = "flightDataBase.db";
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //instantiate objects
        display = new PARSDisplayer();
        boolean loggedIn = false;
        boolean errorExists = false;
        Scanner in = new Scanner(System.in);
        
        
        
        while(true){
            //employee login
            display.printRequestEmployeeID();
            String employeeID = in.nextLine();
            display.printRequestEmployeePass();
            String employeePass = in.nextLine();
            //main menu
            loggedIn = Employee.validateEmployee(employeeID, employeePass);
            
            while(loggedIn)
            {
                display.printMainMenu();
                int act = 0;
                do
                {
                    String action = in.nextLine();
                    try
                    {
                        act = Integer.parseInt(action);errorExists = false;
                    }
                    catch(NumberFormatException e)
                    {
                        errorExists = true;
                        display.printMainMenuErrorMessage();
                    } 
                }while(errorExists);

                switch(act)
                {
                    case 1:
                        reserveSeat();break;
                    case 2:
                        getReceipt();break;
                    case 3:
                        getAvailableFlights();break;
                    case 4:
                        printAllReservations();break;
                    case 5:
                        loggedIn = false;break;
                    case 6:
                        System.exit(0);break;
                    default:
                        display.printMainMenuErrorMessage();
                }
            }
        }
    }
    
    private static void reserveSeat()
    {
        System.out.println("Reserve");
        //ask for route
        getAvailableFlights();
        //ask for departure and return dates
        //get the flights associated with each
        //get customer details
            //validate customer details
        //get credit card information
            //validate credit card info
        //create new customer
        //add this to the two flights
        //create new ticket (attach to customer and print)
        //create new reciept (attach to customer and print)
        //save data to file
        
    }
    private static void getReceipt()
    {
        System.out.println("receipt");
        
    }
    private static void getAvailableFlights()
    {
        //for each flight
        for(Flight flight:flightList)
        {
            if(flight.hasOpenSeats())
            {
                //we already know that the flight is not empty
                
                //print out Flight#, Date, and seats remaining
                display.printFlightInfo(flight);
            }
        }
    }
    
    private static void getAllFlightsInfo()
    {
        //for each flight
        for(Flight flight:flightList)
        {
            //we already know that the flight is not empty

            //print out Flight#, Date, and seats remaining
            display.printFlightInfo(flight);
        }
    }   
    
    private static void printAllReservations()
    {
        for(Flight flight:flightList)
        {//print basic flight info            
            display.printManifest(flight);
        }
    }        
 
    public void save()
    {
        try
        {//use buffering
            OutputStream file = new FileOutputStream( db );
            OutputStream buffer = new BufferedOutputStream( file );
            ObjectOutput output = new ObjectOutputStream( buffer );
            try
            {// Writes to an object
                output.writeObject(flightList); 
            }
            finally
            {
                output.close();
            }
        }  
        catch(IOException ex)
        {
            System.out.println("Error in updating database");
            System.exit(0);
        }    
    }

    
}