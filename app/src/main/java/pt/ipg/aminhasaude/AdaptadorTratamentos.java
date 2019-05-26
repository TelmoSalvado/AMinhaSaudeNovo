package pt.ipg.aminhasaude;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

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

    public class ViewHolderTratamento extends RecyclerView.ViewHolder {
        private Tratamento tratamento;
        public ViewHolderTratamento(@NonNull View itemView) {
            super(itemView);
        }

        public void setTratamento(Tratamento tratamento){this.tratamento = tratamento;}
    }
}
