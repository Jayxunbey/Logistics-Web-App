package com.example.logisticproject.service.auth;

import com.example.logisticproject.config.CustomUserDetails;
import com.example.logisticproject.entity.auth.Permission;
import com.example.logisticproject.entity.auth.Role;
import com.example.logisticproject.entity.auth.User;
import com.example.logisticproject.repo.PermissionRepository;
import com.example.logisticproject.repo.RoleRepository;
import com.example.logisticproject.repo.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new ValidationException("user.not.found");

        Set<GrantedAuthority> authorities = new HashSet<>();
        List<Role> roles = roleRepository.findByUser(user.getId());
        if (!Objects.isNull(roles)) {
            roles.forEach(role -> {
                authorities.add(authority.apply(role.getAuthority()));
                List<Permission> permissions = permissionRepository.findByRoleId(role.getId());
                if (!Objects.isNull(permissions)) {
                    permissions.forEach(permission -> authorities.add(authority.apply(permission.getAuthority())));
                }
            });
        }
        return new CustomUserDetails(user, authorities);
    }

    private final static Function<String, SimpleGrantedAuthority> authority = SimpleGrantedAuthority::new;
}
