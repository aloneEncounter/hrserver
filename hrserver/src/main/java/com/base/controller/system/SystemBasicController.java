package com.base.controller.system;

import com.base.bean.*;
import com.base.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/basic")
public class SystemBasicController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    @Autowired
    MenuRoleService menuRoleService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PositionService positionService;
    @Autowired
    JobLevelService jobLevelService;

    @RequestMapping(value = "/role/{rid}", method = RequestMethod.DELETE)
    public ResultVO deleteRole(@PathVariable Long rid) {
        if (roleService.deleteRoleById(rid) == 1) {
            return ResultVO.success("删除成功!");
        }
        return ResultVO.error("删除失败!");
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public ResultVO addNewRole(String role, String roleZh) {
        if (roleService.addNewRole(role, roleZh) == 1) {
            return ResultVO.success("添加成功!");
        }
        return ResultVO.error("添加失败!");
    }

    @RequestMapping(value = "/menuTree/{rid}", method = RequestMethod.GET)
    public Map<String, Object> menuTree(@PathVariable Long rid) {
        Map<String, Object> map = new HashMap<>();
        List<Menu> menus = menuService.menuTree();
        map.put("menus", menus);
        List<Long> selMids = menuService.getMenusByRid(rid);
        map.put("mids", selMids);
        return map;
    }

    @RequestMapping(value = "/updateMenuRole", method = RequestMethod.PUT)
    public ResultVO updateMenuRole(Long rid, Long[] mids) {
        if (menuRoleService.updateMenuRole(rid, mids) == mids.length) {
            return ResultVO.success("更新成功!");
        }
        return ResultVO.error("更新失败!");
    }

    @RequestMapping("/roles")
    public List<Role> allRoles() {
        return roleService.roles();
    }

    @RequestMapping(value = "/dep", method = RequestMethod.POST)
    public Map<String, Object> addDep(Department department) {
        Map<String, Object> map = new HashMap<>();
        if (departmentService.addDep(department) == 1) {
            map.put("status", "success");
            map.put("msg", department);
            return map;
        }
        map.put("status", "error");
        map.put("msg", "添加失败!");
        return map;
    }

    @RequestMapping(value = "/dep/{did}", method = RequestMethod.DELETE)
    public ResultVO deleteDep(@PathVariable Long did) {
        if (departmentService.deleteDep(did) == 1) {
            return ResultVO.success("删除成功!");
        }
        return ResultVO.error("删除失败!");
    }

    @RequestMapping(value = "/dep/{pid}", method = RequestMethod.GET)
    public List<Department> getDepByPid(@PathVariable Long pid) {
        return departmentService.getDepByPid(pid);
    }

    @RequestMapping(value = "/deps", method = RequestMethod.GET)
    public List<Department> getAllDeps() {
        return departmentService.getAllDeps();
    }

    @RequestMapping(value = "/position", method = RequestMethod.POST)
    public ResultVO addPos(Position pos) {
        int result = positionService.addPos(pos);
        if (result == 1) {
            return ResultVO.success("添加成功!");
        } else if (result == -1) {
            return ResultVO.error("职位名重复，添加失败!");
        }
        return ResultVO.error("添加失败!");
    }

    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    public List<Position> getAllPos() {
        return positionService.getAllPos();
    }

    @RequestMapping("/position/{pids}")
    public ResultVO deletePosById(@PathVariable String pids) {
        if (positionService.deletePosById(pids)) {
            return ResultVO.success("删除成功!");
        }
        return ResultVO.error("删除失败!");
    }

    @RequestMapping(value = "/position", method = RequestMethod.PUT)
    public ResultVO updatePosById(Position position) {
        if (positionService.updatePosById(position) == 1) {
            return ResultVO.success("修改成功!");
        }
        return ResultVO.error("修改失败!");
    }

    @RequestMapping(value = "/joblevel", method = RequestMethod.POST)
    public ResultVO addJobLevel(JobLevel jobLevel) {
        int result = jobLevelService.addJobLevel(jobLevel);
        if (result == 1) {
            return ResultVO.success("添加成功!");
        } else if (result == -1) {
            return ResultVO.error("职称名重复，添加失败!");
        }
        return ResultVO.error("添加失败!");
    }

    @RequestMapping(value = "/joblevels", method = RequestMethod.GET)
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    @RequestMapping(value = "/joblevel/{ids}", method = RequestMethod.DELETE)
    public ResultVO deleteJobLevelById(@PathVariable String ids) {
        if (jobLevelService.deleteJobLevelById(ids)) {
            return ResultVO.success("删除成功!");
        }
        return ResultVO.error("删除失败!");
    }

    @RequestMapping(value = "/joblevel", method = RequestMethod.PUT)
    public ResultVO updateJobLevel(JobLevel jobLevel) {
        if (jobLevelService.updateJobLevel(jobLevel) == 1) {
            return ResultVO.success("修改成功!");
        }
        return ResultVO.error("修改失败!");
    }
}
