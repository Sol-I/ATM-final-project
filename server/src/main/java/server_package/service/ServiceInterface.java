package server_package.service;

import server_package.entity.*;
import server_package.exception.NotFoundElementException;

public interface ServiceInterface {
    User findUserByEmail(String email) throws NotFoundElementException;
    Role findRoleByRoleName(RoleName name) throws NotFoundElementException;
    Client findClientByUserId(int userId) throws NotFoundElementException;
    Account findAccountById(int Id) throws NotFoundElementException;
}
