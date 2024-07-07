package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cliente", nullable = false)
    private long id_cliente;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "e_mail", nullable = false, unique = true)
    private String e_mail;

    @Column(name = "passw", nullable = false)
    private String passw;

    @Column(name = "credito", nullable = false)
    private int credito;

}
