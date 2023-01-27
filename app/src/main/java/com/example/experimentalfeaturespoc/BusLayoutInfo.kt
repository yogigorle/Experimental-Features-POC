package com.example.experimentalfeaturespoc

import com.google.gson.annotations.SerializedName

data class BusLayoutInfo(
    val DestinationStationID: String,
    val JourneyDate: String,
    val Route: String,
    val SeatLayoutID: Int,
    val ServiceID: String,
    val SourceStationID: String,
    @SerializedName("TotalSeatList")
    val totalSeatList: TotalSeatList,
    val additionalInfoValue: String,
    val fareDetails: Any,
    val isAcSeat: Any,
    val isFareCallRequired: String,
    val lowerDividerRow: String,
    val lowerTotalColumns: String,
    val lowerTotalRows: String,
    val maxChildAge: String,
    val maxNumberOfSeats: Int,
    val minEligibleAge: Int,
    val minimumAge: String,
    val operatorId: Int,
    val seatLayoutUniqueId: String,
    val tentativeSeats: Any,
    val upperDividerRow: Int,
    val upperTotalColumns: String,
    val upperTotalRows: Int,
) {
    data class TotalSeatList(
        val lowerdeck_seat_nos: List<LowerdeckSeatNo>,
        val upperdeck_seat_nos: List<LowerdeckSeatNo>,
    ) {
        data class LowerdeckSeatNo(
            val SeatNo: String,
            val RowNo: Int,
            val ColNo: Int,
            val Availability: String = "",
            val Fare: Double = 0.0,
            val Gender: String = "",
            val SeatType: String= "",
            val SeatTypeId: String= "",
            val ServiceTax: Double= 0.0,
            val childFare: Double= 0.0,
            val orientation: String= "",
        )
    }
}