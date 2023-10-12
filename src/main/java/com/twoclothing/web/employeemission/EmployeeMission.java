package com.twoclothing.web.employeemission;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.twoclothing.web.blacklist.BlackList.CompositeDetail;


@Entity
@Table(name = "employeemission")
public class EmployeeMission implements Serializable{
    
	@EmbeddedId
	private CompositeDetail compositeKey;
	
	 public EmployeeMission() {
			super();
		}
	
	 public EmployeeMission(CompositeDetail compositeKey) {
	        this.compositeKey = compositeKey;
	    }
	
	 @Override
	    public String toString() {
	        return "EmployeeMission{" +
	                "compositeKey=" + compositeKey +
	                '}';
	    }
	 
	 public CompositeDetail getCompositeKey() {
	        return compositeKey;
	    }

	    public void setCompositeKey(CompositeDetail compositeKey) {
	        this.compositeKey = compositeKey;
	    }
	
	@Embeddable 
	public static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;
		@Column(name = "empid")
		private Integer empId;
		@Column(name = "permissionid")
		private Integer permissionId;
		
		
		public CompositeDetail() {
			super();
			// TODO Auto-generated constructor stub
		}


		public CompositeDetail(Integer empId, Integer permissionId) {
			super();
			this.empId = empId;
			this.permissionId = permissionId;
		}


		@Override
		public int hashCode() {
			return Objects.hash(empId, permissionId);
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompositeDetail other = (CompositeDetail) obj;
			return Objects.equals(empId, other.empId) && Objects.equals(permissionId, other.permissionId);
		}


		@Override
		public String toString() {
			return "CompositeDetail [empId=" + empId + ", permissionId=" + permissionId + "]";
		}


		public Integer getEmpId() {
			return empId;
		}


		public void setEmpId(Integer empId) {
			this.empId = empId;
		}


		public Integer getPermissionId() {
			return permissionId;
		}


		public void setPermissionId(Integer permissionId) {
			this.permissionId = permissionId;
		}

	}
}
	
    

   

	

