package com.example.apipokemon;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import com.example.apipokemon.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {


    private FragmentFirstBinding binding;
    private ArrayList<Pokemon> listapok = new ArrayList<>();
    private PokemonAdapter pokemonAdapter;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        pokemonAdapter = new PokemonAdapter(
                getContext(),
                R.layout.lv_pokemon_row,
                listapok
        );


        binding.listview1.setAdapter(pokemonAdapter);
        refresh();


        binding.listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pokemon pokimg=pokemonAdapter.getItem(position);

                Bundle args=new Bundle();
                args.putString("ImagenPokemon",pokimg.getImage());
                args.putString("Nombre Pokemon", pokimg.getName());

                Navigation.findNavController(view).navigate(R.id.action_FirstFragment_to_SecondFragment,args);
            }
        });


    }

    private void refresh() {

        Toast.makeText(getContext(), "Refreshing..", Toast.LENGTH_LONG).show();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String tipo = preferences.getString("tipo", "");

        if(tipo.equals("")){
            tipo.getClass();
        }

        executor.execute(() -> {
            PokemonApi api = new PokemonApi();
            ArrayList<Pokemon> pokemons = api.getPokemons();

            handler.post(() -> {
                listapok.clear(); // Limpia la lista actual

                // Agrega los pokemons obtenidos de la API a la lista
                listapok.addAll(pokemons);

                // Notifica al adaptador que los datos han cambiado
                pokemonAdapter.notifyDataSetChanged();
            });
        });
 

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}