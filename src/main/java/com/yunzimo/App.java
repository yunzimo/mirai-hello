package com.yunzimo;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.Face;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;
import net.mamoe.mirai.utils.BotConfiguration;

/**
 * Hello world!
 *
 */
public class App 
{

    //发给bot会回复你的信息
    public static void afterLogin(Bot bot) {
        String yourQQNumber = "你的QQ号";
        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, (event) -> {
            if (yourQQNumber.equals(String.valueOf(event.getSender().getId()))) {
                event.getSubject().sendMessage(new MessageChainBuilder()
                        .append(new QuoteReply(event.getMessage()))
                        .append("Hi, you just said: '")
                        .append(event.getMessage())
                        .append("'")
                        .append(new Face(Face.BAI_TUO))
                        .build()
                );
            }
        });
    }
}

class WithConfiguration1 {
    public static void main(String[] args) {
        //使用自定义配置登录bot
        Bot bot = BotFactory.INSTANCE.newBot(123123, "passwd", new BotConfiguration() {{
            fileBasedDeviceInfo(); // 使用 device.json 存储设备信息
            setProtocol(MiraiProtocol.ANDROID_PAD); // 切换协议
        }});
        bot.login();

        App.afterLogin(bot);
    }
}

