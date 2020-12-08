package cc.dfsoft.project.biz.base.maintain.entity;

public enum StatusEnum {

    //***********************签证记录************************
    TO_AUDIT("1","待审核",""),
    TO_PUSH("2","待推送",""),
    NO_PASSING("3","未通过",""),
    PASSED("4","已通过",""),
    TO_BUDGET("5","待预算调整","120206"),//签证记录
    RE_PUSH("6","待调整签证","120206"),//签证记录
    QUANTY_AUDIT("7","待签证量审核",""),
    TO_CANCEL("-1","作废","");



    private final String code;
    private final String name;
    private final String menuId;

    StatusEnum(String code, String name, String menuId) {
        this.code = code;
        this.name = name;
        this.menuId = menuId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getMenuId() {
        return menuId;
    }
}
