package com.qfedu.examsys.aftercontroller;

import com.qfedu.examsys.pojo.JsonResult;
import com.qfedu.examsys.pojo.Permission;
import com.qfedu.examsys.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author Song
 * @Date 2019/9/48:56
 **/
@Controller
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @CrossOrigin
    @RequestMapping("/findPermission.do")
    @ResponseBody
    public JsonResult findPermission(Integer rid){
        List<Permission> permissionList = rolePermissionService.findPermission( rid );
        return new JsonResult( 1,permissionList );
    }

    @CrossOrigin
    @RequestMapping("/findPermission2.do")
    @ResponseBody
    public JsonResult findPermission2(Integer rid){
        List<Permission> permissionList2 = rolePermissionService.findPermission2( rid );
        return new JsonResult( 1,permissionList2 );
    }

    @RequestMapping("/findAlls.do")
    @ResponseBody
    public JsonResult findAlls(Permission permission){
        List<Permission> alls = rolePermissionService.findAlls(permission);
        return new JsonResult(1, alls);
    }

}
