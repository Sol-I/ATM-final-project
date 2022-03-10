package server_package.service;

import lombok.AllArgsConstructor;
import server_package.entity.*;
import server_package.exception.NotFoundElementException;
import server_package.repository.AccountRepository;
import server_package.repository.ClientRepository;
import server_package.repository.RoleRepository;
import server_package.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RepositoryService implements ServiceInterface {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    @Override
    public User findUserByEmail(String email) throws NotFoundElementException {
        if (!isUserExist(email))
            throw new NotFoundElementException("Not found user in UserRepository");
        return userRepository.findByEmail(email);
    }

    @Override
    public Role findRoleByRoleName(RoleName name) throws NotFoundElementException {
        if (!isRoleExist(name))
            throw new NotFoundElementException("Not found role in RoleRepository");
        return roleRepository.findByRole(name.toString());
    }

    @Override
    public Client findClientByUserId(int userId) throws NotFoundElementException {
        if (!isClientExist(userId))
            throw new NotFoundElementException("Not found client in ClientRepository");
        return clientRepository.findByUserId(userId);
    }

    @Override
    public Account findAccountById(int id) throws NotFoundElementException {
        if (!isAccountExist(id))
            throw new NotFoundElementException("Not found account in AccountRepository");
        return accountRepository.findById(id);
    }

    private boolean isUserExist(String email) {
        return userRepository.findAll().stream().anyMatch(u -> u.getEmail().equals(email));
    }

    private boolean isRoleExist(RoleName name) {
        return roleRepository.findAll().stream().anyMatch(u -> u.getRole().equals(name.toString()));
    }

    private boolean isClientExist(int userId) {
        return clientRepository.findAll().stream().anyMatch(u -> u.getUserId() == userId);
    }

    private boolean isAccountExist(int id) {
        return accountRepository.findAll().stream().anyMatch(u -> u.getId() == id);
    }

    public List<Account> getAccounts(User user) {

        List<Account> accounts = new ArrayList<>();
        Set<String> roles = user.getNameRoles();
        System.out.println(roles.toString());
        if (roles.contains(RoleName.ADMIN.toString())) {
            accounts = getAllAccounts();
        } else if (roles.contains(RoleName.USER.toString())) {
            Client client = clientRepository.findByUserId(user.getId());
            accounts = client.getAccounts();
        }
        return accounts;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

}
