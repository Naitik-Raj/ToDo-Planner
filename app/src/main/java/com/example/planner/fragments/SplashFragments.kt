package com.example.planner.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.planner.R
import com.example.planner.databinding.FragmentsSplashBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

class SplashFragments : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var binding: FragmentsSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentsSplashBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)

        binding.progressBar.visibility = View.VISIBLE

//        binding.progressBar.visibility = View.GONE
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            //user not signed in
            if(auth.currentUser == null){
                binding.progressBar.visibility = View.GONE
                navController.navigate(R.id.action_splashFragments_to_signInFragment)
            }
            //user signed in
            else{
                binding.progressBar.visibility = View.GONE
                navController.navigate(R.id.action_splashFragments_to_homeFragment)
            }
        },2000)
    }
}