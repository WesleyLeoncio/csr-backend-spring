package edu.ifes.ci.si.les.csr.repositories;

import edu.ifes.ci.si.les.csr.model.Marca;
import edu.ifes.ci.si.les.csr.model.Modelo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer>{
    
    @Transactional(readOnly = true)
    public List<Modelo> findByMarca(Marca marca);
}
