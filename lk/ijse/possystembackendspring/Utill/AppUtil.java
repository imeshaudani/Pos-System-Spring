package lk.ijse.possystembackendspring.Utill;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String genereteCustomerId(){
        return "CUST-"+UUID.randomUUID();
    }
    public static String genereteItemId(){
        return "ITEM-"+UUID.randomUUID();
    }
    public static String genereteOrderId(){
        return "ORDER-"+UUID.randomUUID();
    }
    public static String genereteOrderDetailId(){
        return "ORDER-DETAIL-"+UUID.randomUUID();
    }

    public static String genereteUserId() {
        return "USER-"+UUID.randomUUID();
    }
    public static String profilePickToBase64(byte[] profilePic){
        return Base64.getEncoder().encodeToString(profilePic);
    }
}
