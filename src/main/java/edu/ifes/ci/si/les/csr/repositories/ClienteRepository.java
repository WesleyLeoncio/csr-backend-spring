package edu.ifes.ci.si.les.csr.repositories;

import edu.ifes.ci.si.les.csr.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM Cliente WHERE tipo_cliente = ?1", nativeQuery = true)
    public List<Cliente> findTipoCliente(String tipo_cliente);

    @Transactional(readOnly = true)
    @Query(value = "SELECT DISTINCT ce.* FROM cliente ce LEFT OUTER JOIN venda vd ON ce.id = vd.cliente_id INNER JOIN veiculo v ON v.id = vd.veiculo_id;", nativeQuery = true)
    public List<Cliente> findClienteComVeiculo();
}
