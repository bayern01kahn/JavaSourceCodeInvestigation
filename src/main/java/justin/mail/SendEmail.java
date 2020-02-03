package justin.mail;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;


/**
 *JavaMail发送邮件:前提是QQ邮箱里帐号设置要开启POP3/SMTP协议
 */
public class SendEmail {

    public static void main(String[] args) throws Exception {
        //创建Properties对象
        Properties prop = new Properties();
        // 开启debug调试，以便在控制台查看
        prop.setProperty("mail.debug", "true");
        // 设置邮件服务器主机名为QQ邮箱的服务器主机名
        prop.setProperty("mail.host", "smtp.exmail.qq.com");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 设置发送邮件协议名称为SMTP(Simple Mail Transfer Protocol)
        prop.setProperty("mail.transport.protocol", "smtp");

        // 开启SSL加密，否则会失败
//        MailSSLSocketFactory sf = new MailSSLSocketFactory();
//        sf.setTrustAllHosts(true);
//        prop.put("mail.smtp.ssl.enable", "true");
//        prop.put("mail.smtp.ssl.socketFactory", sf);

        // 创建session
        //Session session = Session.getInstance(prop);

        //授权码 在 邮箱-设置-账户-生成授权码
//        Session session = Session.getDefaultInstance(prop, new Authenticator() {
//            public PasswordAuthentication getPasswordAuthentication() {
//                //发件人邮件用户名、授权码
//                return new PasswordAuthentication("155234557@qq.com",
//                        "ktrmwjjgorbpbjdd");
//            }
//        });

        Session session = Session.getInstance(prop);

        // 通过session得到transport对象
        Transport ts = session.getTransport();
        // 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
        //ts.connect();
        ts.connect("smtp.exmail.qq.com","ptc.res@ptctravel.net.cn", "0ry8b4M5uU");
        // 创建邮件
        Message message1 = createSimpleMail(session);
        //Message message2 = createMailWithHtml(session);
        //Message message3 = createMailWithResource(session);

        // 发送邮件
        ts.sendMessage(message1, message1.getAllRecipients());
        //ts.sendMessage(message2, message2.getAllRecipients());
        //ts.sendMessage(message3, message3.getAllRecipients());
        ts.close();
    }



    /**
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     */
    public static MimeMessage createSimpleMail(Session session)
            throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        /*如果你想发送一封e-mail给多个收件人，那么使用下面的方法来指定多个收件人ID：
            void addRecipients(Message.RecipientType type,
                   Address[] addresses) throws MessagingException
          type:要被设置为 TO, CC 或者 BCC，这里 CC 代表抄送、BCC 代表秘密抄送。
          举例：Message.RecipientType.TO
          addresses: 这是 email ID 的数组。在指定电子邮件 ID 时，你将需要使用 InternetAddress() 方法。
        */
        message.setFrom(new InternetAddress("ptc.res@ptctravel.net.cn"));
        // 指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("155234557@qq.com"));
        // 邮件的标题

        message.addRecipients(Message.RecipientType.BCC, new InternetAddress("test4new@163.com").getAddress());

        message.setSubject("JavaMail发送简单文本邮件");
        // 邮件的文本内容
        message.setContent("这是一封JavaMail发送的简单文本邮件！", "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }

    /**
     * @Method: createMailWithHtml
     * @Description: 创建一封HTML邮件
     */
    public static MimeMessage createMailWithHtml(Session session)
            throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("155234557@qq.com"));
        // 指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("155234557@qq.com"));
        // 邮件的标题
        message.setSubject("JavaMail发送HTML邮件");

        StringBuffer sb = new StringBuffer();
        sb.append("<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <div class=\"FoxDiv20191218150053145719\"> \n" +
                "   <div>\n" +
                "    <span></span>\n" +
                "    <div>\n" +
                "     Dear Res. Team, \n" +
                "    </div>\n" +
                "    <div>\n" +
                "     <br />\n" +
                "    </div>\n" +
                "    <div>\n" +
                "     Greeting from PTC Express Travel. \n" +
                "    </div>\n" +
                "    <div>\n" +
                "     <br />\n" +
                "    </div>\n" +
                "    <div>\n" +
                "     Kindly help with below booking request and revert us the confirmation No. at your earliest convenience. \n" +
                "    </div>\n" +
                "    <div>\n" +
                "     <br />\n" +
                "    </div>\n" +
                "   </div>\n" +
                "   <div>\n" +
                "    <table border=\"0\" cellpadding=\"4px\" cellspacing=\"0\" width=\"100%\" style=\"font-family: Tahoma, Arial, 'Microsoft YaHei', 'Hiragino Sans GB', SimSun, Verdana, sans-serif; color: rgb(0, 0, 0); font-size: 14px; font-variant-ligatures: normal; orphans: 2; widows: 2;\">\n" +
                "     <tbody>\n" +
                "      <tr>\n" +
                "       <td width=\"10%\"><b>To:</b></td>\n" +
                "       <td colspan=\"3\"><b>The Star Grand at Gold Coast</b></td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "       <td width=\"10%\">Attn:</td>\n" +
                "       <td width=\"50%\">Reservation</td>\n" +
                "       <td width=\"10%\">From:</td>\n" +
                "       <td>Eva</td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "       <td width=\"10%\">For:</td>\n" +
                "       <td colspan=\"2\">The Star Grand at Gold Coast</td>\n" +
                "       <td></td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "       <td width=\"10%\">Add:</td>\n" +
                "       <td colspan=\"2\">1 Casino Dr, Broadbeach QLD 4218, Gold Coast, Queensland</td>\n" +
                "       <td></td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "       <td width=\"10%\">Tel:</td>\n" +
                "       <td width=\"50%\">07 5592 8100</td>\n" +
                "       <td width=\"10%\">Date:</td>\n" +
                "       <td>16/12/2019</td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "       <td width=\"10%\">Fax:</td>\n" +
                "       <td colspan=\"3\">07 5592 8219</td>\n" +
                "      </tr>\n" +
                "     </tbody>\n" +
                "    </table>\n" +
                "    <table border=\"0\" cellpadding=\"4px\" cellspacing=\"0\" width=\"100%\" style=\"font-family: Tahoma, Arial, 'Microsoft YaHei', 'Hiragino Sans GB', SimSun, Verdana, sans-serif; color: rgb(0, 0, 0); font-size: 14px; font-variant-ligatures: normal; orphans: 2; widows: 2;\">\n" +
                "     <tbody>\n" +
                "      <tr>\n" +
                "       <td width=\"15%\" style=\"height: 12mm; line-height: 10mm; border-top-width: 1px; border-top-style: solid; border-top-color: rgb(51, 51, 51);\"><span style=\"font-size: 16px; font-weight: 700;\">RE:</span></td>\n" +
                "       <td align=\"left\" style=\"height: 12mm; line-height: 10mm; border-top-width: 1px; border-top-style: solid; border-top-color: rgb(51, 51, 51);\"><span style=\"font-size: 16px; font-weight: 700;\">PTCFITA54427 &nbsp;&nbsp; &nbsp; New-Booking</span></td>\n" +
                "      </tr>\n" +
                "     </tbody>\n" +
                "    </table>\n" +
                "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"5mm\" width=\"100%\" style=\"font-family: Tahoma, Arial, 'Microsoft YaHei', 'Hiragino Sans GB', SimSun, Verdana, sans-serif; color: rgb(0, 0, 0); font-size: 14px; font-variant-ligatures: normal; orphans: 2; widows: 2;\">\n" +
                "     <tbody>\n" +
                "      <tr data-tran-id=\"1606973\">\n" +
                "       <td width=\"18%\">Confirmation NO:</td>\n" +
                "       <td>&nbsp;</td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "       <td>TourLeader:</td>\n" +
                "       <td></td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "       <td>IN:</td>\n" +
                "       <td>Fri, 10 JAN 2020&nbsp;&nbsp;&nbsp; ETA: </td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "       <td>OUT:</td>\n" +
                "       <td>Sun, 12 JAN 2020&nbsp;&nbsp;&nbsp; ETD:10:00</td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "       <td>Service:</td>\n" +
                "       <td>Room only-Superior Deluxe Room for 2 nights</td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "       <td>Pax No:</td>\n" +
                "       <td>\n" +
                "        <table width=\"100%\" class=\"detail-num-table\">\n" +
                "         <tbody>\n" +
                "          <tr>\n" +
                "           <td style=\"font-size: 14px;\"><span style=\"padding: 0px 2mm;\">2 </span>ADT</td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table></td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "       <td>RoomType:</td>\n" +
                "       <td>\n" +
                "        <table width=\"100%\" class=\"detail-num-table\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"font-size: 14px;\">\n" +
                "           <td><span style=\"padding: 0px 2mm;\">1 </span>DBL</td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table></td>\n" +
                "      </tr>\n" +
                "     </tbody>\n" +
                "    </table>\n" +
                "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"font-family: Tahoma, Arial, 'Microsoft YaHei', 'Hiragino Sans GB', SimSun, Verdana, sans-serif; color: rgb(0, 0, 0); font-size: 14px; font-variant-ligatures: normal; orphans: 2; widows: 2;\">\n" +
                "     <tbody>\n" +
                "      <tr>\n" +
                "       <td><span style=\"font-size: 16px; line-height: 32px; font-weight: bold;\">Special Instruction:</span><b><pre style=\"margin-top: 3px; margin-bottom: 3px;\">For allotment with room only rate 268AUD\n" +
                "                        </pre></b></td>\n" +
                "      </tr>\n" +
                "     </tbody>\n" +
                "    </table>\n" +
                "    <div style=\"font-family: Tahoma, Arial, 'Microsoft YaHei', 'Hiragino Sans GB', SimSun, Verdana, sans-serif; color: rgb(0, 0, 0); font-variant-ligatures: normal; orphans: 2; widows: 2; height: 5mm;\"></div>\n" +
                "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"font-family: Tahoma, Arial, 'Microsoft YaHei', 'Hiragino Sans GB', SimSun, Verdana, sans-serif; color: rgb(0, 0, 0); font-size: 14px; font-variant-ligatures: normal; orphans: 2; widows: 2;\">\n" +
                "     <tbody>\n" +
                "      <tr>\n" +
                "       <td><span style=\"font-size: 16px; line-height: 32px; font-weight: bold;\">Rooming List</span></td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "       <td>\n" +
                "        <table border=\"1\" width=\"100%\" cellspacing=\"0\" cellpadding=\"3px\" style=\"border-collapse: collapse;\">\n" +
                "         <tbody>\n" +
                "          <tr>\n" +
                "           <td width=\"8%\" style=\"font-size: 14px;\">1</td>\n" +
                "           <td width=\"12%\" style=\"font-size: 14px;\">DBL</td>\n" +
                "           <td width=\"20%\" style=\"font-size: 14px;\">MANFIELD/WILLIAM</td>\n" +
                "           <td width=\"20%\" style=\"font-size: 14px;\">TBA</td>\n" +
                "           <td width=\"20%\" style=\"font-size: 14px;\">&nbsp;</td>\n" +
                "           <td width=\"20%\" style=\"font-size: 14px;\">&nbsp;</td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table></td>\n" +
                "      </tr>\n" +
                "     </tbody>\n" +
                "    </table>\n" +
                "    <div class=\"content-edit-wrap content-edit-border-fff\" style=\"font-family: Tahoma, Arial, 'Microsoft YaHei', 'Hiragino Sans GB', SimSun, Verdana, sans-serif; font-size: 16px; border: 1px solid rgb(238, 238, 238); padding: 6px; margin: 8px auto; font-weight: bold; line-height: 19.2px; color: rgb(0, 0, 0); font-variant-ligatures: normal; orphans: 2; widows: 2;\"></div>\n" +
                "    <p style=\"font-family: Tahoma, Arial, 'Microsoft YaHei', 'Hiragino Sans GB', SimSun, Verdana, sans-serif; color: rgb(0, 0, 0); font-variant-ligatures: normal; orphans: 2; widows: 2; line-height: 14px; margin-top: 0px; margin-bottom: 0px;\"><b>Appreciate your confirmation of above new booking at your earliest convenience.</b></p>\n" +
                "    <p style=\"font-family: Tahoma, Arial, 'Microsoft YaHei', 'Hiragino Sans GB', SimSun, Verdana, sans-serif; color: rgb(0, 0, 0); font-variant-ligatures: normal; orphans: 2; widows: 2; line-height: 14px; margin-top: 0px; margin-bottom: 0px;\">Thanks and Regards</p>\n" +
                "   </div> \n" +
                "   <div>\n" +
                "    <br />\n" +
                "   </div>\n" +
                "   <hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\" /> \n" +
                "   <div>\n" +
                "    <span>\n" +
                "     <div style=\"MARGIN: 10px; FONT-FAMILY: verdana; FONT-SIZE: 10pt\">\n" +
                "      <div>\n" +
                "       Best Regards\n" +
                "      </div>\n" +
                "      <div>\n" +
                "       Eva\n" +
                "      </div>\n" +
                "      <div>\n" +
                "       <a href=\"mailto:ptc.res@ptctravel.net.cn\" rel=\"noopener\" target=\"_blank\">ptc.res@ptct<wbr />ravel.net.cn</a>\n" +
                "      </div>\n" +
                "     </div></span>\n" +
                "   </div> \n" +
                "  </div>\n" +
                " </body>\n" +
                "</html>");



        // 邮件的文本内容
        message.setContent(sb.toString(), "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }

    /**
     * @Method: createMailWithResource
     * @Description: 创建一封包含附件的邮件
     */
    public static MimeMessage createMailWithResource(Session session)
            throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("155234557@qq.com"));
        // 指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("155234557@qq.com"));
        // 邮件的标题
        message.setSubject("JavaMail发送带附件的邮件");
        // 创建消息部分
        BodyPart messageBodyPart = new MimeBodyPart();

        // 消息
        messageBodyPart.setText("这是一封JavaMail发送的带附件的邮件！");

        // 创建多重消息
        Multipart multipart = new MimeMultipart();

        // 设置文本消息部分
        multipart.addBodyPart(messageBodyPart);

        // 附件部分
        messageBodyPart = new MimeBodyPart();
        String filename = "./resource/img.jpg";
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);

        // 发送完整消息
        message.setContent(multipart);
        return message;
    }
}

