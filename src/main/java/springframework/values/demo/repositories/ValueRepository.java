package springframework.values.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springframework.values.demo.domain.Value;

@Repository
public interface ValueRepository extends CrudRepository<Value, Long> {
}
