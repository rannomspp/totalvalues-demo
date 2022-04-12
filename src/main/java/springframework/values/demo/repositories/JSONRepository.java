package springframework.values.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springframework.values.demo.domain.JSONtoDatabase;

@Repository
public interface JSONRepository extends CrudRepository<JSONtoDatabase, Long> {
}
