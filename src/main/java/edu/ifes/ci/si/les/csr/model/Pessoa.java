package edu.ifes.ci.si.les.csr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import javax.persistence.*;
import lombok.*;
import java.util.Date;
import javax.validation.constraints.*;
import org.hibernate.annotations.Type;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    @NotBlank(message = "O nome do cliente deve ser preenchido")
    @Size(min = 3, max = 50, message = "O nome deve ter 3 a 50 Caracteres")
    private String nome;

    @NotNull(message = "A data deve ser preechida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Type(type="date")
    private Date dataNacimento;

    @NotBlank(message = "O cpf deve ser preenchido")
    @Size(min = 14, max = 14, message = "O cpf deve ter 14 caracter")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve seguir o formato: 000.000.000-00")
    private String cpf;

    @NotBlank(message = "O telefone deve ser preenchido")
    @Size(min = 14, max = 14, message = "O telefone deve ter 14 caracter")
    @Pattern(regexp = "\\d{3}\\ \\d{5}\\-\\d{4}", message = "O Telefone deve seguir o formato: 000 00000-0000")
    private String telefone;

    @NotBlank(message = "O cep deve ser preenchido")
    @Size(min = 9, max = 9, message = "O cep deve ter 9 caracter")
    @Pattern(regexp = "\\d{5}\\-\\d{3}", message = "O CPF deve seguir o formato: 00000-000")
    private String cep;

    @Column(length = 2)
    @NotBlank(message = "Sigla da UF deve ser preenchida")
    @Size(min = 2, max = 2, message = "Sigla da UF deve ter 2 Caracter")
    private String uf;

    @Column(length = 50)
    @NotBlank(message = "Nome da cidade deve ser preenchida")
    @Size(min = 3, max = 50, message = "Nome da cidade deve ter 3 a 50 Caracteres")
    private String cidade;

    @Column(length = 50)
    @NotBlank(message = "Nome do bairro deve ser preenchida")
    @Size(min = 3, max = 50, message = "Nome do bairro deve ter 3 a 50 Caracteres")
    private String bairro;

    @Column(length = 50)
    @NotBlank(message = "Nome da rua deve ser preenchida")
    @Size(min = 3, max = 50, message = "Nome da rua deve ter 3 a 50 Caracteres")
    private String rua;

    @Column(length = 5)
    @NotNull(message = "O numero deve ser preenchido")
    @Digits(integer = 5, fraction = 1, message = "O numero deve ser preenchido com dígitos")
    private Integer numero;

}
