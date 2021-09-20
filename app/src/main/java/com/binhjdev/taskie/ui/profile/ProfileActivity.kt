package com.binhjdev.taskie.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.binhjdev.taskie.R
import com.binhjdev.taskie.TaskieApplication
import com.binhjdev.taskie.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_profile.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileActivity : AppCompatActivity(), ProfileView {

    private val presenter: ProfilePresenter by viewModel<ProfilePresenterImpl>()

    override fun onStart() {
        super.onStart()
        presenter.getData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initUI()

        presenter.setView(this)
    }

    private fun initUI() {
        btnLogout.setThrottleClickListener {
            TaskieApplication.saveToken("")
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    override fun showProfile(email: String, name: String) {
        tvUserName.text = email
        tvEmail.text = name
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
