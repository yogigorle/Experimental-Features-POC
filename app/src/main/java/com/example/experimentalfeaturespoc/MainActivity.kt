package com.example.experimentalfeaturespoc

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginBehavior
import com.facebook.login.LoginResult

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception
import kotlin.math.sign


class MainActivity : AppCompatActivity() {

    private lateinit var layout: View
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private var callbackManager: CallbackManager? = null
    val Req_Code: Int = 123
    private lateinit var firebaseAuth: FirebaseAuth

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

            btnGoogleSignout.setOnClickListener {
                signoutFromGoogle()
            }

            btnFbLogin.setOnClickListener {
                signInUsingFb()
            }

            btnTabSelection.setOnClickListener {
                startActivity(Intent(this@MainActivity, TabLayoutChipsSelection::class.java))
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            callbackManager?.onActivityResult(requestCode, resultCode, data)
        } catch (e: Exception) {

        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}