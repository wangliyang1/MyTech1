package com.wd.tech.widget;

/**
 * date:2020/4/28 0028
 * author:王黎杨(Administrator)
 * function:
 */
public class Msg {
    public static final int TYPE_RECEIVED = 2;//左
    public static final int TYPE_SENT = 1;//右

    private static final class Holder{
        private static final Msg MSG=new Msg();
    }

    private Msg() {

    }

    public static Msg getInstance() {
        return Holder.MSG;
    }
    public Message getMessage(String content,String head,String name,int tag){
        Message message = new Message(content,head,name,tag);
        return message;
    }
    public class Message{
        private String content;
        private String head;
        private String name;
        private int type;

        public Message(String content, String head, String name, int type) {
            this.content = content;
            this.head = head;
            this.name = name;
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
