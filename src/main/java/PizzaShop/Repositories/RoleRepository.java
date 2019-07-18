package PizzaShop.Repositories;

import PizzaShop.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findOneByRole(String role);
}
