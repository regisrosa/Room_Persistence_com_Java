package com.example.roompersistencecomjava;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Usuario implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nome")
    @NonNull
    private String nome;

    @ColumnInfo(name = "data_nascimento")
    @NonNull
    private String nascimento;


    public Usuario(int id, String nome, String nascimento) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
    }


    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getNascimento() {

        return nascimento;
    }

    public void setNascimento(String nascimento) {

        this.nascimento = nascimento;
    }
}
