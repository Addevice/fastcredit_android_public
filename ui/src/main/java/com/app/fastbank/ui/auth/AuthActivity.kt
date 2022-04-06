package com.app.fastbank.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.fastbank.ui.auth.locator.DefaultServiceLocator
import com.app.fastbank.android.R
import com.app.fastbank.android.databinding.ActivityAuthBinding
import com.app.fastbank.ui.auth.common.ViewModelFactory
import com.app.fastbank.ui.userdetail.HomeActivity

class AuthActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModels {
        ViewModelFactory(DefaultServiceLocator.getInstance(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            ActivityAuthBinding.inflate(layoutInflater).apply {
                viewModel = this@AuthActivity.viewModel
                lifecycleOwner = this@AuthActivity
            }.root
        )

        viewModel.success.observe(this) {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        viewModel.showErrorToastEvent.observe(this) { event ->
            if (!event.hasBeenHandled) {
                Toast.makeText(this@AuthActivity, R.string.an_error_occurred, Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}