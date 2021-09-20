package com.binhjdev.taskie.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.binhjdev.taskie.R
import com.binhjdev.taskie.TaskieApplication
import com.binhjdev.taskie.data.model.request.LoginUserDataRequest
import com.binhjdev.taskie.ui.main.TaskManagerActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity(), LoginView {

    private val presenter: LoginPresenter by viewModel<LoginPresenterImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (TaskieApplication.getToken().isNotBlank()) {
            val intent = Intent(applicationContext, TaskManagerActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        presenter.setLoginView(this)

        btnLogin.setThrottleClickListener {
            if (checkValidate(editTextLoginEmail.text.toString(), editTextLoginPassword.text.toString())) {
                presenter.sendLoginUserData(
                        LoginUserDataRequest(
                                editTextLoginEmail.text.toString(),
                                editTextLoginPassword.text.toString()
                        )
                )
            }
        }
    }

    private fun checkValidate(email: String, password: String): Boolean {
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Please input field!", Toast.LENGTH_SHORT).show()
            return false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please input correct the email format!", Toast.LENGTH_SHORT)
                    .show()
            return false
        }
        return true
    }

    override fun loginSuccessThenGoMainScreen(token: String) {
        TaskieApplication.saveToken(token)
        Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show()
        val intent = Intent(applicationContext, TaskManagerActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    private fun View.setThrottleClickListener(throttle: Long = 200L, onClicked: (View?) -> Unit) {
        this.setOnClickListener { v ->
            v.isClickable = false
            v.postDelayed({
                v.isClickable = true
            }, throttle)
            onClicked(v)
        }
    }
}
