package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.hawk.Hawk;
import com.rishabhharit.roundedimageview.RoundedImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextInputEditText usuario;
    private TextInputEditText senha;
    private RoundedImageView imagemMain;
    private LinearLayout layoutImagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Hawk.init(this).build();

        usuario = findViewById(R.id.emailEditTextInput);
        senha = findViewById(R.id.passwordEditTextInput);
        imagemMain = findViewById(R.id.userImageMain);
        layoutImagem = findViewById(R.id.layoutImagemMain);

        colocaImagem();

    }

    private void colocaImagem(){
        if(Hawk.contains("imagem")){
            String path = Hawk.get("imagem");
            imagemMain.setImageURI(Uri.fromFile(new File(path)));
            layoutImagem.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        colocaImagem();
    }

    public void fazerLogin(View view){



        if(usuario.getText().toString().equals(Hawk.get("usuario")) &&
            senha.getText().toString().equals(Hawk.get("senha"))){

            Intent intent = new Intent(this, ListasSenhasActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Usuário ou senha incorretos!",Toast.LENGTH_SHORT).show();
        }



    }

    public void novoCadastro(View view){
            Intent intent = new Intent(this, CadastroUsuarioActivity.class);
            startActivity(intent);
    }
}
