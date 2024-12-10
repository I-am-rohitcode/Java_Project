/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.carrentalsystem;

/**
 *
 * @author IUSIKKIM
 */
public class BookCarInformation {
    private Car car;
    private Customer customer;
    private int days;

    public BookCarInformation(Car car,Customer customer,int days){
        this.car=car;
        this.customer=customer;
        this.days=days;
    }

    public Car getCar() {
        return car;
    }
    public Customer getCustomer() {
        return customer;
    }
    public int getDays() {
        return days;
    }

    
    
}
