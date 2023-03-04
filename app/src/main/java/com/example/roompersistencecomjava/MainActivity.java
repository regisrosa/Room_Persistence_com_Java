package com.example.roompersistencecomjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText nome;
    private EditText nascimento;
    private Button botao;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.nome);
        nascimento = findViewById(R.id.nascimento);

        db = Room.databaseBuilder(getApplicationContext(), Database.class, "MeuDB").build();

    }

    public void salvar(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String txtNome = nome.getText().toString();
                String txtNascimento = nascimento.getText().toString();

                //o id começa com 0 e depois o banco autoincrementa automaticamente
                Usuario usuario = new Usuario(0, txtNome, txtNascimento);
                UsuarioDao usuarioDao = db.usuarioDao();
                usuarioDao.insert(usuario);

            }
        }).start();
        Toast.makeText(this, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show();
        nome.setText("");
        nascimento.setText("");
    }

    public void verListaNomes(View view){

        Intent intent = new Intent(this, ListaNomesActivity.class);
        startActivity(intent);

    }
}