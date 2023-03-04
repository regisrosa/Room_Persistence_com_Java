package com.example.roompersistencecomjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaNomesActivity extends AppCompatActivity {

    private ListView listView;
    private Database db;

    UsuarioDao usuarioDao;
    List<Usuario> usuarios;

    List<String> dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_nomes);

        listView = findViewById(R.id.listView);


        //essa lista é para transferir os dados da lista Usuario para cá, pois o adapter não aceita objetos
        List<String> dados = new ArrayList<>();

        //deve ser criada uma nova thread senão o sistema crasha
        new Thread(new Runnable() {
            @Override
            public void run() {
                db = Room.databaseBuilder(getApplicationContext(), Database.class, "MeuDB").build();
                usuarioDao = db.usuarioDao();
                usuarios = usuarioDao.getAll();

                for(Usuario usuario : usuarios){
                    String nome = usuario.getNome();
                    String nasc = usuario.getNascimento();
                    dados.add("Nome: " + nome + "\nNascimento: " + nasc );
                }

            }
        }).start();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dados);
        listView.setAdapter(adapter);

    }


}