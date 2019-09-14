package com.base.controller;

import com.base.bean.Hr;
import com.base.bean.MsgContent;
import com.base.bean.ResultVO;
import com.base.bean.SysMsg;
import com.base.service.HrService;
import com.base.service.SysMsgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 处理通知消息的Controller
 * 登录即可访问
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    HrService hrService;
    @Autowired
    SysMsgService sysMsgService;

    @RequestMapping(value = "/hrs", method = RequestMethod.GET)
    public List<Hr> getAllHr() {
        return hrService.getAllHrExceptAdmin();
    }

    @RequiresPermissions("user:admin")
    @RequestMapping(value = "/nf", method = RequestMethod.POST)
    public ResultVO sendNf(MsgContent msg) {
        if (sysMsgService.sendMsg(msg)) {
            return ResultVO.success("发送成功!");
        }
        return ResultVO.error("发送失败!");
    }

    @RequestMapping("/sysmsgs")
    public List<SysMsg> getSysMsg(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return sysMsgService.getSysMsgByPage(page, size);
    }

    @RequestMapping(value = "/markread", method = RequestMethod.PUT)
    public ResultVO markRead(Long flag) {
        if (sysMsgService.markRead(flag)) {
            if (flag == -1) {
                return ResultVO.success("multiple");
            } else {
                return ResultVO.success("single");
            }
        } else {
            if (flag == -1) {
                return ResultVO.error("multiple");
            } else {
                return ResultVO.error("single");
            }
        }
    }
}
