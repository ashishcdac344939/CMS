package in.fridr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fridr.entity.PainMaster;
@Repository
public interface PainMasterRepo extends JpaRepository<PainMaster, Integer> {

	
}
