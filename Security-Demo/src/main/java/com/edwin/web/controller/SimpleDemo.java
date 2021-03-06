package com.edwin.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wenpuzhao on 2019/3/31.
 */
public class SimpleDemo {}
//
//        implements IMsgHandlerFace {
//
//    private Logger LOG = LoggerFactory.getLogger(getClass());
//    @Override
//    public String textMsgHandle(BaseMsg msg) {
//        // String docFilePath = "D:/itchat4j/pic/1.jpg"; // 这里是需要发送的文件的路径
//        if (!msg.isGroupMsg()) { // 群消息不处理
//            // String userId = msg.getString("FromUserName");
//            // MessageTools.sendFileMsgByUserId(userId, docFilePath); // 发送文件
//            // MessageTools.sendPicMsgByUserId(userId, docFilePath);
//            String text = msg.getText(); // 发送文本消息，也可调用MessageTools.sendFileMsgByUserId(userId,text);
//            LOG.info(text);
//            if (text.equals("111")) {
//                WechatTools.logout();
//            }
//            if (text.equals("222")) {
//                WechatTools.remarkNameByNickName("yaphone", "Hello");
//            }
//            if (text.equals("333")) { // 测试群列表
//                System.out.print(WechatTools.getGroupNickNameList());
//                System.out.print(WechatTools.getGroupIdList());
//                System.out.print(Core.getInstance().getGroupMemeberMap());
//            }
//            return text;
//        }
//        return null;
//    }
//
//    @Override
//    public String picMsgHandle(BaseMsg msg) {
//        String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());// 这里使用收到图片的时间作为文件名
//        String picPath = "/Users/wenpuzhao/Downloads/itchat4j/pic" + File.separator + fileName + ".jpg"; // 调用此方法来保存图片
//        DownloadTools.getDownloadFn(msg, MsgTypeEnum.PIC.getType(), picPath); // 保存图片的路径
//        return "图片保存成功";
//    }
//
//    @Override
//    public String voiceMsgHandle(BaseMsg msg) {
//        String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
//        String voicePath = "/Users/wenpuzhao/Downloads/itchat4j/voice" + File.separator + fileName + ".mp3";
//        DownloadTools.getDownloadFn(msg, MsgTypeEnum.VOICE.getType(), voicePath);
//        return "声音保存成功";
//    }
//
//    @Override
//    public String viedoMsgHandle(BaseMsg msg) {
//        String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
//        String viedoPath = "/Users/wenpuzhao/Downloads/itchat4j/viedo" + File.separator + fileName + ".mp4";
//        DownloadTools.getDownloadFn(msg, MsgTypeEnum.VIEDO.getType(), viedoPath);
//        return "视频保存成功";
//    }
//
//    @Override
//    public String nameCardMsgHandle(BaseMsg msg) {
//        return "收到名片消息";
//    }
//
//    @Override
//    public void sysMsgHandle(BaseMsg msg) { // 收到系统消息
//        String text = msg.getContent();
//        LOG.info(text);
//    }
//
//    @Override
//    public String verifyAddFriendMsgHandle(BaseMsg msg) {
//        MessageTools.addFriend(msg, true); // 同意好友请求，false为不接受好友请求
//        RecommendInfo recommendInfo = msg.getRecommendInfo();
//        String nickName = recommendInfo.getNickName();
//        String province = recommendInfo.getProvince();
//        String city = recommendInfo.getCity();
//        String text = "你好，来自" + province + city + "的" + nickName + "， 欢迎添加我为好友！";
//        return text;
//    }
//
//    @Override
//    public String mediaMsgHandle(BaseMsg msg) {
//        String fileName = msg.getFileName();
//        String filePath = "/Users/wenpuzhao/Downloads/itchat4j/file" + File.separator + fileName; // 这里是需要保存收到的文件路径，文件可以是任何格式如PDF，WORD，EXCEL等。
//        DownloadTools.getDownloadFn(msg, MsgTypeEnum.MEDIA.getType(), filePath);
//        return "文件" + fileName + "保存成功";
//    }
//
//}