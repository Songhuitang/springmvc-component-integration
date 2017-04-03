package win.songhuitang.util;

/**
 * Created by simon.song on 2017/4/3.
 */
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName:EmailUtil <br/>
 * Function: 发送邮件相关功能. <br/>
 * Date:     Aug 8, 2016 3:46:29 PM <br/>
 * @author   simon.song
 * @version
 * @since    JDK 1.8
 * @see
 */
public class EmailUtil {

    private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    private static final String MAIL_USERNAME = "";
    private static final String MAIL_PASSWORD = "";
    private static final String MAIL_HOST = "";
    private static final String MAIL_PORT = "";

    private static final String MAIL_SENDER = "";
    private static final String MAIL_SUBJECT = "";
    private static final String MAIL_CONTENT = "";

    private Properties props = System.getProperties();
    private Session session;
    private MailAuthenticator authenticator;

    //构造函数初始化。
    public EmailUtil(){
        setProps();
        authenticator = new MailAuthenticator(MAIL_USERNAME,MAIL_PASSWORD);
        session = Session.getInstance(props, authenticator);
    }

    private void setProps(){
        props.put("mail.smtp.protocol", "smtp");
        props.put("mail.smtp.host", MAIL_HOST);
        props.put("mail.smtp.port", MAIL_PORT);
        props.put("mail.smtp.starttls.enable","false");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", MAIL_PORT);
        props.put("mail.smtp.socketFactory.class", "");
        props.put("mail.smtp.socketFactory.fallback", "false");
    }

    /**
     *
     * sendEmail:(向指定用户发送邮件). <br/>
     *
     * @author simon.song
     * @param recipient 收件人
     * @throws MessagingException
     * @throws AddressException
     * @since JDK 1.8
     */
    public void sendEmail(String recipient){
        try{
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(MAIL_SENDER));
            message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(MAIL_SUBJECT);
            message.setContent(MAIL_CONTENT, "text/html;charset=utf-8");

            Transport.send(message);
        }catch(Exception e){
            logger.error("Exception occurs in sendEmail functionality. Error: "+e.getMessage());
        }

    }

    /**
     *
     * sendEmail:(向多个用户邮箱发送带附件邮件). <br/>
     *
     * @author simon.song
     * @param recipients 收件人列单
     * @param attachment 附件
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     * @since JDK 1.8
     */
    public void sendEmail(List<String> recipients, File attachment){
        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(MAIL_SENDER));

            InternetAddress[] addresses = new InternetAddress[recipients.size()];
            for(int i = 0; i < addresses.length; i++){
                addresses[i] = new InternetAddress(recipients.get(i));
            }

            message.setRecipients(RecipientType.BCC, addresses);
            message.setSubject(MAIL_SUBJECT);

            Multipart multipart = new MimeMultipart();
            BodyPart contentPart = new MimeBodyPart();

            contentPart.setContent(MAIL_CONTENT, "text/html;charset=utf-8");
            multipart.addBodyPart(contentPart);

            if(null != attachment){
                BodyPart attachmentPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentPart.setDataHandler(new DataHandler(source));
                //避免中文乱码的处理
                attachmentPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
                multipart.addBodyPart(attachmentPart);
            }

            message.setContent(multipart);

            message.saveChanges();
            Transport.send(message);
        }catch(Exception e){
            logger.error("Exception occurs in EMAIL functionality. Error: "+e.getMessage());
        }
    }

}

class MailAuthenticator extends Authenticator{

    private String userName;
    private String password;

    public MailAuthenticator(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(userName, password);
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
