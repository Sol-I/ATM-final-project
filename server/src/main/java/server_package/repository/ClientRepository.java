package server_package.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server_package.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByUserId(int userId);
}
