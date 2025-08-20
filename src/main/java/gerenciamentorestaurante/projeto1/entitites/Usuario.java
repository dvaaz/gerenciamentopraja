package gerenciamentorestaurante.projeto1.entitites;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="usuario")
public class Usuario {
  @Id
  @GeneratedValue
  @Column(name="usuario_id")
    private int id;
  @Column(name="usuario_nome")
    private String nome;
  @Column(name="usuario_telefone")
    private String telefone;
  @Column(name="usuario_email")
    private String email;
  /**
   * Entradas para o tipo de usuário
   * usuario = 0 , cozinha = 1, admin = 2
   */
  @Column(name="usuario_tipo")
    private int tipo;
  @Column(name="usuario_senha_hash")
    private String senha;
  /**
   * Entradas para o status
   * removido: -1, inativo: 0, ativo: 1
   */
  @Column(name="usuario_status")
    private int status;
  @Column(name="usuario_criado_em")
    private LocalDateTime data_criacao;

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

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getTipo() {
    return tipo;
  }

  public void setTipo(int tipo) {
    this.tipo = tipo;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public LocalDateTime getData_criacao() {
    return data_criacao;
  }

  public void setData_criacao(LocalDateTime data_criacao) {
    this.data_criacao = data_criacao;
  }

  // Métodos
  public String getFormattedDataHora() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    return data_criacao.format(formatter);
  }

}
