package com.lyn.eshop.auth.controller;

import com.lyn.eshop.auth.composite.Priority;
import com.lyn.eshop.auth.domain.PriorityDO;
import com.lyn.eshop.auth.domain.PriorityDTO;
import com.lyn.eshop.auth.domain.PriorityVO;
import com.lyn.eshop.auth.service.PriorityService;
import com.lyn.eshop.common.util.BeanCopierUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-08 22:36
 **/
@RestController
@RequestMapping("/auth/priority")
public class PriorityController {

    private static final Logger logger = LoggerFactory.getLogger(PriorityController.class);

    @Autowired
    private PriorityService priorityService;


    /**
     * 查询根权限
     * @return
     */
    @GetMapping("/root")
    public List<PriorityVO> listRootPriorities(){
        priorityService.listRootPriorities();
        return null;
    }

    /**
     * 查询子权限
     * @return
     */
    @GetMapping("/child{parentId}")
    public List<PriorityVO> listChildPriorities(@PathVariable("parentId") Long parentId){
        List<PriorityDTO> priorityDTOList = priorityService.listChildPriorities(parentId);
        List<PriorityVO> list = new ArrayList<>();
        for (PriorityDTO priorityDTO : priorityDTOList) {
            PriorityVO priorityVO = new PriorityVO();
            BeanCopierUtils.copyProperties(priorityDTO, priorityVO);
            list.add(priorityVO);
        }
        return list;
    }

    @GetMapping("/getPriorityById/{id}")
    public PriorityVO getPriorityById(@PathVariable("id") Long id){
        PriorityDTO priorityDTO = priorityService.getPriorityById(id);
        if(priorityDTO == null)
            return null;
        try {
            PriorityVO priorityVO = new PriorityVO();
            BeanCopierUtils.copyProperties(priorityDTO, priorityVO);
            return priorityVO;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    @PutMapping("/savePriority")
    public Boolean savePriority(@RequestBody PriorityVO priorityVO){
        try {
            PriorityDTO priorityDTO = new PriorityDTO();
            BeanCopierUtils.copyProperties(priorityVO, priorityDTO);
            priorityService.savePriority(priorityDTO);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * 查询账号被授权的权限树
     * @param accountId 账号id
     * @return 权限树
     */
//    @GetMapping("/authorized/tree/{accountId}")
//    public List<Priority> listAuthorizedTree(
//            @PathVariable("accountId") Long accountId) {
//        try {
//            return priorityService.listAuthorizedByAccountId(accountId);
//        } catch (Exception e) {
//            logger.error("error", e);
//            return new ArrayList<Priority>();
//        }
//    }

    /**
     * 判断账号对指定编号的权限是否有授权记录
     * @param accountId 账号id
     */
    @GetMapping("/authorized/code/{accountId}")
    public Boolean existAuthorizedByCode(
            @PathVariable("accountId") Long accountId,
            String code) {
        try {
            return priorityService.existAuthorizedByCode(accountId, code);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }

    /**
     * 判断账号对指定url的权限是否有授权记录
     * @param accountId 账号id
     */
    @GetMapping("/authorized/url/{accountId}")
    public Boolean existAuthorizedByUrl(
            @PathVariable("accountId") Long accountId,
            String url) {
        try {
            return priorityService.existAuthorizedByUrl(accountId, url);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
    }



    @PutMapping("/{id}")
    public Boolean updatePriority(@RequestBody PriorityVO priorityVO){
        try {
            PriorityDTO priorityDTO = new PriorityDTO();
            BeanCopierUtils.copyProperties(priorityVO, priorityDTO);
            priorityService.updatePriority(priorityDTO);
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        try {
            return priorityService.removePriority(id);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }


}
