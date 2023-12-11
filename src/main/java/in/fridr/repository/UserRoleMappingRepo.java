package in.fridr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fridr.entity.UserRolesMapping;

@Repository
public interface UserRoleMappingRepo extends JpaRepository<UserRolesMapping, Integer> {
	

}
