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


//    <!--    修改权限为教师所属-->
    @RequestMapping("/updatePerTea.do")
    @ResponseBody
    public JsonResult updatePerTea(Permission permission) {
        permissionService.updatePerTea(permission);
        return new JsonResult(1, null);
    }

//    <!--    修改权限为学生所属-->
    @RequestMapping("/updatePerStu.do")
    @ResponseBody
    public JsonResult updatePerStu(Permission permission) {
        permissionService.updatePerStu(permission);
        return new JsonResult(1, null);
    }

//     <!-- 修改 （删除）其权限( 隐藏为0不展示) -->
    @RequestMapping("/deletePer.do")
    @ResponseBody
    public JsonResult deletePer(Permission permission) {
        permissionService.deletePer(permission);
        return new JsonResult(1, null);
    }

//    <!--    查看parentId为0的 功能 为添加准备-->
    @RequestMapping("/findPerZero.do")
    @ResponseBody
    public JsonResult findPerZero(Permission permission) {
        List<Permission> perZero = permissionService.findPerZero(permission);
        return new JsonResult(1, perZero);
    }


}
