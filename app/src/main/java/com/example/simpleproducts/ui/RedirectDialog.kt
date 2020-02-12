package com.example.simpleproducts.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.simpleproducts.R

class RedirectDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(getString(R.string.ask))
            .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(getString(R.string.atb_url))
                startActivity(openURL)
            }
            .setNegativeButton(getString(R.string.cansel)) { dialog, which ->  }
        return builder.create()
    }
}