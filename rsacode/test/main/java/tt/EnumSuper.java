package tt;

public class EnumSuper {
    public Integer code;
    private String desc;
    EnumSuper(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

//    public static String getDesc(int code){
//        for (EnumSuper e : EnumSuper.values()) {
//            if (e.getCode() == code) {
//                return e.getDesc();
//            }
//        }
//        return null;
//    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
