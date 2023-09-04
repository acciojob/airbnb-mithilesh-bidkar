package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;

import java.util.List;

public class HotelManagementService {

    HotelManagementRespository repo = new HotelManagementRespository();
    public String addHotel(Hotel hotel) {

        return  repo.addHotel(hotel);
    }

    public Integer addUser(User user) {


        return repo.addUser(user);
    }

    public String getHotelWithMostFacilities() {


        return repo.getHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking) {

        return repo.bookARoom(booking);
    }

    public int getBookings(Integer aadharCard) {

        return repo.getBookings(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {

        return repo.updateFacilities(newFacilities,hotelName);
    }
}
