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
    private String messageText;
    private String messageUser;
    private long messageTime;

    public Message(String messageName, String messageUser,String messageAddr,String messagePhone,String messageProb,String messageProbDesc) {
        this.messageName = messageName;
        this.messageUser = messageUser;
        this.messageAddr=messageAddr;
        this.messagePhone=messagePhone;
        this.messageProb=messageProb;
        this.messageProbDesc=messageProbDesc;
        messageTime = new Date().getTime();
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
}
