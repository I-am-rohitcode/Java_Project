/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

 */
package com.mycompany.carrentalsystem;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author IUSIKKIM
 */
public class CarrentalService {
    private final List<Car> car;
    private final List<Customer> customers;
    private final List<BookCarInformation> bookCarInformations;

    
    public CarrentalService(){
        car =new ArrayList<>();
        customers =new ArrayList<>();
        bookCarInformations =new ArrayList<>();
    }
    
    
    public void BookedCar(Car car,Customer customer,int days){
        if(car.getNoOfAvailableCar()>0){
            car.setNoOfAvailableCar(car.getNoOfAvailableCar()-1);
            bookCarInformations.add(new BookCarInformation(car,customer,days));
        }else{
            System.out.println("Car is not Available for rend!!");
        }
        
    }
    
    
    public void ReturnCar(Car car,BookCarInformation bookCarInformation){
            car.setNoOfAvailableCar(car.getNoOfAvailableCar()+1);
            bookCarInformations.remove(bookCarInformation);
    }
    
    
    public void addCustomer(Customer cust){
        customers.add(cust); 
    }
    public void addCars(Car car){
        this.car.add(car);
    }

 @SuppressWarnings("resource")
    public void bookingcar(){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            Scanner tp = new Scanner(System.in); 
                System.out.println("\n==For Renting a Car please provide below details==");
                System.out.print("\nEnter your Customer ID(or leave blank if new Customer): ");
                String customerid=tp.nextLine().trim();
                Customer customer = null;
                if (!customerid.isEmpty()) {
                    //if the Customer id Exist
                    Optional<Customer> optionalcustomer=customers.stream().filter(c->c.getId().equalsIgnoreCase(customerid)).findAny();
                    if (optionalcustomer.isPresent()) {
                        customer = optionalcustomer.get();
                        System.out.println("Welcome back,"+customer.getName()+" !");
                        
                    } else {
                        System.out.println("Customer ID not found.Please try again or register as a new customer.");
                    }
                } else {
                    System.out.print("Enetr your name :");
                    String CustName=tp.nextLine();
                    customer = new Customer("CUSTOMER-"+(customers.size() + 1), CustName);
                    addCustomer(customer);
                    System.out.println("Welcome ,"+customer.getName()+" !");
                    System.out.println("Your Customer ID is : "+customer.getId());
                }
                System.out.print("Enter the CarID you want to rent :");
                String carid=tp.nextLine();
                System.out.print("Enetr the number of days for rental :");
                int days=tp.nextInt();
                
                Optional<Car> optionCar= car.stream().filter(c->c.getCarId().equalsIgnoreCase(carid)&&c.getNoOfAvailableCar()>0).findAny();
                if (optionCar.isEmpty()) {
                    System.out.println("Car is not available.Please try to book another Car!!");
                    Menu(); 
                }
                Car selectedCar=optionCar.get();
                System.out.println("===================================================");
                System.out.println("                  BILL RECEIPT                     ");
                System.out.println("===================================================");
                System.out.printf("%-20s: %s%n","Customer ID",customer.getId());
                System.out.printf("%-20s: %s%n","Customer Name",customer.getName());
                System.out.printf("%-20s: %s%n","Car Brand",selectedCar.getBrand());
                System.out.printf("%-20s: %s%n","Car Model",selectedCar.getModel());
                System.out.printf("%-20s: %s%n","Car Perday price",selectedCar.getPricePerDay());
                System.out.printf("%-20s: %s%n","Rental Days",days);
                System.out.printf("%-20s:%s%n","Booking Date",LocalDate.now().format(formatter));
                System.out.printf("%-20s:%s%n","Returning Date",LocalDate.now().plusDays(days).format(formatter));
                System.out.printf("%-20s: %s%n","Total price",selectedCar.claculatePrice(days));
                System.out.println("===================================================\n");
                System.out.print("Confirm rental(Y/N): ");
                String Confirmrent =tp.next();
                if(Confirmrent.equalsIgnoreCase("Y")){
                    BookedCar(selectedCar, customer, days);
                    System.out.println("Car booking is done successfully...\n");
                }else{
                    System.out.println("\nCar booking is Cancled...\n");
                }  
            }catch(Exception e){
                System.out.println("An error occurred: Please try again");
                
            }
            
       
}
    
    
    public void history(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if(bookCarInformations.isEmpty()){
            System.out.println("\nNo car are currentry booked!!");
        }
        else{
             System.out.println("\n===============Here are all the booked cars=================");
             System.out.println("----------------------------------------------------------------------------------------");
             System.out.printf("|%-10s|%-20s|%-10s|%-10s|%-5s|%-10s|%-15s|\n","Car ID","Customer","Car Brand","Model","Days","Amount","Returning Date");
             System.out.println("----------------------------------------------------------------------------------------");
             bookCarInformations.forEach(b->{
                 System.out.printf("|%-10s|%-20s|%-10s|%-10s|%-5s|%-10s|%-15s|\n",
                         b.getCar().getCarId(),b.getCustomer().getName(),
                         b.getCar().getBrand(),b.getCar().getModel(),b.getDays(),b.getCar().claculatePrice(b.getDays()),LocalDate.now().plusDays(b.getDays()).format(formatter));
             });
            System.out.println("----------------------------------------------------------------------------------------");
        }
    }
    
    
    public void availablecar(){
        
        System.out.println("\n=====Here are the available cars=====");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("|%-10s|%-15s|%-10s|%-10s|%-10s|\n","Car ID","Car Brand","Model","Available","PerDayPrice");
        System.out.println("--------------------------------------------------------------");
        car.stream().filter(c->c.getNoOfAvailableCar()>0).forEach(p->{
            System.out.printf("|%-10s|%-15s|%-10s|%-10s|%-11s|\n",
                    p.getCarId(),p.getBrand(),p.getModel(),p.getNoOfAvailableCar(),p.getPricePerDay());
            System.out.println("--------------------------------------------------------------");
        });
    }
    
    
    @SuppressWarnings({"resource", "empty-statement"})
    public void returning(){
        Scanner pc=new Scanner(System.in);
        System.out.println("\n================Return a Booked Car================");
        System.out.print("Enter the car Id you want to return: ");
        String carid=pc.next();
        Optional<Car> optionCar=car.stream().filter(c->c.getCarId().equalsIgnoreCase(carid)).findAny();
        if(optionCar.isEmpty()){
            System.out.println("\nPlese provide valid car details.");;
            Menu();
        }
        Car cartoReturn = optionCar.get();
        BookCarInformation bookcarinformation=bookCarInformations.stream().filter(b->b.getCar()==cartoReturn).findFirst().orElse(null);
         if (bookcarinformation==null) {
             System.out.println("\nCar Information not available.Please provid valid details.!!");
             Menu();
            return;
        }
         System.out.print("Has the customer paid the bill? (Y/N):");
         String paymentConfirmation=pc.next();
         if(paymentConfirmation.equalsIgnoreCase("Y")){
         Customer cust=bookcarinformation.getCustomer();
         ReturnCar(cartoReturn, bookcarinformation);
         System.out.println("Car returned successfully by : "+cust.getName());
         System.out.println("===================================================");
         }else{
             System.out.println("\nCar cannot be returned until the bill is paid.");
         }
    }

public void displayCustomer() {
    if (customers.isEmpty()) {
        System.out.println("\nNo customers found.");
        return;
    }
    System.out.println("\n=========List of Customers=========");
    System.out.println("-----------------------------------");
    System.out.printf("|%-12s|%-20s|\n","Customer ID","Customer Name");
    System.out.println("-----------------------------------");
    customers.stream().forEach(c->System.out.printf("|%-12s|%-20s|\n",c.getId(),c.getName()));
    System.out.println("-----------------------------------");
}

    
    
    public void Menu(){
        Scanner tp = new Scanner(System.in);
        try{
        
        while(true){
        System.out.println("\n========== Welcome to Our Car Rental Service ==========");
        System.out.println("--------------------------------------------------------");
        System.out.println("|1. Book a Car                                         |");
        System.out.println("|2. Return a Booked Car                                |");
        System.out.println("|3. View Available Car                                 |");
        System.out.println("|4. View All Booked Cars                               |");
        System.out.println("|5. List of the Customer                               |");
        System.out.println("|6. Exit                                               |");
        System.out.println("--------------------------------------------------------");
        System.out.print("Please choose an option by entering the corresponding number: ");
        
        int choice=tp.nextInt();
            
            switch (choice) {
                case 1 -> bookingcar();
                    case 2 -> returning();
                    case 3 -> availablecar();
                    case 4 -> history();
                    case 5 -> displayCustomer();
                    case 6 -> {
                        System.out.println("Exit!!!");
                        System.out.println("\nThank you for using our service! Have a great day!");
                        return;
                }
                default -> {
                }
            }
            
        } 
    }catch (Exception e) {
            // TODO: handle exception
            System.out.println("An error occurred: Please try again");
           Menu();
        }
        
    tp.close();
}
}
