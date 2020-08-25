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
public class Marca implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 50)
    @NotBlank(message = "Nome da Marca deve ser preenchido")
    @Size(min = 3, max = 50, message = "Nome da Marca deve ter entre 3 a 50 Caracteres")
    private String nomeMarca;

}
