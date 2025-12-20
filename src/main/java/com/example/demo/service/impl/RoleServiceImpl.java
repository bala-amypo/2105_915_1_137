@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role updateRole(Long id, Role role) {
        role.setId(id);
        return roleRepository.save(role);
    }

    @Override
    public void deactivateRole(Long id) {
        Role role = getRoleById(id);
        role.setActive(false);
        roleRepository.save(role);
    }
}
