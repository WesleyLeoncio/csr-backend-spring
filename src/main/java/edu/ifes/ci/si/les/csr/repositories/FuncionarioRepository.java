package edu.ifes.ci.si.les.csr.repositories;

import edu.ifes.ci.si.les.csr.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {    
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM funcionario WHERE login=?1 AND senha=?2", nativeQuery = true)
    public Funcionario findVerificarLogin(String login, String senha);
    
}
