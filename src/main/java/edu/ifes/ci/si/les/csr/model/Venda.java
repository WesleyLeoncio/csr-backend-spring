package edu.ifes.ci.si.les.csr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.io.Serializable;
import javax.persistence.*;
import lombok.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Type;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 20)
    @NotBlank(message = "O tipo de cliente deve ser preenchido")
    @Size(min = 4, max = 20, message = "O tipo de cliente deve ter 7 a 11 Caracteres")
    private String tipoCliente;
    
    @NotNull(message = "A data deve ser preechida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Type(type="date")
    private Date dataVenda;
    
    @Min(value = 1, message = "O valor do veiculo deve ser maior que zero")
    @NotNull(message = "O valor do veiculo deve ser preenchido")
    @Digits(integer=8, fraction=3, message = "O valor do veiculo deve ser preenchido com dígitos")
    private Double valorFinal;
    
    @NotNull(message = "O cliente deve ser selecionado")
    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;
    
    @NotNull(message = "O funcionario deve ser selecionado")
    @ManyToOne
    @JoinColumn(name="funcionario_id")
    private Funcionario funcionario;
    
    @NotNull(message = "O veiculo deve ser selecionado")
    @ManyToOne
    @JoinColumn(name="veiculo_id")
    private Veiculo veiculo;

}
