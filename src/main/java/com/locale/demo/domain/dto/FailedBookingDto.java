package com.locale.demo.domain.dto;

import com.locale.demo.domain.FailedBooking;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Parisana
 */
@Getter
public class FailedBookingDto {

    private final Long id;
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
    private final String fromDate;
    private final String toDate;
    private final Byte onlineBooking;

    private final Byte mobileSiteBooking;

    private final String bookingCreated;

    private final Float fromLat;

    private final Float fromLong;
    private final Float toLat;

    private final Float toLong;
    private final Byte carCancellation;

    private String uploadDate;

    public FailedBookingDto() {
        this.id = null;
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
        this.toDate = null;
        this.onlineBooking = null;
        this.mobileSiteBooking = null;
        this.bookingCreated = null;
        this.fromLat = null;
        this.fromLong = null;
        this.toLat = null;
        this.toLong = null;
        this.carCancellation = null;
    }

    public FailedBookingDto(FailedBooking failedBooking) {
        this.id = failedBooking.getId();
        this.bookingId = failedBooking.getBookingId();
        this.userId = failedBooking.getUserId();
        this.vehicleModelId = failedBooking.getVehicleModelId();
        this.packageId = failedBooking.getPackageId();
        this.travelTypeId = failedBooking.getTravelTypeId();
        this.fromAreaId = failedBooking.getFromAreaId();
        this.toAreaId = failedBooking.getToAreaId();
        this.fromCityId = failedBooking.getFromCityId();
        this.toCityId = failedBooking.getToCityId();
        this.fromDate = convertToString(failedBooking.getFromDate());
        this.toDate = convertToString(failedBooking.getToDate());
        this.onlineBooking = failedBooking.getOnlineBooking();
        this.mobileSiteBooking = failedBooking.getMobileSiteBooking();
        this.bookingCreated = convertToString(failedBooking.getBookingCreated());
        this.fromLat = failedBooking.getFromLat();
        this.fromLong = failedBooking.getFromLong();
        this.toLat = failedBooking.getToLat();
        this.toLong = failedBooking.getToLong();
        this.carCancellation = failedBooking.getCarCancellation();
        this.uploadDate = convertToString(failedBooking.getUploadDate());
    }
    private String convertToString(Date date) {
        if (date == null)
            return "NULL";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:ss");
        return simpleDateFormat.format(date);
    }

}
