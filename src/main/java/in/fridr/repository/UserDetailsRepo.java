package in.fridr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.fridr.entity.UserCredential;
import in.fridr.entity.UserDetail;

public interface UserDetailsRepo extends JpaRepository<UserDetail, Integer>{ 

}
