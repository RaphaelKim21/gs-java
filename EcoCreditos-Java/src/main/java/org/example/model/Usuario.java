package org.example.model;

import org.example.exceptions.UsuarioTipoInvalido;

import java.util.Arrays;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String tipo;
    private String cpf;
    private Float creditos;

    public Usuario(Long id, String nome, String email, String senha, String tipo) throws UsuarioTipoInvalido {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        validaTipo(tipo);
    }

    private void validaTipo(String tipo) throws UsuarioTipoInvalido {
        if (Arrays.asList("admin", "beneficiario", "outro").contains(tipo)){
            this.tipo = tipo;
        }else{
            throw new UsuarioTipoInvalido();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
