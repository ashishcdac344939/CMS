package in.fridr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fridr.entity.PatientPainAssesmentSymtomMasterMapping;
@Repository
public interface PatientPainAssesmentSymtomMasterMappingRepo extends JpaRepository<PatientPainAssesmentSymtomMasterMapping,Integer> {

}
