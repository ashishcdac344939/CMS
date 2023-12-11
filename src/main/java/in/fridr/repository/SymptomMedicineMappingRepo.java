package in.fridr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fridr.entity.SymptomMedicineMapping;

@Repository
public interface SymptomMedicineMappingRepo extends JpaRepository<SymptomMedicineMapping,Integer>{

	List<SymptomMedicineMapping> findBySymptomsMasterSymptomId(Integer symptomId);
	
	

}
