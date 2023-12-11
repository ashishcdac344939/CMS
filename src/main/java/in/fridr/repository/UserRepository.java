package in.fridr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fridr.entity.UserDetail;

@Repository
public interface UserRepository extends JpaRepository<UserDetail,Integer>{

}
