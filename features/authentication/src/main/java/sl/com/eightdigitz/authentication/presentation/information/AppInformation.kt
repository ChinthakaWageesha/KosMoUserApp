package sl.com.eightdigitz.authentication.presentation.information

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.utils.pdf.Download
import sl.com.eightdigitz.authentication.utils.pdf.DownloadingImpl
import butterknife.ButterKnife
import java.io.File
import kotlinx.android.synthetic.main.activity_app_informations.toolbar_auth

class AppInformation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_informations)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar_auth)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        TODO update title accordingly PC or  TC
        supportActionBar?.title = getString(R.string.title_pc)
    }

    private fun downloadPDF(url: String) {
        url.substring(url.lastIndexOf('/') + 1)

        DownloadingImpl(this, Handler(), object : Download.Listener {
            override fun onSuccess(url: String, pdfFile: File) {

                displayPdpFromFile(pdfFile)
            }

            override fun onFailure(e: Exception) {
            }

            override fun onProgressUpdate(progress: Int, total: Int) {
            }
        }).download(url)
    }

    private fun displayPdpFromFile(file: File) {

        // Android PDF Viewer https://github.com/barteksc/AndroidPdfViewer

//        pdfView.fromFile(file)
//            .defaultPage(0)
//            .onPageChange(this)
//            .enableAnnotationRendering(true)
//            .onLoad(this)
//            .scrollHandle(DefaultScrollHandle(this))
//            .spacing(10) // in dp
//            .onPageError(this)
//            .load()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
