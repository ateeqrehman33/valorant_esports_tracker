package com.attitude.designs.valtrackr.repository

import com.attitude.designs.valtrackr.api.ImageService
import javax.inject.Inject

class ImageRepository @Inject constructor(private val api: ImageService) {
    suspend fun getAllImages() = api.getAllImages("en-GB","val")
    //suspend fun getAllImages() = api.getAllImages()
    suspend fun getUpcomingMatches(list: String) = api.getUpcomingMatches("en-GB","val",list)

    suspend fun getUpcomingMatches() = api.getUpcomingMatches("en-GB","val","109551178413356399,106109559530232966,107019646737643925,107566795186957938")

    suspend fun getSchedule(list: String) = api.getSchedule("en-GB","val",list)

    suspend fun getSchedule() = api.getSchedule("en-GB","val","109551178413356399,106109559530232966,107019646737643925,107566795186957938")


    suspend fun getBrackets(selectedtourId: String) = api.getBrackets("en-GB","val",selectedtourId)

    suspend fun getLeagues() = api.getLeagues("en-GB","val")

}