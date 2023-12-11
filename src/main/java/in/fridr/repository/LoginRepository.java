package in.fridr.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fridr.entity.UserCredential;

@Repository
public interface LoginRepository extends JpaRepository<UserCredential,Integer> {

	UserCredential findByUserIdAndOtp(String email, String otp);

	UserCredential findByUserId(String email);

}
