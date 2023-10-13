package com.twoclothing.web.permissions;

import java.util.List;

public interface PermissionsDAO {
    void addPermissions(Permissions permission);
    void updatePermissions(Permissions permission);
    void deletePermissions(Integer permissionId);
    List<Permissions> getAllPermissions();
    Permissions getPermissionsById(Integer permissionId);
    List<Permissions> getPermissionsByPermissionName(String permissionName);
}
