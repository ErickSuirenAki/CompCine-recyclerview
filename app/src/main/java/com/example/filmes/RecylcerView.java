package com.example.filmes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RecylcerView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Filme> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recylcer_view);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        list = new ArrayList<>();
        list = FilmesData.getFilmes();

        Adapter adapter = new Adapter(list);

        recyclerView = findViewById(R.id.rc);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Filme filme = list.get(position);
                Intent intent = new Intent(RecylcerView.this, InfoActivity.class);
                intent.putExtra("titulo", filme.getTitulo());
                intent.putExtra("genero", filme.getGenero());
                intent.putExtra("ano", String.valueOf(filme.getAno()));
                intent.putExtra("descricao", filme.getDescricao());
                intent.putExtra("imagem", filme.getImagem());
                startActivity(intent);

            }
        });

        adapter.setOnItemLongClickListener(position -> {
            String titulo = list.get(position).getTitulo();

            AlertDialog.Builder janela = new AlertDialog.Builder(RecylcerView.this);
            janela.setTitle("CineComp");
            janela.setIcon(R.drawable.popcorn);

            janela.setMessage("Você tem certeza que deseja excluir o filme "+titulo + "?");
            janela.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    list.remove(position);
                    adapter.notifyItemRemoved(position);
                    Toast.makeText(RecylcerView.this, titulo + " foi removido da lista.", Toast.LENGTH_SHORT).show();

                }
            });
            janela.setNegativeButton("Não", null);
            janela.show();


        });
    }
}
