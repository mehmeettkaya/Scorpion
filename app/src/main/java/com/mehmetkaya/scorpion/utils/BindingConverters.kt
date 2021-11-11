package com.mehmetkaya.scorpion.utils

import android.view.View
import androidx.databinding.BindingConversion

@get:BindingConversion
val Boolean.toVisibility
    get() = if (this) View.VISIBLE else View.GONE
