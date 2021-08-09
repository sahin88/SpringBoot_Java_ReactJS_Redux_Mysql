package expense.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import expense.model.Events;
import expense.model.User;

public interface EventRepository  extends JpaRepository<Events ,Long> {
	
	@Transactional
	@Query("SELECT evnt from Events evnt  WHERE evnt.user=?1")

	List<Events> findAllEventsofUser(User usr);
	
	
	@Transactional
	@Query("SELECT evnt from Events evnt WHERE evnt.user=?1 and evnt.id=?2")
	Events findEventbyIdandbyUser(User user, Long id);

	@Transactional
	@Modifying
	@Query("DELETE FROM Events event WHERE event.user=?1 AND event.id=?2 ")
	void findEventbyIdandbyUserandDelete(User userId, Long id);
}
