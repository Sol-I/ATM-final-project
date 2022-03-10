package server_package.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server_package.entity.Account;
import server_package.entity.Client;

import java.util.List;
import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findById(int id);
}
