package com.andrewvychev.railwaytickets.ui.help

import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import com.andrewvychev.railwaytickets.R
import android.content.Intent
import android.net.Uri


/**
 * Created by Andrew on 12/21/17.
 */
class HelpFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_help)

        val faq1 = findPreference("faq1") as Preference
        val faq2 = findPreference("faq2") as Preference
        val faq3 = findPreference("faq3") as Preference
        val faq4 = findPreference("faq4") as Preference

        faq1.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            openUrl("https://rw-web.herokuapp.com/return.html")
            return@OnPreferenceClickListener true
        }
        faq2.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            openUrl("https://rw-web.herokuapp.com")
            return@OnPreferenceClickListener true
        }
        faq3.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            openUrl("https://rw-web.herokuapp.com")
            return@OnPreferenceClickListener true
        }
        faq4.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            openUrl("https://rw-web.herokuapp.com")
            return@OnPreferenceClickListener true
        }
    }

    private fun openUrl(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}