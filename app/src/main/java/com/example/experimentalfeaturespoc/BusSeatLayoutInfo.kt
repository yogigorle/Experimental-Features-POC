package com.example.experimentalfeaturespoc

data class BusSeatLayoutInfoApiResp(
    val status: String = "success",
    val status_code: Int = 201,
    val message: String = "",
    val data: BusLayoutInfo,
)