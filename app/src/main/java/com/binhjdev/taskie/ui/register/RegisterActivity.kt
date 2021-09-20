package com.binhjdev.taskie.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.binhjdev.taskie.R
import com.binhjdev.taskie.TaskieApplication
import com.binhjdev.taskie.data.model.request.RegisterUserDataRequest
import com.binhjdev.taskie.ui.login.LoginActivity
import com.binhjdev.taskie.ui.main.TaskManagerActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.viewmodel.ext.android.viewModel


class RegisterActivity : AppCompatActivity(), RegistersView {

    private val presenter: RegisterPresenter by viewModel<RegisterPresenterImpl>()

    override fun onStart() {
        super.onStart()
        if (TaskieApplication.getToken().isNotBlank()) {
            val intent = Intent(applicationContext, TaskManagerActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter.setViews(this)

        btnRegister.setThrottleClickListener {
            if (checkValidate(
                    edtRegisterEmail.text.toString(),
                    edtRegisterUserName.text.toString(),
                    edtRegisterPassword.text.toString()
                )
            ) {
                presenter.sendRegisterUserData(
                    RegisterUserDataRequest(
                        edtRegisterEmail.text.toString(),
                        edtRegisterUserName.text.toString(),
                        edtRegisterPassword.text.toString()
                    )
                )
            }
        }

        textViewAlreadyAccount.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }

    private fun checkValidate(email: String, userName: String, password: String): Boolean {
        if (email.isBlank() || userName.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Please input field!", Toast.LENGTH_SHORT).show()
            return false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please input correct the email format!", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    override fun registerSuccessThenGoToLoginScreen(message: String) {
        Toast.makeText(this, "Register User $message", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
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