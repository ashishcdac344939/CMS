package in.fridr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.fridr.entity.StateMaster;

public interface StateRepositoryRepo extends JpaRepository<StateMaster,Integer> {

}
