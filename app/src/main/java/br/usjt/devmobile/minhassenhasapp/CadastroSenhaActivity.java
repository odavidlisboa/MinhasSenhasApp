package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;


public class CadastroSenhaActivity extends AppCompatActivity {

    private static final String TAG = "CadastroSenhaActivity";
    private TextInputEditText nome;
    private TextInputEditText usuario;
    private TextInputEditText senha;
    private TextInputEditText url;
    private TextInputEditText observacao;
    private Button saveButton;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_senha);
        db = Room.databaseBuilder(this.getApplicationContext(),
                AppDatabase.class, "database-name").build();
        nome = (TextInputEditText)findViewById(R.id.cadNomeSenhaEditTextInput);
        usuario = (TextInputEditText)findViewById(R.id.cadUsuarioEditTextInput);
        senha = (TextInputEditText)findViewById(R.id.cadSenhaEditTextInput);
        url = (TextInputEditText)findViewById(R.id.cadUrlEditTextInput);
        observacao = (TextInputEditText)findViewById(R.id.cadObservacaoEditTextInput);
        saveButton = findViewById(R.id.loginButton);

        nome.addTextChangedListener(validarCamposVazios);
        usuario.addTextChangedListener(validarCamposVazios);
        senha.addTextChangedListener(validarCamposVazios);
    }

    private TextWatcher validarCamposVazios = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nomeCheck = nome.getText().toString().trim();
            String usuarioCheck = usuario.getText().toString().trim();
            String senhaCheck = senha.getText().toString().trim();

            saveButton.setEnabled(!nomeCheck.isEmpty() && !usuarioCheck.isEmpty() && !senhaCheck.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void cadastrarSenha(View view) {

            final Senha senha = new Senha(
                nome.getText().toString(),
                usuario.getText().toString(),
                this.senha.getText().toString(),
                url.getText().toString(),
                observacao.getText().toString()
                );

                new SaveSenhaAsyncTask().execute(senha);
    }


    private class SaveSenhaAsyncTask extends AsyncTask<Senha, Void, Boolean>
    {
        @Override
        protected Boolean doInBackground(Senha... senha) {
           db.senhaDao().insertAll(senha);
           return true;
        }

        @Override
        protected void onPostExecute(Boolean sucess){
            Toast.makeText(CadastroSenhaActivity.this,"Senha criada com sucesso!",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
