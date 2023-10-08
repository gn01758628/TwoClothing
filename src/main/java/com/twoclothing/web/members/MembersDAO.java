package com.twoclothing.web.members;

import java.util.List;

public interface MembersDAO {

    void insert(Members members);

    Members getByPrimaryKey(Integer mbrId);

    List<Members> getAll();

    void update(Members members);

    void delete(Integer mbrId);
}
