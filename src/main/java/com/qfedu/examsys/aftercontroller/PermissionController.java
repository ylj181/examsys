package com.qfedu.examsys.aftercontroller;

import com.qfedu.examsys.pojo.JsonResult;
import com.qfedu.examsys.pojo.Permission;
import com.qfedu.examsys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author Song
 * @Date 2019/9/515:29
 **/
@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @CrossOrigin
    @RequestMapping("/findByIdPermission.do")
    @ResponseBody
    public JsonResult findByIdPermission(Integer id){
        Permission permission = permissionService.findByIdPermission( id );
        return new JsonResult( 1,permission );
    }

    @CrossOrigin
    @RequestMapping("/findByIdPermission2.do")
    @ResponseBody
    public JsonResult findByIdPermission2(Integer parentId){
        List<Permission> permissionList = permissionService.findByIdPermission2( parentId );
        return new JsonResult( 1,permissionList );
    }
}
