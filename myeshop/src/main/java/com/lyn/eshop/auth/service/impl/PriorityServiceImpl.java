package com.lyn.eshop.auth.service.impl;

import com.lyn.eshop.auth.composite.Priority;
import com.lyn.eshop.auth.dao.AccountPriorityRelationshipDAO;
import com.lyn.eshop.auth.dao.PriorityDAO;
import com.lyn.eshop.auth.dao.RolePriorityRelationshipDAO;
import com.lyn.eshop.auth.domain.PriorityDO;
import com.lyn.eshop.auth.domain.PriorityDTO;
import com.lyn.eshop.auth.service.PriorityService;
import com.lyn.eshop.auth.service.RelatedCheckPriorityOperation;
import com.lyn.eshop.auth.service.RemovePriorityOperation;
import com.lyn.eshop.common.bean.SpringApplicationContext;
import com.lyn.eshop.common.util.BeanCopierUtils;
import com.lyn.eshop.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @program: projects
 * @author: lyn
 * * @create: 2021-05-08 22:35
 **/
@Service
public class PriorityServiceImpl implements PriorityService {

    private static final Logger logger = LoggerFactory.getLogger(PriorityServiceImpl.class);

    @Autowired
    private PriorityDAO priorityDAO;

    @Autowired
    private AccountPriorityRelationshipDAO accountPriorityRelationshipDAO;

    @Autowired
    private RolePriorityRelationshipDAO rolePriorityRelationshipDAO;

    @Autowired
    private SpringApplicationContext springApplicationContext;


    @Override
    public List<PriorityDTO> listRootPriorities() {
        List<PriorityDO> priorityDOS = priorityDAO.listRootPriorities();
        if(priorityDOS == null)
            return null;
        try{
            List<PriorityDTO> list = new ArrayList<>();
            for (PriorityDO priorityDO : priorityDOS) {
                PriorityDTO priorityDTO = new PriorityDTO();
                BeanCopierUtils.copyProperties(priorityDO, priorityDTO);
                list.add(priorityDTO);
            }
            return list;
        }catch(Exception e){
            logger.error("error", e);
        }
        return null;
    }

    public List<PriorityDTO> listChildPriorities(Long parentId){
        List<PriorityDO> priorityDOS = priorityDAO.listChildPriorities(parentId);
        if(priorityDOS == null)
            return null;
        try{
            List<PriorityDTO> list = new ArrayList<>();
            for (PriorityDO priorityDO : priorityDOS) {
                PriorityDTO priorityDTO = new PriorityDTO();
                BeanCopierUtils.copyProperties(priorityDO, priorityDTO);
                list.add(priorityDTO);
            }
            return list;
        }catch(Exception e){
            logger.error("error", e);
        }
        return null;
    }

    public PriorityDTO getPriorityById(Long id){
        PriorityDO priorityById = priorityDAO.getPriorityById(id);
        if (priorityById == null)
            return null;
        try {
            PriorityDTO priorityDTO = new PriorityDTO();
            BeanCopierUtils.copyProperties(priorityById, priorityDTO);
            return priorityDTO;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    public Boolean savePriority(PriorityDTO priorityDTO){
        try {
            PriorityDO priorityDO = new PriorityDO();
            BeanCopierUtils.copyProperties(priorityDTO, priorityDO);
            priorityDAO.savePriority(priorityDO);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    public Boolean updatePriority(PriorityDTO priorityDTO){
        try {
            PriorityDO priorityDO = new PriorityDO();
            BeanCopierUtils.copyProperties(priorityDTO, priorityDO);
            return true;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    /**
     * ????????????
     * @param id
     * @return
     */
    public Boolean removePriority(Long id){
        try {
            PriorityDO priorityDO = priorityDAO.getPriorityById(id);
            Priority priorityNode = new Priority();
            BeanCopierUtils.copyProperties(priorityDO, priorityNode);

            // ?????????????????????????????????????????????????????????????????????????????????????????????
            RelatedCheckPriorityOperation priorityNodeVisitor =
                            new RelatedCheckPriorityOperation(
                            priorityDAO,
                            accountPriorityRelationshipDAO,
                            rolePriorityRelationshipDAO);

            RelatedCheckPriorityOperation relatedCheckPriorityOperation = springApplicationContext.getBean(RelatedCheckPriorityOperation.class);

            relatedCheckPriorityOperation.relateCheck(priorityNode);

            Boolean execute = (Boolean) priorityNode.execute(relatedCheckPriorityOperation);

//            Boolean checkResult = priorityNodeVisitor.getRelateCheckResult();
//            if (!checkResult) {
//                RemovePriorityOperation removeVisitor = new RemovePriorityOperation(priorityDAO);
//                // ???????????????????????????????????????
//                removeVisitor.visit(priorityNode);
//            }
            if (execute) {
                return false;
            }

            // ??????????????????
            RemovePriorityOperation removePriorityOperation = springApplicationContext.getBean(RemovePriorityOperation.class);
            priorityNode.execute(removePriorityOperation);

        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }
        return true;
    }

    /**
     * ???????????????????????????????????????????????????????????????
     * @param accountId ??????id
     * @param code ????????????
     * @return ????????????????????????
     * @throws Exception
     */
    public Boolean existAuthorizedByCode(Long accountId, String code){
        try {
            Long count = priorityDAO.countAuthorizedByCode(accountId, code);
            return count > 0 ? true : false;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    /**
     * ?????????????????????????????????url????????????????????????
     * @param accountId ??????id
     * @param url ??????url
     * @return ????????????????????????
     * @throws Exception
     */
    public Boolean existAuthorizedByUrl(Long accountId, String url){
        try {
            Long count = priorityDAO.countAuthorizedByUrl(accountId, url);
            return count > 0 ? true : false;
        } catch (Exception e) {
            logger.error("error", e);
        }
        return false;
    }

    /**
     * ?????????????????????????????????
     * @param parameters ????????????
     * @return ??????
     */
    public List<PriorityDTO> listAuthroziedByAccountId(Map<String, Object> parameters){
        try {
            return ObjectUtils.convertList(priorityDAO.listAuthroziedByAccountId(parameters),PriorityDTO.class);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * ??????????????????????????????????????????????????????????????????
     * @param accountId ??????id
     * @param code ????????????
     * @return ?????????????????????
     */
    public Long countAuthorizedByCode(Long accountId, String code){
        try {
            return priorityDAO.countAuthorizedByCode(accountId, code);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * ????????????URL????????????????????????????????????????????????
     * @param accountId ??????id
     * @param url ??????url
     * @return ?????????????????????
     */
    public Long countAuthorizedByUrl(Long accountId, String url){
        try {
            return priorityDAO.countAuthorizedByUrl(accountId, url);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }

    /**
     * ????????????id????????????id
     * @param priorityId ??????id
     * @return
     */
    public List<Long> listAccountIdsByPriorityId(Long priorityId){
        try {
            return priorityDAO.listAccountIdsByPriorityId(priorityId);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return null;
    }


}
