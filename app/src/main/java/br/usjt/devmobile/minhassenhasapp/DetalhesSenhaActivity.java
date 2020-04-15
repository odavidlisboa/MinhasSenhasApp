package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetalhesSenhaActivity extends AppCompatActivity {

    private Senha senha;
    private TextView textViewSenhaNome;
    private TextView textViewSenhaUsuario;
    private TextView textViewSenhaSenha;
    private TextView textViewSenhaUrl;
    private TextView textViewSenhaObservacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_senha);
        Intent intent = getIntent();
        senha = (Senha)intent.getSerializableExtra("senha");
        textViewSenhaNome = findViewById(R.id.textViewNomeValue);
        textViewSenhaNome.setText(senha.getNome());
        textViewSenhaUsuario = findViewById(R.id.textViewUsuarioValue);
        textViewSenhaUsuario.setText(senha.getUsuario());
        textViewSenhaSenha = findViewById(R.id.textViewSenhaValue);
        textViewSenhaSenha.setText(senha.getSenha());
        textViewSenhaUrl = findViewById(R.id.textViewUrlValue);
        textViewSenhaUrl.setText(senha.getUrl());
        textViewSenhaObservacao = findViewById(R.id.textViewObservacaoValue);
        textViewSenhaObservacao.setText(senha.getObservacao());
    }
}
