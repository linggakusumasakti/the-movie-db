package com.lingga.themoviedb.utils.ui

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.DlgSuccessCreateAccountBinding


class CustomDialog  {
    private var dlg: Dialog? = null

    private fun isShowing(): Boolean = dlg?.isShowing ?: false

    fun dismiss() {
        dlg?.dismiss()
        dlg = null
    }

    private fun showDialog(
        context: Context,
        view: View,
        dismissListener: DialogInterface.OnDismissListener
    ) {
        if (isShowing()) return
        dlg = Dialog(context, R.style.AlertDialogTheme)
        dlg?.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCanceledOnTouchOutside(false)
            setOnDismissListener(dismissListener)
            setContentView(view)
            show()
        }
    }

    fun showSuccessCreateAccount(
        context: Context,
        positiveAction: View.OnClickListener,
        dismissListener: DialogInterface.OnDismissListener = DialogInterface.OnDismissListener { }
    ) {
        val binding: DlgSuccessCreateAccountBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.dlg_success_create_account,
                null,
                false
            )
        binding.buttonNext.setOnClickListener(positiveAction)
        showDialog(context, binding.root, dismissListener)
    }

}