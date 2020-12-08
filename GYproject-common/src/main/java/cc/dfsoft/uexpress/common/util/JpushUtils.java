package cc.dfsoft.uexpress.common.util;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
public class JpushUtils {
    public static final String APP_KEY = "47c2e945d58c1ac4512b906e";
    public static final String MASTER_SECRET = "9d2a59adba55645ffef68df6";

    public static void main(String[] args) {
        jpush("此处填registrationID", "此处填消息通知内容");
    }

    public static String  jpush(String registrationId, String content) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        PushPayload payload = buildPushObject_all_registrationId_alertWithTitle(registrationId, content);

        try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println("Got result - " + result);
            return "success";
        } catch (APIConnectionException e) {
            e.printStackTrace();
            // Connection error, should retry later
            System.out.println("Connection error, should retry later" + e.getMessage());
            return "Connection error, should retry later" + e.getMessage();
        } catch (APIRequestException e) {
            e.printStackTrace();
            // Should review the error, and fix the request
            System.out.println("Should review the error, and fix the request" + e.getMessage());
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
            return "Should review the error, and fix the request" + e.getMessage();
        }
    }

    public static PushPayload buildPushObject_all_registrationId_alertWithTitle(String registrationId, String content) {
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.android())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.registrationId(registrationId))
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.alert(content))
                .build();
    }
}
