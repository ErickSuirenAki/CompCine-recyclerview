package com.example.filmes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class InfoActivity extends AppCompatActivity {

    private ImageView imagemInfo;
    private TextView titulo;
    private TextView ano;

    private TextView genero;
    private TextView descricao;
    private Button buttonVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonVoltar = findViewById(R.id.button_voltar);

        Intent it = getIntent();

        imagemInfo = findViewById(R.id.imagemInfo);
        titulo = findViewById(R.id.textTitulo);
        ano = findViewById(R.id.textAno);
        genero = findViewById(R.id.textGenero);
        descricao = findViewById(R.id.textDescricao);

        titulo.setText(it.getStringExtra("titulo"));
        ano.setText("Ano: "+ it.getStringExtra("ano"));
        genero.setText("Gênero: "+ it.getStringExtra("genero"));
        descricao.setText(it.getStringExtra("descricao"));
        Glide.with(this).load(it.getStringExtra("imagem")).into(imagemInfo);



        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(InfoActivity.this, RecylcerView.class) ;// nome da activity
                startActivity(back);
            }
        });
    }
}