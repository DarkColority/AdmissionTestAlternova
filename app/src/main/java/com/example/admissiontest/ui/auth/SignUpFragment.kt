package com.example.admissiontest.ui.auth

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admissiontest.R
import com.example.admissiontest.databinding.FragmentSignUpBinding
import com.example.admissiontest.isValidEmail

class SignUpFragment : Fragment() {

    interface SignUpFragmentActions{
        fun onSignUpFieldsValidated(email: String, password: String)
    }

    private lateinit var signUpFragmentActions: SignUpFragmentActions

    override fun onAttach(context: Context) {
        super.onAttach(context)
        signUpFragmentActions = try {
            context as SignUpFragmentActions
        }catch(e: ClassCastException){
            throw ClassCastException("$context must implement LoginFragmentActions")
        }
    }

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater)
        setupSignUpButton()
        return binding.root
    }

    private fun setupSignUpButton(){
        binding.signUpButton.setOnClickListener {
            validateFields()
        }
    }

    private fun validateFields() {
        binding.emailInput.error = ""
        binding.passwordInput.error = ""
        binding.confirmPasswordInput.error = ""
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

        val passwordConfirmation = binding.confirmPasswordEdit.text.toString()
        if(password.isEmpty()){
            binding.confirmPasswordInput.error = getString(R.string.password_not_empty)
            return
        }

        if(password != passwordConfirmation){
            binding.confirmPasswordInput.error = getString(R.string.passwords_not_match)
            return
        }

        signUpFragmentActions.onSignUpFieldsValidated(email, password)
    }

}