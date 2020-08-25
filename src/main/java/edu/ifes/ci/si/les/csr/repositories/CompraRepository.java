package edu.ifes.ci.si.les.csr.repositories;

import edu.ifes.ci.si.les.csr.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>{
    
}
