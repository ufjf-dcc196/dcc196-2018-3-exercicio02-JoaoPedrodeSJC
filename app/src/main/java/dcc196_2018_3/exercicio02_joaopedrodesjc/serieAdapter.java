package dcc196_2018_3.exercicio02_joaopedrodesjc;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class serieAdapter extends RecyclerView.Adapter<serieAdapter.ViewHolder>{
    private Cursor cursor;
    public serieAdapter(Cursor c){
        cursor = c;
    }

    public void setCursor(Cursor c){
        cursor = c;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View livroView = inflater.inflate(R.layout.serie_layout, parent, false);
        ViewHolder holder = new ViewHolder(livroView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int idxTitulo = cursor.getColumnIndexOrThrow(SerieContract.Serie.COLUMN_NAME_TITULO);
        int idxTemp = cursor.getColumnIndexOrThrow(SerieContract.Serie.COLUMN_NAME_TEMP);
        int idxEp = cursor.getColumnIndexOrThrow(SerieContract.Serie.COLUMN_NAME_EP);

        cursor.moveToPosition(position);

        holder.txtTitulo.setText(cursor.getString(idxTitulo));
        holder.txtTemporada.setText(String.valueOf(cursor.getInt(idxTemp)));
        holder.txtEpisodio.setText(String.valueOf(cursor.getInt(idxEp)));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitulo;
        public TextView txtTemporada;
        public TextView txtEpisodio;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txt_titulo_layout);
            txtTemporada = itemView.findViewById(R.id.txt_temp_layout);
            txtEpisodio = itemView.findViewById(R.id.txt_episode_layout);
        }
    }
}
