package com.example.apipokemon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.apipokemon.databinding.FragmentSecondBinding;
import com.squareup.picasso.Picasso;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        Picasso.get().load(args.getString("ImagenPokemon")).into(binding.imageView2);
        binding.Nombre2.setText(args.getString("Nombre Pokemon"));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}