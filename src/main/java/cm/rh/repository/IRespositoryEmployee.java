package cm.rh.repository;

import cm.rh.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRespositoryEmployee extends JpaRepository<Employee, Integer> {
}
