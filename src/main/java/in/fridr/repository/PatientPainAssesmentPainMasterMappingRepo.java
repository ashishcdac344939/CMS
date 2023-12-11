package in.fridr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fridr.entity.PatientPainAssesmentPainMasterMapping;

@Repository
public interface PatientPainAssesmentPainMasterMappingRepo extends JpaRepository<PatientPainAssesmentPainMasterMapping, Integer>{

}
