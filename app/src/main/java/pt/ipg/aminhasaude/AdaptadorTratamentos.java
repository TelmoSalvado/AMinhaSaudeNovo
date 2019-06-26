package pt.ipg.aminhasaude;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorTratamentos extends RecyclerView.Adapter<AdaptadorTratamentos.ViewHolderTratamento> {
    private Cursor cursor;
    private Context context;

    public AdaptadorTratamentos (Context context){this.context = context;}

    public void setCursor(Cursor cursor){
        if(this.cursor != cursor){
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolderTratamento onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemTratamento = LayoutInflater.from(context).inflate(R.layout.item_tratamento, parent, false);

        return new ViewHolderTratamento(itemTratamento);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTratamento holder, int position) {
            cursor.moveToPosition(position);
            Tratamento tratamento = Tratamento.fromCursor(cursor);
            holder.setTratamento(tratamento);
    }

    @Override
    public int getItemCount() {
       if (cursor == null) return 0;

       return cursor.getCount();
    }

    public Tratamento getTratamentoSelecionado(){
        if(ViewHolderTratamentoSelecionado == null) return null;

        return ViewHolderTratamentoSelecionado.tratamento;
    }

    private static ViewHolderTratamento ViewHolderTratamentoSelecionado = null;

    /**
     *
     */
    public class ViewHolderTratamento extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textViewMedicamento;
        private TextView textViewHora;
        private TextView textViewHoraATomar;
        private TextView textViewDias;
        private TextView textViewDoenca;

        private Tratamento tratamento;

        public ViewHolderTratamento(@NonNull View itemView){
            super(itemView);
            textViewMedicamento = (TextView) itemView.findViewById(R.id.textViewMedicamento);
            textViewHora = (TextView)itemView.findViewById(R.id.textViewHora);
            textViewHoraATomar = (TextView)itemView.findViewById(R.id.textViewHoraTomar);
            textViewDias = (TextView)itemView.findViewById(R.id.textViewDias);
            textViewDoenca = (TextView)itemView.findViewById(R.id.textViewDoenca);

            itemView.setOnClickListener(this);
        }

        public void setTratamento(Tratamento tratamento){
            this.tratamento = tratamento;

            textViewMedicamento.setText(tratamento.getMedicamento());
            textViewHora.setText(tratamento.getHora());
            textViewHoraATomar.setText(tratamento.getHoraATomar());
            textViewDias.setText(String.valueOf(tratamento.getdias()));
            textViewDoenca.setText(tratamento.getDoenca());
        }
        private void desSeleciona() {
            itemView.setBackgroundResource(android.R.color.white);
        }

        private void seleciona() {
            itemView.setBackgroundResource(android.R.color.holo_blue_light);
        }

        @Override
        public void onClick(View v) {
            if(ViewHolderTratamentoSelecionado != null){
                ViewHolderTratamentoSelecionado.desSeleciona();
            }

            ViewHolderTratamentoSelecionado = this;

            ((VerTratamentos) context).atualizaOpcoesMenu();

            seleciona();
        }
    }
}

