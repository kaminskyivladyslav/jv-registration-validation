package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exceptions.RegistrationFailException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null) {
            throw new RegistrationFailException("User cannot be null");
        }
        if (user.getLogin() == null || user.getLogin()
                .length() < 6) {
            throw new RegistrationFailException("Login must be greater than 6 characters");
        }
        if (user.getPassword() == null || user.getPassword()
                .length() < 6) {
            throw new RegistrationFailException("Password must be greater than 6 characters");
        }
        if (user.getAge() == null || user.getAge() < 18) {
            throw new RegistrationFailException("Age must be greater than 18");
        }
        if (storageDao.get(user.getLogin()) != null) {
            throw new RegistrationFailException("Login already exists");
        }
        storageDao.add(user);
        return user;
    }

}


