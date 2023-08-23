package com.example.admissiontest.ui.auth

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admissiontest.R
import com.example.admissiontest.databinding.FragmentLoginBinding
import com.example.admissiontest.isValidEmail

class LoginFragment : Fragment() {

    interface LoginFragmentActions{
        fun onRegisterButtonClick()
        fun onLoginFieldsValidated(email: String, password: String)
    }

    private lateinit var loginFragmentActions: LoginFragmentActions
    private lateinit var binding: FragmentLoginBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginFragmentActions = try {
            context as LoginFragmentActions
        }catch(e: ClassCastException){
            throw ClassCastException("$context must implement LoginFragmentActions")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        binding.loginRegisterButton.setOnClickListener {
            loginFragmentActions.onRegisterButtonClick()
        }

        binding.loginButton.setOnClickListener {
            validateFields()
        }
        return binding.root
    }
    private fun validateFields() {
        binding.emailInput.error = ""
        binding.passwordInput.error = ""
        val email = binding.emailEdit.text.toString()

        if(!isValidEmail(email)){
            binding.emailInput.error = getString(R.string.email_not_valid)
            return
        }

        val password = binding.passwordEdit.text.toString()
        if(password.isEmpty()){
            binding.passwordInput.error = getString(R.string.password_not_empty)
            return
        }
        loginFragmentActions.onLoginFieldsValidated(email, password)
    }

}