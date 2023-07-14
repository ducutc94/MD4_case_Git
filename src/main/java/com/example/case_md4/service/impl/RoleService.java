package com.example.case_md4.service.impl;
import com.example.case_md4.model.Role;
import com.example.case_md4.repository.IRoleRepository;
import com.example.case_md4.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;

    @Override
    public Iterable<Role> findAll() {
        return iRoleRepository.findAll();
    }

    @Override
    public Optional<Role> findOne(Long id) {
        return iRoleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        Optional<Role> role1 = findOne(role.getId());
        if(role1.isPresent()){
            return null;
        }else {
            return iRoleRepository.save(role);
        }
    }

    @Override
    public void remove(Long id) {
        Optional<Role> roleOptional = findOne(id);
        if(roleOptional.isPresent()){
            iRoleRepository.deleteById(id);
        }

    }
}
