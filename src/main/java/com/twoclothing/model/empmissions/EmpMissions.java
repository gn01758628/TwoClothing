package com.twoclothing.model.empmissions;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "empmissions")
public class EmpMissions implements Serializable {

    @EmbeddedId
    private CompositeDetail compositeKey;

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
}
