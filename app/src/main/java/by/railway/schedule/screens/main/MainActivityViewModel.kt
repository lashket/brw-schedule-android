package by.railway.schedule.screens.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import by.railway.schedule.base.BaseViewModel
import by.railway.schedule.domain.models.Ticket
import by.railway.schedule.domain.usecase.InsertTicketToDbUseCase
import by.railway.schedule.utils.Helper
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions
import java.util.*
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
        private val insertTicketToDbUseCase: InsertTicketToDbUseCase
): BaseViewModel() {

    private fun insertTicketToDb(ticket: Ticket) {
        insertTicketToDbUseCase.ticket = ticket
        insertTicketToDbUseCase.execute {
            onComplete {
                statusLiveData.postValue(Status.COMPLETE)
            }
            onError {
                statusLiveData.postValue(Status.ERROR)
            }
        }
    }

    fun handleIncomingIntent(intent: Intent, context: Context) {
        val imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM) as Uri
        val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
        val firebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap)
        val options = FirebaseVisionCloudTextRecognizerOptions.Builder().setLanguageHints(Arrays.asList("en", "hi")).build()
        val detector = FirebaseVision.getInstance()
                .getCloudTextRecognizer(options)
        detector.processImage(firebaseVisionImage)
                .addOnSuccessListener { firebaseVisionText ->
                    insertTicketToDb(Helper.getTicket(firebaseVisionText.text))
                }
                .addOnFailureListener {

                }
    }

}
