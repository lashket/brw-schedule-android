package by.railway.schedule.domain.models

import by.railway.schedule.R
import java.io.Serializable

data class Train(
    val name: String,
    val trainNumber: String,
    val platformNumber: String,
    val lineNumber: String,
    val trainType: TrainType,
    val departureTime: String,
    val arrivingTime: String,
    val totalTime: String = "none",
    val vagons: ArrayList<WagonTypeModel>? = null
): Serializable {

    enum class TrainType {
        AIRPORT, CITY, INTERNATIONAL, INTERREGIONAL_BUSINESS, INTERREGIONAL_ECONOMY, REGIONAL_BUSINESS, REGIONAL_ECONOMY
    }

    val iconResId: Int
    get() {
        when(trainType) {
            TrainType.AIRPORT -> return R.drawable.s_airport
            TrainType.CITY -> return R.drawable.s_city
            TrainType.INTERNATIONAL -> return R.drawable.s_international
            TrainType.INTERREGIONAL_BUSINESS -> return R.drawable.s_interregional_business
            TrainType.INTERREGIONAL_ECONOMY -> return R.drawable.s_interregional_economy
            TrainType.REGIONAL_BUSINESS -> return R.drawable.s_regional_business
            TrainType.REGIONAL_ECONOMY -> return R.drawable.s_regional_economy
        }
    }

}