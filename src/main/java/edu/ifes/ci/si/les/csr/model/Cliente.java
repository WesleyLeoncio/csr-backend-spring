package edu.ifes.ci.si.les.csr.model;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Cliente extends Pessoa {

    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "O tipo do cliente deve ser selecionada")
    private String tipoCliente;
    
    
    
    @Builder
    public Cliente(String tipoCliente, Integer id, String nome, Date dataNacimento, String cpf, String telefone, String cep, String uf, String cidade, String bairro, String rua, Integer numero) {
        super(id, nome, dataNacimento, cpf, telefone, cep, uf, cidade, bairro, rua, numero);
        this.tipoCliente = tipoCliente;
    }
    
  
}
