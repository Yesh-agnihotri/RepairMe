package repaireme.com.repairme;

/**
 * Created by YESH on 24-05-2018.
 */

import java.util.Date;


public class Message {
    private String messageName;
    private String messageAddr;
    private String messagePhone;
    private String messageProb;
    private String messageProbDesc;
    private String company_name;
    private String model_no;
    private String prod_id;
    private String coupon;
    private String serviceType;
    private String messageUser;
    private long messageTime;

    public Message(String messageName, String messageUser,String messageAddr,String messagePhone,String messageProb,String messageProbDesc,String company_name,String model_no,String prod_id,String coupon,String serviceType) {

        this.messageName = messageName;
        this.messageUser = messageUser;
        this.messageAddr=messageAddr;
        this.messagePhone=messagePhone;
        this.messageProb=messageProb;
        this.messageProbDesc=messageProbDesc;
        messageTime = new Date().getTime();
        this.company_name=company_name;
        this.model_no=model_no;
        this.prod_id=prod_id;
        this.coupon=coupon;
        this.serviceType=serviceType;
    }

    public Message() {
    }



    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getMessageAddr() {
        return messageAddr;
    }

    public void setMessageAddr(String messageAddr) {
        this.messageAddr = messageAddr;
    }

    public String getMessagePhone() {
        return messagePhone;
    }

    public void setMessagePhone(String messagePhone) {
        this.messagePhone = messagePhone;
    }

    public String getMessageProb() {
        return messageProb;
    }

    public void setMessageProb(String messageProb) {
        this.messageProb = messageProb;
    }

    public String getMessageProbDesc() {
        return messageProbDesc;
    }

    public void setMessageProbDesc(String messageProbDesc) {
        this.messageProbDesc = messageProbDesc;
    }
    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getModel_no() {
        return model_no;
    }

    public void setModel_no(String model_no) {
        this.model_no = model_no;
    }

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

}
