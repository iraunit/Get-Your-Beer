package com.example.getyourbeer

import android.content.Context
import android.media.tv.AdRequest
import android.text.Layout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContentProviderCompat.requireContext

class Popup {

    fun showPopup(context:Context,popup_layout:Int,MyView:View,location:Int){
        var inflate = LayoutInflater.from(context)
        var popupview = inflate.inflate(popup_layout, null, false)
        var builder = PopupWindow(
            popupview,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT, true
        )

        builder.setBackgroundDrawable(
            AppCompatResources.getDrawable(
                context,R.drawable.background
            )
        )
        builder.animationStyle = R.style.DialogAnimation
        builder.showAtLocation(
            MyView.findViewById(location),
            Gravity.CENTER,
            0,
            0
        )

    }
}