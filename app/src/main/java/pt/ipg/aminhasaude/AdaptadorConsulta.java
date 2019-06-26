package pt.ipg.aminhasaude;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorConsulta extends RecyclerView.Adapter<AdaptadorConsulta.ViewHolderConsulta> {
    private Cursor cursor;
    private Context context;

    public AdaptadorConsulta (Context context){this.context = context;}

    public void setCursor(Cursor cursor){
        if(this.cursor != cursor){
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public AdaptadorConsulta.ViewHolderConsulta onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemConsulta= LayoutInflater.from(context).inflate(R.layout.item_consulta, parent, false);

        return new AdaptadorConsulta.ViewHolderConsulta(itemConsulta);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorConsulta.ViewHolderConsulta holder, int position) {
        cursor.moveToPosition(position);
        Consulta consulta = Consulta.fromCursor(cursor);
        holder.setConsulta(consulta);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;

        return cursor.getCount();
    }

    public Consulta getConsultaSelecionado(){
        if(ViewHolderConsultaSelecionado == null) return null;

        return ViewHolderConsultaSelecionado.consulta;
    }

    private static  ViewHolderConsulta ViewHolderConsultaSelecionado = null;

    public class ViewHolderConsulta extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textViewDia;
        private TextView textViewHora;
        private TextView textViewLocal;
        private TextView textViewMotivo;
        private TextView textViewMedico;

        private Consulta consulta;

        public ViewHolderConsulta(@NonNull View itemView) {
            super(itemView);

            textViewDia = (TextView) itemView.findViewById(R.id.textViewDia);
            textViewHora = (TextView)itemView.findViewById(R.id.textViewHora);
            textViewLocal = (TextView)itemView.findViewById(R.id.textViewLocal);
            textViewMotivo = (TextView)itemView.findViewById(R.id.textViewMotivo);
            textViewMedico = (TextView)itemView.findViewById(R.id.textViewMédico);

            itemView.setOnClickListener(this);
        }

        public void setConsulta(Consulta consulta){
            this.consulta = consulta;

            textViewDia.setText(consulta.getdia());
            textViewHora.setText(consulta.getHora());
            textViewLocal.setText(consulta.getlocal());
            textViewMotivo.setText(consulta.getmotivo());
            textViewMedico.setText(consulta.getMedico());
        }
        private void desSeleciona() {
            itemView.setBackgroundResource(android.R.color.white);
        }

        private void seleciona() {
            itemView.setBackgroundResource(android.R.color.holo_blue_light);
        }

        @Override
        public void onClick(View v) {
            if(ViewHolderConsultaSelecionado != null){
                ViewHolderConsultaSelecionado.desSeleciona();
            }

            ViewHolderConsultaSelecionado = this;

            ((VerConsultas) context).atualizaOpcoesMenu();

            seleciona();
        }
        }
    }




