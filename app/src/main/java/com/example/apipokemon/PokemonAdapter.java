package com.example.apipokemon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class PokemonAdapter extends ArrayAdapter<Pokemon> {

    public PokemonAdapter(@NonNull Context context, int resource, List<Pokemon> pokemons) {
        super(context, resource,pokemons);


    }
    public View getView(int position, View convertView, ViewGroup parent) {

        Pokemon pok1 = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_pokemon_row, parent, false);
        }

        TextView Titulo  = convertView.findViewById(R.id.Nombre);
        TextView AlturaPokemon = convertView.findViewById(R.id.altura2);
        TextView AnchoPokemon = convertView.findViewById(R.id.Ancho);
        Titulo.setText(pok1.getName());
        AlturaPokemon.setText("Altura: "+ pok1.getHeight());
        AnchoPokemon.setText("El ancho:" + pok1.getWeight());



        return convertView;
    }
}





