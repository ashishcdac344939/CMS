package in.fridr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fridr.entity.DistrictMaster;
@Repository
public interface DistrictMasterRepo extends JpaRepository<DistrictMaster,Integer> {

	List<DistrictMaster> findByStateMasterLgdStateCode(Integer lgdStateCode);

}
