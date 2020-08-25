package edu.ifes.ci.si.les.csr.repositories;

import edu.ifes.ci.si.les.csr.model.Veiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM veiculo WHERE disponivel = true", nativeQuery = true)
    public List<Veiculo> findVeiculoDisponivel();
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT v.* FROM veiculo v, modelo mo, marca mc WHERE v.disponivel = true AND v.modelo_id = mo.id AND mo.marca_id = mc.id AND mc.id = ?1", nativeQuery = true)
    public List<Veiculo> findVeiculoMarca(Integer id);
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM veiculo v WHERE v.disponivel = true AND v.modelo_id = ?1", nativeQuery = true)
    public List<Veiculo> findVeiculoModelo(Integer id);
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT v.* FROM veiculo v LEFT OUTER JOIN venda vd ON v.id = vd.veiculo_id INNER JOIN cliente ce ON vd.cliente_id = ce.id AND ce.id = ?1", nativeQuery = true)
    public List<Veiculo> findVeiculoCliente(Integer id);
    
}
