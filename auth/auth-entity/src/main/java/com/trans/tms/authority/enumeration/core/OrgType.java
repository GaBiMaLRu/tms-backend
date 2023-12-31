package com.trans.tms.authority.enumeration.core;

public enum OrgType {
    BRANCH_OFFICE(1, "分公司"),
    TOP_TRANSFER_CENTER(2, "一级转运中心"),
    SECONDARY_TRANSFER_CENTER(3, "二级转运中心"),
    BUSINESS_HALL(4, "网点");

    private Integer type;
    private String name;

    private OrgType(Integer type, String name) {
        this.name = name;
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public static OrgType getEnumByType(Integer type) {
        if (null == type) {
            return null;
        }
        for (OrgType orgType : values()) {
            if (orgType.getType().equals(type)) {
                return orgType;
            }
        }
        return null;
    }
}
