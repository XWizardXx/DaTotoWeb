package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Long id_cliente;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "e_mail", nullable = false, unique = true, length = 320)
    private String e_mail;

    @Column(name = "passw", nullable = false)
    private String passw;

    @Column(name = "credito", nullable = false)
    private double credito;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true)
    private Set<Ordine> ordini = new LinkedHashSet<>();

}
