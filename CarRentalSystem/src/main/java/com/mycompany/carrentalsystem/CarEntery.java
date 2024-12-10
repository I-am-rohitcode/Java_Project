//saf
package com.mycompany.carrentalsystem;

public class CarEntery {
    public static void main(String[] args) {

      Car Mahindra=new Car();
       Mahindra.setCarId("Car-1");
       Mahindra.setBrand("Mahindra");
       Mahindra.setModel("Scorpio");
       Mahindra.setNoOfAvailableCar(1);
       Mahindra.setPricePerDay(4000);

       Car Maruti=new Car();
       Maruti.setCarId("Car-2");
       Maruti.setBrand("Maruti");
       Maruti.setModel("Dzire");
       Maruti.setNoOfAvailableCar(4);
       Maruti.setPricePerDay(2500);

       Car Tata=new Car();
       Tata.setCarId("Car-3");
       Tata.setBrand("Tata");
       Tata.setModel("Nexon.ev");
       Tata.setNoOfAvailableCar(5);
       Tata.setPricePerDay(5000);

       Car Toyota=new Car();
       Toyota.setCarId("Car-4");
       Toyota.setBrand("Toyota");
       Toyota.setModel("Fortuner");
       Toyota.setNoOfAvailableCar(3);
       Toyota.setPricePerDay(3500);

       Car Audi=new Car();
       Audi.setCarId("Car-5");
       Audi.setBrand("Audi");
       Audi.setModel("A4");
       Audi.setNoOfAvailableCar(2);
       Audi.setPricePerDay(3000);
       
      CarrentalService carRentalService=new CarrentalService();
      carRentalService.addCars(Mahindra);
      carRentalService.addCars(Maruti);
      carRentalService.addCars(Tata);
      carRentalService.addCars(Toyota);
      carRentalService.addCars(Audi);
       
      carRentalService.Menu();
      

    }
    
}
