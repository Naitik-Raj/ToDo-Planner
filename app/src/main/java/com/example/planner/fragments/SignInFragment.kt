package com.example.planner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.planner.R
import com.example.planner.databinding.FragmentSignInBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class SignInFragment : Fragment() {

    private lateinit var authentication: FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        registerEvents()
    }

    private fun registerEvents() {

        binding.authTextView.setOnClickListener{
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.nextBtn.setOnClickListener{
            val email = binding.emailEt.text.toString().trim()
            val pass = binding.passEt.text.toString().trim()

            if(email.isNotEmpty() && pass.isNotEmpty()){

                binding.progressBar.visibility = View.VISIBLE

                    //creating new user
                authentication.signInWithEmailAndPassword(email, pass).addOnCompleteListener(
                    OnCompleteListener{
                        if(it.isSuccessful){
                            Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
                            navController.navigate(R.id.action_signInFragment_to_homeFragment)
                        }else{
                            Toast.makeText(context, it.exception?.message, Toast.LENGTH_LONG).show()
                        }
                        binding.progressBar.visibility = View.GONE
                    })
            }else{
                Toast.makeText(context,"Empty fields not allowed here...", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initView(view: View) {
        navController = Navigation.findNavController(view)
        authentication = FirebaseAuth.getInstance()
    }
}