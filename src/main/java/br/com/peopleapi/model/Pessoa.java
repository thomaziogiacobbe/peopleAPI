package br.com.peopleapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@Table(name = "pessoa")
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Pessoa_SEQ")
    @SequenceGenerator(name = "Pessoa_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE, CascadeType.MERGE },
            orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    public void addEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
        endereco.setPessoa(this);
    }

    public void removeEndereco(Endereco endereco) {
        this.enderecos.remove(endereco);
        endereco.setPessoa(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pessoa pessoa = (Pessoa) o;
        return this.id != null && Objects.equals(this.id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}