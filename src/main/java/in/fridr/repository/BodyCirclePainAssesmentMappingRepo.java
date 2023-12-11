package in.fridr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fridr.entity.BodyCirclePainAssesmentMapping;
@Repository
public interface BodyCirclePainAssesmentMappingRepo extends JpaRepository<BodyCirclePainAssesmentMapping, Integer> {

}
