package edu.ifes.ci.si.les.csr.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 17)
    @NotBlank(message = "O Chassi do veiculo deve ser preenchido")
    @Size(min = 17, max = 17, message = "O Chassi deve ter 17 Caracter")
    private String chassi;
    
    @Column(length = 30)
    @NotBlank(message = "O combustivel do veiculo deve ser preenchido")
    @Size(min = 3, max = 30, message = "O combustivel deve ter 3 a 30 Caracteres")
    private String combustivel;
    
    @Min(value = 1, message = "O valor do veiculo deve ser maior que zero")
    @NotNull(message = "O valor do veiculo deve ser preenchido")
    @Digits(integer=8, fraction=3, message = "O valor do veiculo deve ser preenchido com dígitos")
    private Double valorVenda;
    
    @Column(length = 4)
    @NotBlank(message = "O ano do veiculo deve ser preenchido")
    @Size(min = 4, max = 4, message = "O ano do veiculo deve ter 4 Caracteres")
    private String ano;
    
    @Column(length = 1)
    @NotNull(message = "O numero de portas do veiculo deve ser preenchido")
    @Digits(integer=1, fraction=1, message = "O numero de portas do veiculo deve ser preenchido com dígitos")
    private Integer portas;
    
    @Column(length = 30)
    @NotBlank(message = "A cor do veiculo deve ser preenchido")
    @Size(min = 3, max = 30, message = "A cor do veiculo deve ter 3 a 30 Caracteres")
    private String cor;
    
    @Column(length = 50)
    @NotBlank(message = "O caminho da imagem deve ser preenchido")
    @Size(min = 18, max = 50, message = "O caminho da imagem deve ter 18 a 50 Caracteres")
    private String imagem;
    
    private Boolean disponivel;
    
    @NotNull(message = "O modelo do veiculo deve ser selecionado")
    @ManyToOne
    @JoinColumn(name="modelo_id")
    private Modelo modelo;

}
