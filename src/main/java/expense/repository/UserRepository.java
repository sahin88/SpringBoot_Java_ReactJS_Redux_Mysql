package expense.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import expense.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Transactional
	@Query("SELECT usr from User usr WHERE usr.email=?1")
	User findUserByEmail(String email);
	
	@Transactional
	@Query("SELECT count(usr) from User usr WHERE usr.email=?1")
	int findUserByEmailBoolean(String email);
	
	
	@Transactional
	@Query("SELECT usr from User usr WHERE usr.id=?1")
	User findUserById(Long userId);



}
