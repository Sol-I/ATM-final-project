package server_package.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import server_package.entity.RoleName;
import server_package.exception.NotFoundElementException;
import server_package.repository.AccountRepository;
import server_package.repository.ClientRepository;
import server_package.repository.RoleRepository;
import server_package.repository.UserRepository;

@DataJpaTest
public class RepositoryServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AccountRepository accountRepository;

    private RepositoryService repositoryService;

    @BeforeEach
    void setUp() {
        repositoryService = new RepositoryService(userRepository, roleRepository,
                clientRepository, accountRepository);
    }

    @Test
    void getAccounts() {
        Assertions.assertEquals(repositoryService.getAccounts(userRepository.findByEmail("mail1@mail.ru")).size(), 7);
        Assertions.assertEquals(repositoryService.getAccounts(userRepository.findByEmail("mail2@mail.ru")).size(), 2);
        Assertions.assertEquals(repositoryService.getAccounts(userRepository.findByEmail("mail4@mail.ru")).size(), 3);
    }

    @Test
    void getAllAccounts() {
        Assertions.assertEquals(repositoryService.getAllAccounts().size(), 7);
    }

    @Test
    void findUserByEmail() throws NotFoundElementException {
        Assertions.assertEquals(repositoryService.findUserByEmail("mail3@mail.ru").getId(), 3);
        Assertions.assertThrows(NotFoundElementException.class, ()
                -> repositoryService.findUserByEmail("mail99@mail.ru"));
    }

    @Test
    void findRoleByNameRole() throws NotFoundElementException {
        Assertions.assertEquals(repositoryService.findRoleByRoleName(RoleName.ADMIN).getId(), 1);
        Assertions.assertThrows(NotFoundElementException.class, ()
                -> repositoryService.findRoleByRoleName(RoleName.TECHNICAL_ROLE));
    }

    @Test
    void findClientByUserId() throws NotFoundElementException {
        Assertions.assertEquals(repositoryService.findClientByUserId(2).getId(), 1);
        Assertions.assertThrows(NotFoundElementException.class, ()
                -> repositoryService.findClientByUserId(99));
    }

    @Test
    void findAccountById() throws NotFoundElementException {
        Assertions.assertEquals(repositoryService.findAccountById(2).getId(), 2);
        Assertions.assertThrows(NotFoundElementException.class, ()
                -> repositoryService.findAccountById(99));
    }
}