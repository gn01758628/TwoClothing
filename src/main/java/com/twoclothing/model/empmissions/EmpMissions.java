package com.twoclothing.model.empmissions;


import javax.persistence.*;

import com.twoclothing.model.employee.Employee;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "empmissions")
public class EmpMissions implements Serializable {

    @EmbeddedId
    private CompositeDetail compositeKey;
    
    @ManyToOne(fetch = FetchType.LAZY) // 定义与Employee的多对一关系
    @JoinColumn(name = "empid", insertable = false, updatable = false)
    private Employee employee;

        
    public EmpMissions() {
    }

    public EmpMissions(CompositeDetail compositeKey) {
        this.compositeKey = compositeKey;
    }

    @Override
    public String toString() {
        return "EmpMissions{" +
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
        }

        public CompositeDetail(Integer empId, Integer permissionId) {
            this.empId = empId;
            this.permissionId = permissionId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeDetail that = (CompositeDetail) o;
            return Objects.equals(empId, that.empId) && Objects.equals(permissionId, that.permissionId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(empId, permissionId);
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
    
	  public com.twoclothing.model.employee.Employee getEmployee() {
		  com.twoclothing.tonyhsieh.service.EmployeeServiceImpl EmpSvcq = new com.twoclothing.tonyhsieh.service.EmployeeServiceImpl();
		  com.twoclothing.model.employee.Employee employee = EmpSvcq.getByPrimaryKey(compositeKey.empId);
		    return employee;
	    }
    
}
