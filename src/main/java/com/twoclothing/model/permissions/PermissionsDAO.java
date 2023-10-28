package com.twoclothing.model.permissions;

import java.util.List;

public interface PermissionsDAO {
	void insert(Permissions permission);

	List<Permissions> getAll();

	Permissions getByPrimaryKey(Integer permissionId);

	List<Permissions> getAllByPermissionName(String permissionName);

	void update(Permissions permission);

	void delete(Integer permissionId);
}
