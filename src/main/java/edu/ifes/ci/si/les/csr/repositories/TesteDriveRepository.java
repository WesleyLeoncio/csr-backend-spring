package edu.ifes.ci.si.les.csr.repositories;

import edu.ifes.ci.si.les.csr.model.TesteDrive;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface TesteDriveRepository extends JpaRepository<TesteDrive, Integer>{
    @Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(*) FROM teste_drive td WHERE td.funcionario_id = ?1 AND td.data_teste = ?2", nativeQuery = true)
    public Integer findCountTesteDrive(Integer id,Date data);
}
