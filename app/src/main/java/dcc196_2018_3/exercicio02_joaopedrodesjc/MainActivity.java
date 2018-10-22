package dcc196_2018_3.exercicio02_joaopedrodesjc;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnAdicionar,btnRemover;
    private EditText txtTitulo,txtTemporada,txtEpisodio;
    private RecyclerView rclSeries;
    private SerieDbHelper dbHelper;
    private serieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new SerieDbHelper(getApplicationContext());

        rclSeries = (RecyclerView) findViewById(R.id.rcl_series);
        rclSeries.setLayoutManager(new LinearLayoutManager(this));
        
        adapter = new serieAdapter(getCursorSeriePos1950());
        rclSeries.setAdapter(adapter);

        txtTitulo = (EditText) findViewById(R.id.txt_titulo);
        txtTemporada = (EditText) findViewById(R.id.txt_temp);
        txtEpisodio = (EditText) findViewById(R.id.txt_ep);

        btnAdicionar = (Button) findViewById(R.id.btn_adicionar);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtTitulo.getText()!=null && txtEpisodio.getText()!=null && txtEpisodio.getText()!=null) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues valores = new ContentValues();
                    valores.put(SerieContract.Serie.COLUMN_NAME_TITULO, String.valueOf(txtTitulo.getText()));
                    valores.put(SerieContract.Serie.COLUMN_NAME_TEMP, Integer.parseInt(String.valueOf(txtTemporada.getText())));
                    valores.put(SerieContract.Serie.COLUMN_NAME_EP, Integer.parseInt(String.valueOf(txtEpisodio.getText())));
                    long id = db.insert(SerieContract.Serie.TABLE_NAME, null, valores);
                    Log.i("DBINFO", "registro criado com id: " + id);
                    adapter.setCursor(getCursorSeriePos1950());
                }
            }
        });
    }
    private Cursor getCursorSeriePos1950() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                SerieContract.Serie.COLUMN_NAME_TITULO,
                SerieContract.Serie.COLUMN_NAME_TEMP,
                SerieContract.Serie.COLUMN_NAME_EP
        };
        String restricoes = SerieContract.Serie.COLUMN_NAME_EP + " > ?";
        String[] params = {"1950"};
        String sort = SerieContract.Serie.COLUMN_NAME_EP+ " DESC";
        return db.query(SerieContract.Serie.TABLE_NAME, visao,restricoes,params,null,null, sort);
    }
}
