package com.ecommerce.ecommerceapp.service;

import com.ecommerce.ecommerceapp.dao.RoleRepository;
import com.ecommerce.ecommerceapp.entity.ERole;
import com.ecommerce.ecommerceapp.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public void saveRole(Role role){
        roleRepository.save(role);
    }

    public Role findByName(ERole eRole){
        return roleRepository.findByName(eRole).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }
}
