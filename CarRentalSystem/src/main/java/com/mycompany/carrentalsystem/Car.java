package com.mycompany.carrentalsystem;

public class Car {

    private String CarId;
    private String Brand;
    private String Model;
    private int NoOfAvailableCar;
    private int PricePerDay;
    public String getCarId() {
        return CarId;
    }
    public void setCarId(String carId) {
        CarId = carId;
    }
    public String getBrand() {
        return Brand;
    }
    public void setBrand(String brand) {
        Brand = brand;
    }
    public String getModel() {
        return Model;
    }
    public void setModel(String model) {
        Model = model;
    }
    public int getNoOfAvailableCar() {
        return NoOfAvailableCar;
    }
    public void setNoOfAvailableCar(int noOfAvailableCar) {
        NoOfAvailableCar = noOfAvailableCar;
    }
    public int getPricePerDay() {
        return PricePerDay;
    }
    public void setPricePerDay(int pricePerDay) {
        PricePerDay = pricePerDay;
    }
    public double claculatePrice(int days){
        return PricePerDay*days;
    }
    
 
}
