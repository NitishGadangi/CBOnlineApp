package com.codingblocks.cbonlineapp.auth.onboarding

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codingblocks.cbonlineapp.BuildConfig
import com.codingblocks.cbonlineapp.R
import com.codingblocks.cbonlineapp.util.Components
import com.codingblocks.cbonlineapp.util.extensions.openChrome
import com.codingblocks.cbonlineapp.util.extensions.replaceFragmentSafely
import kotlinx.android.synthetic.main.fragment_login_home.*


class LoginHomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):
        View? = inflater.inflate(R.layout.fragment_login_home, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setfirstSpan()
        setSecondSpan()

        mobileBtn.setOnClickListener {
            replaceFragmentSafely(SignInFragment(), "SignIn", containerViewId = R.id.loginContainer, enterAnimation = R.animator.slide_in_right, exitAnimation = R.animator.slide_out_left)
        }

        gmailBtn.setOnClickListener {
            requireContext().openChrome(
                "${BuildConfig.OAUTH_URL}?redirect_uri=${BuildConfig.REDIRECT_URI}&response_type=code&client_id=${BuildConfig.CLIENT_ID}"
            )
        }

        fbBtn.setOnClickListener {
            requireContext().openChrome(
                "${BuildConfig.OAUTH_URL}?redirect_uri=${BuildConfig.REDIRECT_URI}&response_type=code&client_id=${BuildConfig.CLIENT_ID}"
            )
        }


    }

    private fun setSecondSpan() {
        val policySpan = SpannableString("By loggin in you agree to Coding Blocks’s\n" +
            "Privacy Policy & Terms of Service")
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
//                replaceFragmentSafely(SignInFragment(), containerViewId = R.id.loginContainer, enterAnimation = R.animator.slide_in_right, exitAnimation = R.animator.slide_out_left)
            }

            override fun updateDrawState(ds: TextPaint) {

            }
        }
        policySpan.setSpan(clickableSpan, 41, policySpan.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        policyTv.apply {
            text = policySpan
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }

    private fun setfirstSpan() {
        val wordToSpan = SpannableString("New here? Create an account")
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                replaceFragmentSafely(SignInFragment.newInstance("NEW"), containerViewId = R.id.loginContainer, enterAnimation = R.animator.slide_in_right, exitAnimation = R.animator.slide_out_left)
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.color = ds.linkColor
                ds.isUnderlineText = false // set to false to remove underline
            }
        }
        wordToSpan.setSpan(clickableSpan, 9, wordToSpan.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        createAccTv.apply {
            text = wordToSpan
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }

}