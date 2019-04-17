package by.railway.schedule.data.source.jsoup

import android.util.Log
import by.railway.schedule.domain.models.Train
import by.railway.schedule.domain.models.Station
import by.railway.schedule.domain.models.WagonTypeModel
import org.jsoup.Jsoup

class JsoupRepositoryImpl: JsoupRepository {

    override suspend fun getOnlineTableItems(): ArrayList<Train> {
        val list = ArrayList<Train>()
        val doc = Jsoup.connect("http://rasp.rw.by/ru/tablo/?set_exp=2100001").timeout(30000).userAgent("Mozilla/5.0 (X11; Solaris x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.125 Safari/537.36").get()
        val docIterator = doc.select("tr.b-train")
        for (item in docIterator) {
            var type: Train.TrainType = Train.TrainType.INTERNATIONAL
            with(item.select("i.train_type")) {
                if (hasClass("international")) type = Train.TrainType.INTERNATIONAL
                if (hasClass("interregional_business"))type = Train.TrainType.INTERREGIONAL_BUSINESS
                if (hasClass("interregional_economy")) type = Train.TrainType.INTERREGIONAL_ECONOMY
                if (hasClass("interregional_economy")) type = Train.TrainType.INTERREGIONAL_ECONOMY
                if (hasClass("regional_economy")) type = Train.TrainType.REGIONAL_ECONOMY
                if (hasClass("regional_business")) type = Train.TrainType.REGIONAL_BUSINESS
                if (hasClass("city")) type = Train.TrainType.CITY
                if (hasClass("airport")) type = Train.TrainType.AIRPORT

            }
            list.add(Train(
                    item.select("div.train_name").text(),
                    item.select("small.train_id").text(),
                    item.select("td.train_platform").text(),
                    item.select("td.train_way").text(),
                    type,
                    item.select("b.train_start-time").text(),
                    item.select("b.train_end-time").text()
                    ))
        }
        return list
    }

    override suspend fun getTripTrainsList(arriveDate: String, arriveStation: Station, destinationStation: Station): ArrayList<Train> {
        val list = ArrayList<Train>()
        val doc = Jsoup.connect("https://rasp.rw.by/ru/route/?from=" + arriveStation.value
                + "&to=" + destinationStation.value
        + "&date=" + arriveDate
        + "&from_exp=" + arriveStation.exp
        + "&from_esr=" + arriveStation.ecp
        + "&to_exp=" + destinationStation.exp
        + "&to_esr=" + destinationStation.ecp
        ).timeout(30000).userAgent("Mozilla/5.0 (X11; Solaris x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.125 Safari/537.36").get()
        val docIterator = doc.select("tr.b-train")
        for (item in docIterator) {
            var type: Train.TrainType = Train.TrainType.INTERNATIONAL
            with(item.select("i.train_type")) {
                if (hasClass("international")) type = Train.TrainType.INTERNATIONAL
                if (hasClass("interregional_business"))type = Train.TrainType.INTERREGIONAL_BUSINESS
                if (hasClass("interregional_economy")) type = Train.TrainType.INTERREGIONAL_ECONOMY
                if (hasClass("interregional_economy")) type = Train.TrainType.INTERREGIONAL_ECONOMY
                if (hasClass("regional_economy")) type = Train.TrainType.REGIONAL_ECONOMY
                if (hasClass("regional_business")) type = Train.TrainType.REGIONAL_BUSINESS
                if (hasClass("city")) type = Train.TrainType.CITY
                if (hasClass("airport")) type = Train.TrainType.AIRPORT

            }
            val wagons = ArrayList<WagonTypeModel>()
            for (vagon in item.select("td.train_item")) {
                if (!vagon.select("li.train_note").text().replace(" ", "\n").isNullOrEmpty()
                        && !vagon.select("li.train_price").text().replace(" ", "\n").isNullOrEmpty()
                        && !vagon.select("li.train_place").text().replace(" ", "\n").isNullOrEmpty()) {
                    wagons.add(WagonTypeModel(
                            vagon.select("li.train_note").text().replace(" ", "\n"),
                            vagon.select("li.train_price").text().replace(" ", "\n"),
                            vagon.select("li.train_place").text().replace(" ", "\n")
                    ))
                }
            }
            list.add(Train(
                    item.select("div.train_name").text(),
                    item.select("small.train_id").text(),
                    "",
                    "",
                    type,
                    item.select("b.train_start-time").text(),
                    item.select("b.train_end-time").text(),
                    item.select("span.train_time-total").text(),
                    wagons
            ))
        }
        return list
    }
}