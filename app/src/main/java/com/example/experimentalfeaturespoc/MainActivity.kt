package com.example.experimentalfeaturespoc

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.experimentalfeaturespoc.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import java.util.jar.Manifest
import androidx.annotation.NonNull
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.experimentalfeaturespoc.databinding.Example1CalendarDayBinding
import com.example.experimentalfeaturespoc.databinding.LayoutCustomCalendarBsBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginBehavior
import com.facebook.login.LoginResult

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import java.lang.Exception
import java.time.LocalDate
import java.time.MonthDay
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.concurrent.Flow
import kotlin.math.roundToInt
import kotlin.math.sign


class MainActivity : AppCompatActivity() {

    private lateinit var layout: View
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private var callbackManager: CallbackManager? = null
    val Req_Code: Int = 123
    private lateinit var firebaseAuth: FirebaseAuth
    private val ktor = HttpClient(CIO)
    private var calBottomSheetDialog: BottomSheetDialog? = null

    //variables related to calendar config
    private var selectedDates = mutableSetOf<LocalDate>()
    private var monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM")
    private var today = LocalDate.now()


    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.i("Permission: ", "Granted")
        } else {
            Log.i("Permission: ", "Denied")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        layout = mainActivityBinding.clMain
        setContentView(mainActivityBinding.root)
        initClickListeners()

        with(mainActivityBinding) {
            btnMapPoc.setOnClickListener {
                startActivity(Intent(this@MainActivity, MapsActivity::class.java))
            }
            btnAnimationPlayground.setOnClickListener {
                startActivity(Intent(this@MainActivity, AnimationPlayground::class.java))
            }
        }

        mainActivityBinding.btnInfiniteRv.setOnClickListener {
            startActivity(Intent(this, InfiniteRvTest::class.java))
        }

        callbackManager = CallbackManager.Factory.create()

//        FirebaseApp.initializeApp(this)

        //google sign in options
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
//        firebaseAuth = FirebaseAuth.getInstance()

        val account = GoogleSignIn.getLastSignedInAccount(this)

        if (GoogleSignIn.hasPermissions(account, *gso.scopeArray)) {
            Toast.makeText(
                this,
                "Successfully Logged in with email address ${account?.email}",
                Toast.LENGTH_SHORT
            ).show()

        } else {
            signoutFromGoogle()
            // Trigger a silent login, followed by interactive if silent fails.
        }

        invokeCustomCalendarBs()

//        mainActivityBinding.etSearch.addTextChangedListener(TypingListener(){
//            Log.e("SEARCHED_TEXT",it)
//        })


    }


    private fun onClickRequestPermission(view: View) {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                layout.showSnackbar(
                    getString(R.string.permission_granted)
                ) {}
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CAMERA
            ) -> {
                layout.showSnackbar(
                    getString(R.string.permission_required),
                    actionMessage = getString(R.string.ok)
                ) {
                    requestPermissionLauncher.launch(
                        android.Manifest.permission.CAMERA
                    )
                }
            }
            else -> {
                requestPermissionLauncher.launch(
                    android.Manifest.permission.CAMERA
                )
            }
        }
    }

    private fun initClickListeners() {
        with(mainActivityBinding) {
            buttonReqPermissions.setOnClickListener {
                onClickRequestPermission(buttonReqPermissions)
            }

            btnGoogle.setOnClickListener {
                googleSignIn()
            }

            btnInvokeCalendar.setOnClickListener {
                calBottomSheetDialog?.show()
            }

            btnFbLogin.setOnClickListener {
                signInUsingFb()
            }

            btnDownloadPdf.setOnClickListener {
                downloadWithFlow("http://tekkr-ecom-test.s3.amazonaws.com/Invoice/F29OR000411.pdf")
            }

            btnTabSelection.setOnClickListener {
                startActivity(Intent(this@MainActivity, TabLayoutChipsSelection::class.java))
            }

            btnInfiniteRv.setOnClickListener {
                startActivity(Intent(this@MainActivity, InfiniteRecyclerView::class.java))
            }

            btnCustomCalendar.setOnClickListener {
                startActivity(Intent(this@MainActivity, CustomCalendar::class.java))
            }

        }
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleSignInResult(task)
            }
        }

    private fun googleSignIn() {
//        googleSignInClient.signOut()
        val signInIntent = googleSignInClient.signInIntent
        startForResult.launch(signInIntent)
//        startActivityForResult(signInIntent,Req_Code)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            account.serverAuthCode
            Toast.makeText(
                this,
                "Successfully Logged in with email address ${account?.email}",
                Toast.LENGTH_SHORT
            ).show()

        } catch (e: ApiException) {
            Toast.makeText(
                this,
                "Sorry Google Login is failed with following exception $e",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun signoutFromGoogle() {
        googleSignInClient.signOut()
            .addOnCompleteListener(this, OnCompleteListener<Void?> {
                // [START_EXCLUDE]
                Toast.makeText(
                    this,
                    "Succesfully logged out with accout ${it.result}",
                    Toast.LENGTH_SHORT
                ).show()
                // [END_EXCLUDE]
            })
    }

    private fun signInUsingFb() {
        //specify login behaviour
        with(mainActivityBinding) {
            btnFbLogin.loginBehavior = LoginBehavior.NATIVE_WITH_FALLBACK

            var email = ""
            var userName = ""

            btnFbLogin.setPermissions(
                listOf("public_profile,email")
            )

            //register result callback
            btnFbLogin.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    result?.let {
                        val accessToken = it.accessToken.token


                        try {

                            val graphRequest =
                                GraphRequest.newMeRequest(it.accessToken) { userData, _ ->
                                    val userId = userData.getString("id") ?: ""

                                    if (userData.has("email")) {
                                        email = userData.getString("email") ?: ""
                                    }
                                    if (userData.has("name"))
                                        userName = userData.getString("name") ?: ""
                                }

                            val parameters = Bundle()
                            parameters.putString("fields", "id,email,name")
                            graphRequest.parameters = parameters
                            graphRequest.executeAsync()


                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }

                }

                override fun onCancel() {
                    Toast.makeText(
                        this@MainActivity,
                        "Sorry Something Went Wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onError(error: FacebookException?) {
                    root.showSnackbar(error?.localizedMessage!!) {

                    }
                }

            })
        }
    }

    private fun downloadWithFlow(url: String) {
        //get external storage directory
        val externalStorageDir = getExternalFilesDir(null)
        val folder = File(externalStorageDir, "Invoices")
        if (!folder.exists()) {
            folder.mkdir()
        }
        val invoiceFile = File(folder, url.substringAfter("Invoice/"))
        if (!invoiceFile.exists()) {
            //download and save to storage
            lifecycleScope.launch(Dispatchers.IO) {
                ktor.downloadFile(invoiceFile, url).collect {
                    withContext(Dispatchers.Main) {
                        when (it) {
                            is DownloadStatus.Success -> {
                                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                startActivity(browserIntent)
                                Toast.makeText(
                                    this@MainActivity,
                                    "Download Successful",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }

                            is DownloadStatus.Error -> Toast.makeText(
                                this@MainActivity,
                                it.message,
                                Toast.LENGTH_SHORT
                            )
                                .show()

                            is DownloadStatus.Progress -> Toast.makeText(
                                this@MainActivity,
                                it.progress.toString(),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }

        }
    }

    suspend fun HttpClient.downloadFile(
        file: File,
        url: String,
    ): kotlinx.coroutines.flow.Flow<DownloadStatus> {
        return flow {
            val response = call {
                url(url)
                method = HttpMethod.Get
            }.response
            val byteArray = ByteArray(response.contentLength()?.toInt() ?: 0)
            var offset = 0
            do {
                val currentReadBytes =
                    response.content.readAvailable(byteArray, offset, byteArray.size)
                offset += currentReadBytes
                val progress = (offset * 100f / byteArray.size).roundToInt()
                emit(DownloadStatus.Progress(progress))

            } while (currentReadBytes > 0)
            response.close()
            if (response.status.isSuccess()) {
                file.writeBytes(byteArray)
                emit(DownloadStatus.Success)
            } else {
                emit(DownloadStatus.Error("File not downloaded.."))
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            callbackManager?.onActivityResult(requestCode, resultCode, data)
        } catch (e: Exception) {

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun invokeCustomCalendarBs() {

        val daysOfWeek = daysOfWeekFromLocale()

        val currentMonth = YearMonth.now()
        val endMonth = currentMonth.plusMonths(1)

        calBottomSheetDialog = BottomSheetDialog(this, R.style.Theme_Design_BottomSheetDialog)
        calBottomSheetDialog?.run {
            val calLayoutBinding = LayoutCustomCalendarBsBinding.inflate(layoutInflater)
            setContentView(calLayoutBinding.root)
            with(calLayoutBinding) {
                //class to hold days and handles click listener on the dates inherit from ViewContainer from  custom calendar library
                class DayViewContainer(view: View) : ViewContainer(view) {
                    //selected date will be assigned when this class is binded to dayBinder of Custom calendar lib
                    lateinit var day: CalendarDay
                    val textView = Example1CalendarDayBinding.bind(view).tvDay

                    init {
                        view.setOnClickListener {
                            if (day.owner == DayOwner.THIS_MONTH) {
                                if (selectedDates.contains(day.date)) {
                                    (1..14).forEach {
                                        selectedDates.remove(day.date.plusDays(it.toLong()))
                                    }
                                } else {
                                    (1..14).forEach {
                                        selectedDates.add(day.date.plusDays(it.toLong()))
                                    }
                                }
                                Timber.e(selectedDates.toString())

                                customCalendar.notifyDayChanged(day)

                            }
                        }
                    }
                }
                customCalendar.apply {
                    setup(currentMonth, endMonth, daysOfWeek.first())
                    smoothScrollToMonth(currentMonth)
                    dayBinder = object : DayBinder<DayViewContainer> {
                        // Called only when a new container is needed.
                        override fun create(view: View) = DayViewContainer(view)

                        // Called every time we need to reuse a container.
                        override fun bind(container: DayViewContainer, day: CalendarDay) {
                            container.day = day
                            val tv = container.textView
                            tv.text = day.date.dayOfMonth.toString()
                            (1..14).forEach {
                                if (day.owner == DayOwner.THIS_MONTH) {
                                    when {
                                        selectedDates.contains(day.date) -> {
                                            tv.setTextColorRes(R.color.selected_date_text_color)
                                            tv.setBackgroundResource(R.drawable.example_1_selected_bg)
                                        }
                                        today == day.date -> {
                                            tv.setTextColorRes(R.color.white)
                                            tv.setBackgroundResource(R.drawable.example_1_today_bg)
                                        }
                                        else -> {
                                            tv.setTextColorRes(R.color.white)
                                            tv.background = null
                                        }
                                    }
                                } else {
                                    tv.setTextColorRes(R.color.light_white)
                                    tv.background = null
                                }
                            }

                        }
                    }
                    monthScrollListener =
                        {

                        }


                }


            }

        }
    }

    companion object {
        var calendarType = ""
    }

}