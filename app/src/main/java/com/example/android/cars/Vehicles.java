package com.example.android.cars;

 public class Vehicles {

     private int id;
     private String vehiclename,vehicleplate,enginecapacity,odometer;


     public Vehicles(int id, String vehiclename, String vehicleplate, String enginecapacity, String odometer) {
         this.id = id;
         this.vehiclename = vehiclename;
         this.vehicleplate = vehicleplate;
         this.enginecapacity = enginecapacity;
         this.odometer = odometer;
     }

     public int getId() {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }

     public String getVehiclename() {
         return vehiclename;
     }

     public void setVehiclename(String vehiclename) {
         this.vehiclename = vehiclename;
     }

     public String getVehicleplate() {
         return vehicleplate;
     }

     public void setVehicleplate(String vehicleplate) {
         this.vehicleplate = vehicleplate;
     }

     public String getEnginecapacity() {
         return enginecapacity;
     }

     public void setEnginecapacity(String enginecapacity) {
         this.enginecapacity = enginecapacity;
     }

     public String getOdometer() {
         return odometer;
     }

     public void setOdometer(String odometer) {
         this.odometer = odometer;
     }
 }
