package justin.spring.demo.action;

import justin.spring.annotation.JAutowired;
import justin.spring.annotation.JController;
import justin.spring.annotation.JRequestMapping;
import justin.spring.annotation.JRequestParam;
import justin.spring.demo.service.IModifyService;
import justin.spring.demo.service.IQueryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 公布接口url
 * @author Justin
 *
 */
@JController
@JRequestMapping("/web")
public class MyAction {

    @JAutowired
    IQueryService queryService;
    @JAutowired
    IModifyService modifyService;

    @JRequestMapping("/query.json")
    public void query(HttpServletRequest request, HttpServletResponse response,
                      @JRequestParam("name") String name){

        String result = queryService.query(name);
        out(response,result);
    }

    @JRequestMapping("/add*.json")
    public void add(HttpServletRequest request,HttpServletResponse response,
                    @JRequestParam("name") String name,@JRequestParam("addr") String addr){
        String result = modifyService.add(name,addr);
        out(response,result);
    }

    @JRequestMapping("/remove.json")
    public void remove(HttpServletRequest request,HttpServletResponse response,
                       @JRequestParam("id") Integer id){
        String result = modifyService.remove(id);
        out(response,result);
    }

    @JRequestMapping("/edit.json")
    public void edit(HttpServletRequest request,HttpServletResponse response,
                     @JRequestParam("id") Integer id,
                     @JRequestParam("name") String name){
        String result = modifyService.edit(id,name);
        out(response,result);
    }



    private void out(HttpServletResponse resp,String str){
        try {
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}