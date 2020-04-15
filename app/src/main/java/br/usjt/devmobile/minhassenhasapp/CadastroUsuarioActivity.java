package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.hawk.Hawk;
import com.rishabhharit.roundedimageview.RoundedImageView;

import java.util.regex.Pattern;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private static final String TAG = "CadastroUsuarioActivity";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    private TextInputEditText usuario;
    private TextInputEditText senha;
    private TextInputEditText pergunta;
    private TextInputEditText resposta;
    private Button loginButton;
    private RoundedImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        usuario = (TextInputEditText)findViewById(R.id.usuarioEditTextInput);
        senha = (TextInputEditText)findViewById(R.id.senhaEditTextInput);
        pergunta = (TextInputEditText)findViewById(R.id.perguntaEditTextInput);
        resposta = (TextInputEditText)findViewById(R.id.respostaEditTextInput);
        loginButton = findViewById(R.id.loginButton);
        imagem = findViewById(R.id.userImage);
        Hawk.init(this).build();

        usuario.addTextChangedListener(validarCamposVazios);
        senha.addTextChangedListener(validarCamposVazios);
        pergunta.addTextChangedListener(validarCamposVazios);
        resposta.addTextChangedListener(validarCamposVazios);
    }

    private TextWatcher validarCamposVazios = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usuarioCheck = usuario.getText().toString().trim();
            String senhaCheck = senha.getText().toString().trim();
            String perguntaCheck = pergunta.getText().toString().trim();
            String respostaCheck = resposta.getText().toString().trim();

            loginButton.setEnabled(!usuarioCheck.isEmpty() && !senhaCheck.isEmpty() && !perguntaCheck.isEmpty() && !respostaCheck.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void cadastrarUsuario(View view){
        // TODO fazer o cadastro
        Log.d(TAG,"Clicou no fazer cadastro!");

        String passString = senha.getText().toString().trim();

        if (passString.isEmpty()) {
            senha.setError("Não pode ser vazio");
        } else if (!PASSWORD_PATTERN.matcher(passString).matches()) {
            senha.setError("Senha fraca");
        } else {
            Hawk.put("usuario", usuario.getText().toString());
            Hawk.put("senha", senha.getText().toString());
            Hawk.put("pergunta", pergunta.getText().toString());
            Hawk.put("resposta", resposta.getText().toString());

            Toast.makeText(this,"Cadastro Realizado com Sucesso",Toast.LENGTH_LONG).show();

            finish();
        }

    }


    public void capturarImagem(View view) {

        ImagePicker.Companion.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            Uri fileUri = data.getData();
            imagem.setImageURI(fileUri);

            Hawk.put("imagem",fileUri.getPath());


        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, "Erro na captura da imagem", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Captura de imagem cancelada", Toast.LENGTH_SHORT).show();
        }


    }

}
