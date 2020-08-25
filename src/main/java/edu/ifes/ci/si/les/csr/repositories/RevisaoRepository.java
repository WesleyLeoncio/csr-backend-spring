package edu.ifes.ci.si.les.csr.repositories;

import edu.ifes.ci.si.les.csr.model.Revisao;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RevisaoRepository extends JpaRepository<Revisao, Integer>{
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM revisao r WHERE r.status = false AND r.funcionario_id = ?1", nativeQuery = true)
    public List<Revisao> findRevisaoPendente(Integer id);
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(*) FROM revisao r WHERE r.funcionario_id = ?1 AND r.data_revisao = ?2", nativeQuery = true)
    public Integer findCountRevisao(Integer id,Date data);
}
