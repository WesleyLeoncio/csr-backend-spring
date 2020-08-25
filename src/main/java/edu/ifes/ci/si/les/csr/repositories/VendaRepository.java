package edu.ifes.ci.si.les.csr.repositories;

import edu.ifes.ci.si.les.csr.model.Venda;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(*) FROM venda v WHERE v.funcionario_id = ?1", nativeQuery = true)
    public Integer findCountVenda(int id);
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT v.* FROM venda v WHERE EXTRACT(YEAR FROM data_venda) = ?1 AND EXTRACT(MONTH FROM data_venda) = ?2", nativeQuery = true)
    public List<Venda> findVendaMesAno(double ano, double mes);
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM venda WHERE cliente_id = ?1", nativeQuery = true)
    public List<Venda> findVendaCliente(int id);
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(*), SUM(v.valor_final) - SUM(cp.valor_compra) "
    		+ "FROM venda v,compra cp,veiculo vc "
    		+ "WHERE v.veiculo_id = vc.id AND vc.id = cp.veiculo_id "
    		+ "AND EXTRACT(YEAR FROM v.data_venda) = ?1 "
    		+ "AND EXTRACT(MONTH FROM v.data_venda) = ?2 ", nativeQuery = true)
    public Object findVendaLucroAnoMes(double ano, double mes);
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT vc.imagem, mo.nome_modelo,COUNT (vc.modelo_id)"
            + " FROM venda v, veiculo vc, modelo mo "
            + "WHERE v.veiculo_id = vc.id "
            + "AND vc.modelo_id = mo.id "
            + "GROUP BY mo.nome_modelo, vc.imagem ORDER BY COUNT (vc.modelo_id) DESC", nativeQuery = true)
    public List<String> findVendaVeiculoVendido();
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT f.nome,f.profissao, COUNT(v.id) FROM venda v, funcionario f WHERE v.funcionario_id = f.id GROUP BY f.nome,f.profissao ORDER BY COUNT(v.id)DESC;", nativeQuery = true)
    public List<String> findVendaMelhorFunc();
    
}
