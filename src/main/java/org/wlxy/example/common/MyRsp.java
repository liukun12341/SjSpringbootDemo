package org.wlxy.example.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyRsp implements Serializable {
    private  int code;
    private  String msg;
    private Object content;

    public MyRsp(int code, String msg, Object content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    public MyRsp() {
    }

    public static  MyRsp wrapper(MyException ex){
        MyRsp myRsp = new MyRsp();
        myRsp.setCode(ex.getCode());
        myRsp.setMsg(ex.getMsg());
        myRsp.setContent(null);
        return  myRsp;
    }

    public  static MyRsp success(Object content){
        MyRsp myRsp = new MyRsp();
        myRsp.setCode(HttpCode.SUCCESS.getCode());
        myRsp.setMsg(HttpCode.SUCCESS.getMsg());
        myRsp.setContent(content);
        return  myRsp;
    }

    public  static MyRsp error(){
        MyRsp myRsp = new MyRsp();
        myRsp.setCode(HttpCode.ERROR.getCode());
        myRsp.setMsg(HttpCode.ERROR.getMsg());
        myRsp.setContent(null);
        return myRsp;
    }
    public MyRsp msg(String msg){
        this.msg=msg;
        return  this;
    }
}
