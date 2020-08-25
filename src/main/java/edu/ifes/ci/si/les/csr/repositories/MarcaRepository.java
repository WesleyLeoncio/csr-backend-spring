package edu.ifes.ci.si.les.csr.repositories;

import edu.ifes.ci.si.les.csr.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer>{
    
}
