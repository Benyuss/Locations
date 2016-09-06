package hu.benyuss.geohash.webserver.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDBRepository extends CrudRepository<UsersDB, String> {
	UsersDB findByNicknameOrEmail(String nickname, String email);
}