package br.com.peopleapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import javax.validation.constraints.Pattern;
import java.util.Objects;


@Entity
@Getter
@Setter
@Builder
@Table(name = "endereco", uniqueConstraints = {
        @UniqueConstraint(name = "UniqueAddress", columnNames = { "cep", "numero", "logradouro" })
})
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Endereco_SEQ")
    @SequenceGenerator(name = "Endereco_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @Column(name = "is_principal")
    private Boolean isPrincipal = false;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    private Integer numero;

    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP é invalido")
    @Column(name = "cep", length = 9, nullable = false)
    private String cep;

    @Column(name = "cidade")
    private String cidade;

    public Boolean isUniqueEndereco(Endereco other) {
        return !(other.getCep().equals(this.cep)
                && other.getNumero() == this.numero
                && other.getLogradouro().equals(this.logradouro));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Endereco endereco = (Endereco) o;
        return this.id != null && Objects.equals(this.id, endereco.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}