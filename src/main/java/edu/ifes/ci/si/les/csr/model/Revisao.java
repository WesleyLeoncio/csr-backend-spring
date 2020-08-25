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
public class Revisao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "A data deve ser preechida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Type(type="date")
    private Date dataRevisao;
    
    @NotBlank(message = "O horario deve ser preenchida")
    @Size(min = 5, max = 5, message = "O horario da revisão deve ter entre 5 caracter")
    @Pattern(regexp = "\\d{2}\\:\\d{2}", message = "O horario do teste drive deve seguir o formato: hh:mm")
    private String horaRevisao;
    
    private Boolean status;

    @NotNull(message = "O cliente deve ser selecionado")
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @NotNull(message = "O funcionario deve ser selecionado")
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @NotNull(message = "O veiculo deve ser selecionado")
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

}
