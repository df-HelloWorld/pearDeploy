package com.xn.system.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.util.BeanUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.MD5;
import com.xn.system.entity.Account;
import com.xn.system.entity.Role;
import com.xn.system.model.AccountModel;
import com.xn.system.service.AccountService;
import com.xn.system.service.RoleService;
import org.springframework.web.util.WebUtils;

/**
 * 
*<p>Title: AccountController</p>
*<p>Description: 账号信息</p>
*<p>Company: 讯牛科技</p> 
*@author hwh
*@date 2017-5-2
 */
@Controller
@RequestMapping("/system/account")
public class AccountController extends BaseController{

	private static Logger log = Logger.getLogger(RoleController.class);

	@Autowired
	private AccountService<Account> accountService;//管理员


	@Autowired
	private RoleService<Role> roleService;
	
	/**
	 * 获取页面
	 */
	@RequestMapping("/list")
	public String list() {
		return "system/account/list-account";
	}
	/**
	 * 获取新增页面
	 */
	@RequestMapping("/new-account")
	public String newAccount(Model model) {	
		model.addAttribute("rloeMenu", roleService.queryList());
		return "system/account/new-account";
	}
	/**
	 * 获取修改页面
	 */
	@RequestMapping("/update-account")
	public String updateRole(Model model, long id, Integer op) {
		model.addAttribute("rloeMenu", roleService.queryList());
		Account atModel = new Account();
		atModel.setId(id);
		model.addAttribute("account", accountService.queryById(atModel));
		model.addAttribute("rloeMenu", roleService.queryList());
		model.addAttribute("op", op);
		
		return "system/account/update-account";
	}
	/**
	 * 
	 * 获取表格数据列表
	 */
	@RequestMapping("/dataList")
	public void dataList(AccountModel model, HttpServletResponse response) throws Exception {
		List<Account> dataList = accountService.queryByList(model);
		HtmlUtil.writerJson(response, model.getPage(), dataList);
	}
	/**
	 * 添加数据
	 */
	@RequestMapping("/add")
	public void add(HttpServletRequest request, HttpServletResponse response, Account bean) throws Exception {
		Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
		if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
			//check是否有重复的账号

			if (bean.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_SYS || bean.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_CHECK
			|| bean.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.ROLE_OPERATE){
				//管理员账号
				Account queryBean = new Account();
				queryBean.setAccountNum(bean.getAccountNum());
				queryBean = accountService.queryByCondition(queryBean);
				if (queryBean != null && queryBean.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
					sendFailureMessage(response,"有重复的账号,请重新输入其它账号!");
				}else {
					bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
					bean.setCreateUser(account.getId());
					bean.setCreateRole(account.getRoleId());
					accountService.add(bean);
				}
			}
			sendSuccessMessage(response, "保存成功~");
		}else {
			sendFailureMessage(response,"登入超时，请重新登入!");
		}

	}
	/**
	 * 修改数据
	 */
	@RequestMapping("/update")
	public void update(HttpServletRequest request, HttpServletResponse response,Account bean, String op) throws Exception {
		Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
		if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
			bean.setUpdateUser(account.getId());
			bean.setUpdateRole(account.getRoleId());
			if ("2".equals(op)) {
				bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
			}
			accountService.update(bean);
			sendSuccessMessage(response, "保存成功~");
		}else {
			sendFailureMessage(response, "登录超时,请重新登录在操作!");
		}

	}
	/**
	 * 删除数据
	 */
	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Account bean) throws Exception {
		Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
		if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
			bean.setUpdateUser(account.getId());
			bean.setUpdateRole(account.getRoleId());
			accountService.delete(bean);
			sendSuccessMessage(response, "删除成功");
		}else{
			sendFailureMessage(response, "登录超时,请重新登录在操作!");
		}

	}

	/**
	 * 启用/禁用
	 */
	@RequestMapping("/manyOperation")
	public void manyOperation(HttpServletRequest request, HttpServletResponse response, Account bean) throws Exception {
		Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
		if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
			bean.setUpdateUser(account.getId());
			bean.setUpdateRole(account.getRoleId());
			accountService.manyOperation(bean);
			sendSuccessMessage(response, "状态更新成功");
		}else{
			sendFailureMessage(response, "登录超时,请重新登录在操作!");
		}
	}
}
