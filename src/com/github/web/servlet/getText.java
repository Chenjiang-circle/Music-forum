/**
 * FileName: getText
 * Author:   陈江超
 * Date:     2019/7/25 21:43
 * Description: 获取文章
 */
package com.github.web.servlet;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.domain.User;
import com.github.domain.collection;
import com.github.domain.text1;
import com.github.domain.text2;
import com.github.service.impl.TextServiceImpl;
import com.github.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/getText")
public class getText extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("usermsg");
        /*text.setUserid("1455075085@qq.com");
        text.setTime("2019-7-25");
        text.setTitle("hello world");
        text.setText("阿里巴巴集团主席兼首席执行官马云说：“永不抱怨的人生态度才是第一位的”。可我们许多人，却经常抱怨。面对工作，抱怨压力太大；面对家庭，抱怨烦恼太多；面对社会，抱怨世风日下；面对变化，抱怨命运不公。总之，他们总能找到抱怨的事，而且还觉得振振有词。偶然的抱怨属于情绪宣泄的一个出口，情有可原，倘可理解。但若不分时机，不讲场合，经常性的抱怨就是问题了。那是一种病态心理，一种不良的习惯，一种人生的负能量。一旦这种习惯养成，既伤害自己又影响别人。我们必须引起高度注意，自觉克服，远离抱怨，积极生活。\n" +
                "\n" +
                "　　经常抱怨的人之所以抱怨，其原因很多很复杂，但我以为主要有两个方面。一是不会积极的对待生活，二是不会正确的比较。\n" +
                "\n" +
                "　　不会积极的看待生活，主要是认知和心态出了问题。爱抱怨的人没有学会正确的看待事物，发现不了生活的美好，对自己拥有的不珍惜、不知足、不感恩。事物有自身的存在方式，其结果总是一定的，但我们对待事物的态度却是由我们的心态可以调整的。正如莎士比亚在《哈姆雷特》中所说的那样，“世上本无所谓好坏，皆思想所然”。当我们面对不快时，换一个角度看问题，结论就可能完全相反。\n" +
                "\n" +
                "　　不会正确的比较就是总爱用自己没有的去和人家有的比，拿自己的短处比人家的长处，越比越气，越比越恼，最后比出嫉妒羡慕恨。弄得自己长吁短叹，怨天忧人，总不开心。\n" +
                "\n" +
                "　　其实学会正确的比较是人生的一门艺术，是使生活变得快乐的一种方法。是一种达观的人生态度，甚至可以说，是一种人生的智慧。人，总是会与别人比得，这是人性使然。但怎么比却表现了驾驭生活能力的高低。\n" +
                "\n" +
                "　　抱怨根本的原因还是出在错误的比较上。正确的比较是人生快乐的源头，不正确地比较是人生悲剧的开始。\n" +
                "\n" +
                "　　美国第23届总统富兰克林•罗斯福是一位传奇人物，他是美国历史上任期最长的总统。他“把自己从轮椅上举起，把整个国家自屈服中解放”。他被历史学家和政治学家一致誉为与华盛顿、林肯并肩齐名的美国历史上最伟大的三位总统之一。他的很多故事流传久远。其中有一则家中被盗故事，对我们学会如何正确的比较很有启迪和帮助。\n" +
                "\n" +
                "　　相传，有一次罗斯福家被盗，损失惨重。一位朋友闻讯后，忙写信安慰他，罗斯福在回信中写道：“亲爱的朋友，谢谢你来信安慰我，我现在很好，感谢上帝：因为第一，贼偷去的是我的东西，而没有伤害我的生命；第二，贼只偷去了我部分东西，而不是全部；第三，最值得庆幸的是，做贼的是他，而不是我。”对任何一个人来说，被盗绝对是不幸的事，但罗斯福却从另一个角度，找到了不抱怨而是感恩的三条理由。\n" +
                "\n" +
                "　　作家史铁生，曾调侃自己的职业是生病，写作只是业余的。他写过一篇《生病的经验是一步步懂得满足》的文章。其中写道：“发烧了，才知道不发烧的日子多么清爽。咳嗽了，才体会到不咳嗽的嗓子是多么安详。刚坐上轮椅时，我老想，不能直立行走岂非把人的特点搞丢了？便觉得天昏地暗。等到又生出褥疮，一连数日只能歪七扭八地躺着，才看见端坐的日子其实多么晴朗。后来又患尿毒症，经常昏昏然不能思想，就更加怀恋起往日时光。终于醒悟：其实每时每刻我们都是幸运的，因为任何灾难的前面都可能再加一个‘更’字。”你的抱怨是因为你不知道不好的后面还有更坏的结果。一旦你明白了，你就不会抱怨的，只会庆幸当下的最好。人生无常，明天和意外谁先到来，谁也说不清楚。\n" +
                "\n" +
                "　　所以，俄国著名作家契诃夫教导我们说：“如果你手上扎了一根刺，那你应该高兴才对，幸亏不是扎在眼晴里。”我们应该学会正确地对待生活中的小不幸，学会正确的比较。当我们还在为丢了一双鞋而忧郁的时候，我们应该看到还有人没有脚。比起那个口里说着“春天来了，而我却看不到她”的沿街乞讨的盲人，进一步说比起那些失去了生命和自由的人，目前能这样健健康康、快快乐乐、自自由由地活在这个世界上，谁说不是一种命运的恩赐？我们还有什么理由抱怨呢？我们应当感恩才是。我们活着的这一天是多少人向往而不得的一天啊！\n" +
                "\n" +
                "　　当我们抱怨自己吃得不够好时，我们应该想想那些在垃圾中寻找食物的乞丐；当我们抱怨自己住的房子不够大时，我们应该去看看寒冷的街边无家可归的人；当我们抱怨妻子不够温柔，我们应该听听一位丈夫被妻子骂得猪狗不如；当我们抱怨丈夫不会赚钱的时，我们应知道还有人的丈夫躺在病床上；当我们抱怨自己的孩子成绩不好时，我们应想想还有好多人正为没有孩子发愁着呢；当我们抱怨自己的工作不如意时，我们应想想那些失业在家而没找到工作的人。\n" +
                "\n" +
                "　　只有这样比较，我们的幸福感才会油然而生，会心生感激。此刻，我们真的是幸福的，我们不应当抱怨，应该感恩才对。应该明白，我们的生活幸亏没有更坏，而世界上的任何事情其实都有更坏的可能。但是，我们真的很幸运，感谢命运，我们的生活是如此美好。心怀感恩，我们才会珍惜，充满感激，我们才会知足。\n" +
                "\n" +
                "　　常怀感恩之心，我们才会有报恩之心；常怀感恩之心，我们才会忽略生活中的那些不尽人意；常怀感恩之心，我们才会自责自己内心的狭隘，忏悔自己的过错；常怀感恩之心，我们才会原谅那些生活中那些与我们发生了不快的人；常怀感恩之心，我们才会知足的活着不抱怨。\n" +
                "\n" +
                "　　我们可以追求更好的生活，但不可以抱怨生活，我们可以更加积极努力让生活更好，但唯独不能抱怨生活。因为，我们是幸运的，对于幸运的人，还有什么理由抱怨呢？\n" +
                "\n" +
                "　　抱怨不好是因为不知道还有更坏。");
        text.setCollection(12);
        text.setComment(32);
        text.setLikes(1000);
        text.setType("摇滚");
        text.setTextid(99);
        text.setUsername("圈圈");*/
        if(user != null) {
//        int textid = Integer.parseInt(req.getParameter("textid"));
            TextServiceImpl textService = new TextServiceImpl();
            UserServiceImpl userService = new UserServiceImpl();
            text2 text = textService.findAlltext(1);
            collection collection = new collection();
//        String userid = req.getParameter("userid");
//        String collectiontextid = req.getParameter("collectiontextid");
            Map<String, String[]> map = req.getParameterMap();
            try {
                BeanUtils.populate(collection, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            String s = JSON.toJSONString(text);
            System.out.println(s);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(), text);
            mapper.writeValue(resp.getWriter(), user);
            PrintWriter out = resp.getWriter();
            out.println("{\"ifColl\":" + userService.isCollection(collection) + "}");
            System.out.println("{\"ifColl\":" + userService.isCollection(collection) + "}");
            out.flush();
            out.close();
//        JSON.writeJSONString(resp.getWriter(), text);
        }else{
            //木有登录
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
