package com.locale.demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Parisana
 */

//bookingId,user_id,vehicle_model_id,package_id,travel_type_id,from_area_id,to_area_id,from_city_id,
// to_city_id,from_date,to_date,online_booking,mobile_site_booking,booking_created,from_lat,from_long,to_lat,to_long,Car_Cancellation
@Getter
@Entity
public class BookingDTO {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
    private final Long bookingId;
    private final Long userId;
    private final Integer vehicleModelId;
//    (1=4hrs & 40kms, 2=8hrs & 80kms, 3=6hrs & 60kms, 4= 10hrs & 100kms, 5=5hrs & 50kms, 6=3hrs & 30kms, 7=12hrs & 120kms)
    private final Byte packageId;

//    (1=long distance, 2= point to point, 3= hourly rental).
    private final Byte travelTypeId;
    private final Integer fromAreaId;
    private final Integer toAreaId;
    private final Integer fromCityId;
    private final Integer toCityId;
    private final Date fromDate;
    private final Date toDate;
    private final Byte onlineBooking;

    private final Byte mobileSiteBooking;

    private final Date bookingCreated;

    private final Float fromLat;

    private final Float fromLong;
    private final Float toLat;

    private final Float toLong;
    private final Byte carCancellation;

    private final Date uploadDate = GregorianCalendar.getInstance().getTime();

    public BookingDTO(){
        this.id = null;
        this.customer = null;
        this.bookingId = null;
        this.userId = null;
        this.vehicleModelId = null;
        this.packageId = null;
        this.travelTypeId = null;
        this.fromAreaId = null;
        this.toAreaId = null;
        this.fromCityId = null;
        this.toCityId = null;
        this.fromDate = null;
        this.toDate =null;
        this.onlineBooking = null;
        this.mobileSiteBooking = null;
        this.bookingCreated = null;
        this.fromLat = null;
        this.fromLong = null;
        this.toLat =null;
        this.toLong = null;
        this.carCancellation = null;
    }

    @JsonCreator
    public BookingDTO(@JsonProperty(value = "id", required = true) Long bookingId,
                      @JsonProperty(value = "user_id", required = true) Long userId,
                      @JsonProperty("vehicle_model_id") Integer vehicleModelId,
                      @JsonProperty("package_id") Byte packageId,
                      @JsonProperty("travel_type_id") Byte travelTypeId,
                      @JsonProperty(value = "from_area_id", required = true) Integer fromAreaId,
                      @JsonProperty("to_area_id") Integer toAreaId,
                      @JsonProperty("from_city_id") Integer fromCityId,
                      @JsonProperty("to_city_id") Integer toCityId,
                      @JsonProperty("from_date") @JsonFormat(pattern = "MM/dd/yyyy hh:mm") Date fromDate,
                      @JsonProperty("to_date") @JsonFormat(pattern = "MM/dd/yyyy hh:mm") Date toDate,
                      @JsonProperty("online_booking") Byte onlineBooking,
                      @JsonProperty("mobile_site_booking") Byte mobileSiteBooking,
                      @JsonProperty("booking_created")@JsonFormat(pattern = "MM/dd/yyyy hh:mm") Date bookingCreated,
                      @JsonProperty(value = "from_lat", required = true) Float fromLat,
                      @JsonProperty(value = "from_long", required = true) Float fromLong,
                      @JsonProperty("to_lat") Float toLat,
                      @JsonProperty("to_long") Float toLong,
                      @JsonProperty("Car_Cancellation") Byte carCancellation) {
        this.id = null;
        this.bookingId = bookingId;
        this.userId = userId;
        this.vehicleModelId = vehicleModelId;
        this.packageId = packageId;
        this.travelTypeId = travelTypeId;
        this.fromAreaId = fromAreaId;
        this.toAreaId = toAreaId;
        this.fromCityId = fromCityId;
        this.toCityId = toCityId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.onlineBooking = onlineBooking;
        this.mobileSiteBooking = mobileSiteBooking;
        this.bookingCreated = bookingCreated;
        this.fromLat = fromLat;
        this.fromLong = fromLong;
        this.toLat = toLat;
        this.toLong = toLong;
        this.carCancellation = carCancellation;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", vehicleModelId=" + vehicleModelId +
                ", packageId=" + packageId +
                ", travelTypeId=" + travelTypeId +
                ", fromAreaId=" + fromAreaId +
                ", toAreaId=" + toAreaId +
                ", fromCityId=" + fromCityId +
                ", toCityId=" + toCityId +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", onlineBooking=" + onlineBooking +
                ", mobileSiteBooking=" + mobileSiteBooking +
                ", bookingCreated=" + bookingCreated +
                ", fromLat=" + fromLat +
                ", fromLong=" + fromLong +
                ", toLat=" + toLat +
                ", toLong=" + toLong +
                ", carCancellation=" + carCancellation +
                '}';
    }

//    //    1/1/2013 2:00
//    public void setToDate(String toDate) {
//        this.toDate = convertToInstant(toDate);
//    }
//
//    public void setFromDate(String toDate) {
//        this.fromDate = convertToInstant(toDate);
//    }
//    public void setBookingCreated(String dateTime) {
//        this.bookingCreated = convertToInstant(dateTime);
//    }
}
