package by.railway.schedule.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Station(
    val prefix: String,
    val label: String,
    @SerializedName("label_tail") val labelTail: String,
    val value: String,
    @PrimaryKey val gid: String,
    val lon: String,
    val lat: String,
    val exp: String,
    val ecp: String,
    val otd: String
): Serializable