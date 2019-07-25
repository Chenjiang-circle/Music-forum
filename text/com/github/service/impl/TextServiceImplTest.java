package com.github.service.impl;

import com.github.dao.TextDao;
import com.github.dao.impl.TextDaoImpl;
import com.github.domain.Text;
import com.github.domain.comment;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextServiceImplTest {

    @Test
    public void createText() {
        Text text = new Text();
        text.setUserid("1455075085@qq.com");
        text.setText("文章内容，错过的人生哲理文章\n" +
                "　　水暖水寒鱼自知，花开花谢春不管。夏天来了，我们才想起了采撷花朵，可是我们已经错过了花期；汽车开动了，我们才姗姗来迟；当那个朝思暮想的人就站在面前的时候，我们却茫然地让其擦肩而过……\n" +
                "\n" +
                "　　错过了花期，错过了出发，错过了佳人……人生中该有多少错过啊！\n" +
                "\n" +
                "　　说好了要珍惜，明知道要把握，为什么还要一而再，再而三地错过呢？人生越长，错过的越多，人生真是一部遗憾的连续剧。然而，我们却在错过中得到了砥砺，学会了成长，谁能拒绝错过呢！谁说错过的就百无一用呢？没有错过，或许我们不懂得珍惜；没有错过，或许我们就让生命轻而易举地从自己手里溜过……我们不想错过，那是因为我们曾经错过。\n" +
                "\n" +
                "　　错过的感受是遗憾的、痛苦的，然而错过的教训是我们值得铭记，值得吸取的，这或许就是人们老是不想犯错却又常常犯错的原因吧！\n" +
                "\n" +
                "　　人就是这样，错在别人身上，是绝不会痛在自己心里的。对于他人的痛，我们常常蜻蜓点水地劝解，只有自己品尝过了，才有了切肤之痛，每个错过了什么的人，大概都有这样的感觉吧！\n" +
                "\n" +
                "　　在成长的过程中，我们需要他人的帮助，但谁都不可能帮助我们一生，我们自己的人生，还需要我们自己去经营，从哪里跌下去，就从哪里爬起来。错过带给了我们伤痛，但错过又给予了我们成熟，人世间的事情 ，有什么对错可言呢！\n" +
                "\n" +
                "　　错过不等于永远地失去，错过了星星，还有月亮，甚至是太阳；错过了春花，还有夏荷、秋菊，甚至是冬梅；错过了青涩的童年、少年，我们还有日趋成熟的青年、中年，甚至是老年！古人曰：“失之东隅，收之桑榆”。谁能说错过就是不堪回首的呢？\n" +
                "\n" +
                "　　积极是我们人生不变的态度。不想错过，但难免错过；允许错过，但不允许同样的错误一犯再犯。马尚有失蹄的时候，人又岂能无错呢！错过的不一定是最好的，最好的不该让其错过。\n" +
                "\n" +
                "　　常常，我们会为错过而痛心疾首；常常，我们会因错过而耿耿于怀，可这些又有什么用呢？既然错过是难免的，就让我们把错过降到最低吧！\n" +
                "\n" +
                "　　错过，是一首萨克斯，让人忧伤而怀念！你错过的人和事，别人才有机会遇见；别人错过了，你才有机会拥有。人人都会错过，真正属于你的，永远不会错过。就像美国作家简?吉尔伯逊说的：\n" +
                "\n" +
                "　　如果你渴望得到某样东西\n" +
                "\n" +
                "　　你就必须给它以自由\n" +
                "\n" +
                "　　如果它回来了\n" +
                "\n" +
                "　　它就是你的\n" +
                "\n" +
                "　　如果它没回来\n" +
                "\n" +
                "　　无论怎样你都永远不会真正得到它。");
        text.setTitle("错过的人生");
        text.setTime("2019-7-25");
        TextDao textDao = new TextDaoImpl();
        textDao.createText(text);
        System.out.println("添加文章成功");
    }

    @Test
    public void addComment() {
        TextDao textDao = new TextDaoImpl();
        comment comments = new comment();
        comments.setTextid(25);
        comments.setUserid("1455075085@qq.com");
        comments.setTime("2019-8-5");
        comments.setText("3楼评论2楼1");
        int commentid = textDao.addCommentToText(comments);
        if (commentid != -1){
            Boolean aBoolean = textDao.addComment(comments, commentid);
            System.out.println(aBoolean);
        }else {
            System.out.println("存储评论到数据库错误，报错点（TextServiceImpl-->addComment）");
        }
    }
}