package edu.ifes.ci.si.les.csr.model;

import java.util.Date;
import javax.persistence.*;
import lombok.*;
import javax.validation.constraints.*;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Funcionario extends Pessoa {

    private static final long serialVersionUID = 1L;
    
    @Column(length = 15)
    @NotBlank(message = "O login deve ser preenchido")
    @Size(min = 5, max = 15, message = "O login deve ter 5 a 15 Caracteres")
    private String login;
    
    @Column(length = 15)
    @NotBlank(message = "A senha deve ser preenchida")
    @Size(min = 6, max = 15, message = "A senha deve ter 6 a 15 Caracteres")
    private String senha;
    
    @Column(length = 9)
    @NotBlank(message = "A profissão deve ser preenchida")
    @Size(min = 7, max = 9, message = "A profissão deve ter 7 a 9 Caracteres")
    private String profissao;   
    
    @Min(value = 1, message = "O salario deve ser maior que zero")
    @NotNull(message = "O salario deve ser preenchido")
    @Digits(integer=6, fraction=2, message = "O salario deve ser preenchido com dígitos")
    private Double salario;
    
    @Builder
    public Funcionario(String login, String senha, String profissao, Double salario, Integer id, String nome, Date dataNacimento, String cpf, String telefone, String cep, String uf, String cidade, String bairro, String rua, Integer numero) {
        super(id, nome, dataNacimento, cpf, telefone, cep, uf, cidade, bairro, rua, numero);
        this.login = login;
        this.senha = senha;
        this.profissao = profissao;
        this.salario = salario;
    }
    
   
    
}
