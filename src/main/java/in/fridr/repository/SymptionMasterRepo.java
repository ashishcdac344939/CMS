package in.fridr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fridr.entity.SymptomsMaster;
@Repository
public interface SymptionMasterRepo extends JpaRepository<SymptomsMaster,Integer> {
	
	List<SymptomsMaster> findBySymptomStatus( boolean symptomStatus);

}
