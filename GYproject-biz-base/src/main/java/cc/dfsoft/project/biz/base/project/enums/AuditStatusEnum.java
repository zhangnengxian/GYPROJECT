package cc.dfsoft.project.biz.base.project.enums;
/**
 * @ClassDesc: 审核状态枚举
 * @Author zhangnx
 * @Date 2019/7/27 11:00
 * Version:1.0
 */
public enum AuditStatusEnum {
    TO_SIGN("0","待签订"),
    TO_PUSH("1","待推送"),
    AUDIT_IN_PROGRESS("2","审核中"),
    PASS("3","已通过"),
    NO_PASS("4","未通过"),
    TO_MODIFY("5","待修改"),
    DELETE("-1","已作废");

    private final String code;
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    AuditStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

 public static String getMessageByCode(String code){
     for(AuditStatusEnum e: AuditStatusEnum.values()) {
         if(e.getCode().equals(code)) {
             return e.getMessage();
         }
     }
     return null;
 }




}
