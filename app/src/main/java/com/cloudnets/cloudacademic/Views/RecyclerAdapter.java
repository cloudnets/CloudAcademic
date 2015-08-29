package com.cloudnets.cloudacademic.Views;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Creado por Deimer Villa on 29/08/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.EstudianteViewHolder> {

    public static class EstudianteViewHolder extends RecyclerView.ViewHolder{
        CardView cardEst;
        TextView nombreEstudiante;
        ImageView foto;
        EstudianteViewHolder(View itemView){
            super(itemView);
            cardEst = (CardView)itemView.findViewById(R.id.c);
        }
    }

}
