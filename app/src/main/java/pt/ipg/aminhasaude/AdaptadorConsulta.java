package pt.ipg.aminhasaude;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public class ViewHolderConsulta extends RecyclerView.ViewHolder {
        private Consulta consulta;
        public ViewHolderConsulta(@NonNull View itemView) {
            super(itemView);
        }

        public void setConsulta(Consulta consulta){this.consulta = consulta;}
    }



}
