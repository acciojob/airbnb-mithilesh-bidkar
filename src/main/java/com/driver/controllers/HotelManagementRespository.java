package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import io.swagger.models.auth.In;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class HotelManagementRespository {

    HashMap<String,Hotel> hotelDB = new HashMap<>();
    HashMap<String,Booking> bookingDB = new HashMap<>();

    HashMap<Integer,User> userDB = new HashMap<>();


    public String addHotel(Hotel hotel) {

        if (hotel.getHotelName() == null || hotel == null || hotelDB.containsKey(hotel.getHotelName())){
            return "FAILURE";
        }

        hotelDB.put(hotel.getHotelName(),hotel);

        return "SUCCESS";
    }

    public Integer addUser(User user) {

        Integer adhaar = user.getaadharCardNo();

        userDB.put(adhaar,user);

        return adhaar;
    }

    public String getHotelWithMostFacilities() {

        int noOfFacilities = 0;
        String ans = "";

        for(String hotelName : hotelDB.keySet()){
            Hotel hotel = hotelDB.get(hotelName);

            if (hotel.getFacilities().size() > noOfFacilities){
                noOfFacilities = hotel.getFacilities().size();
                ans = hotelName;
            }else if(hotel.getFacilities().size() == noOfFacilities){

                if (hotelName.compareTo(ans) < 0){

                    ans = hotelName;
                }
            }
        }

        return ans;

    }

    public int bookARoom(Booking booking) {
        String BID = booking.getBookingId();
        String hotelName = booking.getHotelName();
        Hotel hotel = hotelDB.get(hotelName);

        if(hotel.getAvailableRooms() < booking.getNoOfRooms()){
            return  -1;
        }

        booking.setBookingId(String.valueOf(UUID.randomUUID()));
        int amountPaid = booking.getNoOfRooms() * hotel.getPricePerNight();
        hotel.setAvailableRooms(hotel.getAvailableRooms() - booking.getNoOfRooms());
        booking.setAmountToBePaid(amountPaid);

        bookingDB.put(BID,booking);
        hotelDB.put(hotelName,hotel);



        return booking.getAmountToBePaid();



    }

    public int getBookings(Integer aadharCard) {

        int count = 0;

         for(String BID : bookingDB.keySet()){
             Booking booking = bookingDB.get(BID);

             if(booking.getBookingAadharCard() == aadharCard){
                 count++;
             }
         }

         return  count;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {

        Hotel hotel = hotelDB.get(hotelName);
        List<Facility> curr = hotel.getFacilities();

        for(Facility f : newFacilities){

            if(!curr.contains(f)){
                curr.add(f);
            }
        }

        hotel.setFacilities(curr);

        hotelDB.put(hotelName,hotel);

        return hotel;

    }
}
