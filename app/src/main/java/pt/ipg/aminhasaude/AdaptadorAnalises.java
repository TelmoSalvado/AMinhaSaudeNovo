package pt.ipg.aminhasaude;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorAnalises extends RecyclerView.Adapter<AdaptadorAnalises.ViewHolderAnalises> {

    private Cursor cursor;
    private Context context;

    public AdaptadorAnalises (Context context){this.context = context;}

    public void setCursor(Cursor cursor){
        if(this.cursor != cursor){
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }
    @NonNull
    @Override
    public ViewHolderAnalises onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item_analises= LayoutInflater.from(context).inflate(R.layout.item_analises, parent, false);

        return new AdaptadorAnalises.ViewHolderAnalises(item_analises);
    }



    @Override
    public void onBindViewHolder(@NonNull AdaptadorAnalises.ViewHolderAnalises holder, int position) {
        cursor.moveToPosition(position);
        Analise analises = Analise.fromCursor(cursor);
        holder.setAnalise(analises);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;

        return cursor.getCount();
    }

    public Analise getAnaliseSelecionado(){
        if(ViewHolderAnalisesSelecionado == null) return null;

        return ViewHolderAnalisesSelecionado.analise;
    }

    private static AdaptadorAnalises.ViewHolderAnalises ViewHolderAnalisesSelecionado = null;


    public class ViewHolderAnalises extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView textViewDia;
        private TextView textViewDiabetes;
        private TextView textViewColestrol;
        private TextView textViewCreatina;
        private TextView textViewAcidoUrico;
        private TextView textViewUreia;

        private Analise analise;
        public ViewHolderAnalises(@NonNull View itemView) {
            super(itemView);

            textViewDia = (TextView) itemView.findViewById(R.id.textViewDia);
            textViewDiabetes = (TextView)itemView.findViewById(R.id.textViewDiabetes);
            textViewColestrol = (TextView)itemView.findViewById(R.id.textViewColestrol);
            textViewCreatina = (TextView)itemView.findViewById(R.id.textViewCreatina);
            textViewAcidoUrico = (TextView)itemView.findViewById(R.id.textViewAcidoUrico);
            textViewUreia = (TextView)itemView.findViewById(R.id.textViewUreia);

            itemView.setOnClickListener(this);
        }
        public void setAnalise(Analise analises){
            this.analise = analises;

            textViewDia.setText(analises.getDia());
            textViewDiabetes.setText(String.valueOf(analises.getdiabetes()));
            textViewColestrol.setText(String.valueOf(analises.getColestrol()));
            textViewCreatina.setText(String.valueOf(analises.getCreatina()));
            textViewAcidoUrico.setText(String.valueOf(analises.getAcidoUrico()));
            textViewUreia.setText(String.valueOf(analises.getUreia()));
        }

        private void desSeleciona() {
            itemView.setBackgroundResource(android.R.color.white);
        }

        private void seleciona() {
            itemView.setBackgroundResource(android.R.color.holo_blue_light);
        }
        @Override
        public void onClick(View v) {
            if(ViewHolderAnalisesSelecionado != null){
                ViewHolderAnalisesSelecionado.desSeleciona();
            }

            ViewHolderAnalisesSelecionado = this;

            ((VerAnalises) context).atualizaOpcoesMenu();

            seleciona();
        }
        }
    }

