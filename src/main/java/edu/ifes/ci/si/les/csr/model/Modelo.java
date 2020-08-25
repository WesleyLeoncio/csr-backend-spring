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
public class Modelo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 50)
    @NotBlank(message = "Nome do Modelo deve ser preenchido")
    @Size(min = 3, max = 50, message = "Nome do Modelo deve ter entre 3 a 50 Caracteres")
    private String nomeModelo;
    
    @NotNull(message = "A marca do modelo deve ser selecionada")
    @ManyToOne
    @JoinColumn(name="marca_id")
    private Marca marca;

}
