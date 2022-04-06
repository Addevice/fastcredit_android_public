package com.app.fastbank.ui.userdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.app.fastbank.android.R
import com.app.fastbank.ui.userdetail.common.ViewModelFactory
import com.app.fastbank.ui.userdetail.locator.DefaultServiceLocator


class HomeActivity : AppCompatActivity() {

    private val detailViewModel: UserDetailViewModel by viewModels {
        ViewModelFactory(DefaultServiceLocator.getInstance(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        detailViewModel.showErrorToastEvent.observe(this) { event ->
            if (!event.hasBeenHandled) {
                Toast.makeText(this@HomeActivity, R.string.an_error_occurred, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        detailViewModel.loadUser()
    }
}