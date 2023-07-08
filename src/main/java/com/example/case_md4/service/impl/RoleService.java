package com.example.case_md4.service.impl;

import com.example.case_md4.model.Role;
import com.example.case_md4.repository.IRoleRepository;
import com.example.case_md4.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        List<Role> roleList = (List<Role>) findAll();
        for (Role r:roleList
             ) {
            if(!r.getName().equals(role.getName())){
                roleList.add(role);
            }
        }
        return null;
    }

    @Override
    public void remove(Long id) {
        Optional<Role> roleOptional = findOne(id);
        if(roleOptional.isPresent()){
            iRoleRepository.deleteById(id);
        }

    }
}
