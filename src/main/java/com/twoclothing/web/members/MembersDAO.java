package com.twoclothing.web.members;

import java.util.List;

public interface MembersDAO {

    public void insert(Members members);

    public Members getByPrimaryKey(Integer mbrId);

    public List<Members> getAll();

    public void update(Integer mbrId);

    public void delete(Integer mbrId);
}
