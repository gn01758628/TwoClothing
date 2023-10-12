package com.twoclothing.web.permission;

import java.util.List;

public interface PermissionDAO {
    void addPermission(Permission permission);
    void updatePermission(Permission permission);
    void deletePermission(Integer permissionId);
    List<Permission> getAllPermissions();
    Permission getPermissionById(Integer permissionId);
    List<Permission> getPermissionsByPermissionName(String permissionName);
}
