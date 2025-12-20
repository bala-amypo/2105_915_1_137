@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Override
    public RolePermission grantPermission(RolePermission mapping) {
        return rolePermissionRepository.save(mapping);
    }

    @Override
    public List<RolePermission> getPermissionsForRole(Long roleId) {
        return rolePermissionRepository.findByRoleId(roleId);
    }

    @Override
    public RolePermission getMappingById(Long id) {
        return rolePermissionRepository.findById(id).orElse(null);
    }

    @Override
    public void revokePermission(Long id) {
        rolePermissionRepository.deleteById(id);
    }
}
