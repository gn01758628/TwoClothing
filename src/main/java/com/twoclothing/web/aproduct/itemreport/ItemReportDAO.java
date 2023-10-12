package com.twoclothing.web.aproduct.itemreport;

import java.util.List;

public interface ItemReportDAO {
	
	 public void insert(ItemReport itemReport);

	    public ItemReport getByPrimaryKey(Integer reportId);

	    public List<ItemReport> getAll();

	    public List<ItemReport> getAllByItemId(Integer itemId);

	    public List<ItemReport> getAllByEmpId(Integer empId);
	    
	    public List<ItemReport> getAllByRStatus(Integer rStatus);

	    public List<ItemReport> getAllByResult(Integer result);

	    public void update(Integer reportId);
}
