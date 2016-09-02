package webserver.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDBRepository extends CrudRepository<LocationDB, String> {
	
//	ArrayList<PairedData> findall () throws SQLException, ClassNotFoundException;
}