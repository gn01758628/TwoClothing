package com.twoclothing.web.itemreport;

import java.util.List;

public interface ItemReportDAO {
	
	 public void insert(ItemReport itemBrowsing);

	    public ItemReport getByPrimaryKey(Integer reportId);

	    public List<ItemReport> getAll();

	    public List<ItemReport> getAllByMbrId(Integer mbrId);

	    public List<ItemReport> getAllByEmpId(Integer empId);

	    public void update(Integer reportId);
}
