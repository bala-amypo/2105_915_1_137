@RestController
@RequestMapping("/api/role-permissions")
public class RolePermissionController {

    private final RolePermissionService service;

    public RolePermissionController(RolePermissionService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse assign(@RequestParam Long roleId,
                              @RequestParam Long permissionId) {
        return new ApiResponse(true, "Assigned",
                service.assign(roleId, permissionId));
    }
}
