@Service
public class PermissionServiceImpl implements PermissionService {

    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }

    @Override
    public Permission updatePermission(Long id, Permission permission) {
        permission.setId(id);
        return permissionRepository.save(permission);
    }

    @Override
    public void deactivatePermission(Long id) {
        Permission permission = getPermissionById(id);
        permission.setActive(false);
        permissionRepository.save(permission);
    }
}
