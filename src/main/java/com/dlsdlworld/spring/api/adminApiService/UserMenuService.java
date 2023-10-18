package com.dlsdlworld.spring.api.adminApiService;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : hansik.shin
 * Date : 2020/05/19
 * Time : 5:30 오후
 */

import com.dlsdlworld.spring.api.adminApiController.UserMenuController;
import com.dlsdlworld.spring.api.exception.EntityNotFoundException;
import com.dlsdlworld.spring.api.model.Menu;
import com.dlsdlworld.spring.api.model.User;
import com.dlsdlworld.spring.api.model.UserMenu;
import com.dlsdlworld.spring.api.param.PostUserMenuParam;
import com.dlsdlworld.spring.api.repository.MenuRepository;
import com.dlsdlworld.spring.api.repository.UserMenuRepository;
import com.dlsdlworld.spring.api.repository.UserRepository;
import com.dlsdlworld.spring.api.types.MenuTypes;
import com.dlsdlworld.spring.api.utils.Assert;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.net.URI;

@Slf4j
@Service
public class UserMenuService {

	private static final String PROC_GET_USER_MENU = "get_user_menu";

	@Resource
	private EntityManager em;

	@Resource
	private ObjectMapper mapper;

	private UserMenuRepository userMenuRepository;

	private UserRepository userRepository;

	private MenuRepository menuRepository;

	@Autowired
	public UserMenuService(UserMenuRepository userMenuRepository, UserRepository userRepository, MenuRepository menuRepository) {
		this.userMenuRepository = userMenuRepository;
		this.userRepository = userRepository;
		this.menuRepository = menuRepository;
	}

	@PostConstruct
	public void init() {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS, false);
	}

	@Transactional
	public URI postUserMenus(PostUserMenuParam param){

		User user = userRepository.findById(param.getUserId())
				.orElseThrow(() -> new EntityNotFoundException("No user found for the " + param.getUserId()));
		log.trace("getUserId : {}", user.toString());

		Menu menu = menuRepository.findById(param.getMenuId())
				.orElseThrow(() -> new EntityNotFoundException("No menu found for the " + param.getMenuId()));
		log.trace("getMenuId : {}", menu.toString());

		UserMenu rootUserMenu = userMenuRepository.findByUserIdAndMenuTypeAndLevel(param.getUserId(), param.getMenuType(), (short)0);

		if(rootUserMenu != null & param.getMenuType() == MenuTypes.BASIC) {
			 userMenuRepository.deleteByUserIdAndMenuTypeAndLevel(param.getUserId(), param.getMenuType(), (short)1);
		}

		log.trace("rootUserMenu : {}", rootUserMenu);
		UserMenu savedRootUserMenu = null;

		if( rootUserMenu == null ) {
			Menu rootMenu = menuRepository.findByNameCd("menu.name."+param.getMenuType());
			log.trace("getMenuId : {}", menu.toString());

			rootUserMenu = new UserMenu();
			BeanUtils.copyProperties(param, rootUserMenu);
			rootUserMenu.setMenuType(param.getMenuType());
			rootUserMenu.setLevel((short)0);
			rootUserMenu.setMenu(rootMenu);
			rootUserMenu.setUser(user);

			savedRootUserMenu = userMenuRepository.save(rootUserMenu);
			log.trace("saveUserMenu {} ", savedRootUserMenu);
		}

		UserMenu newUserMenu = new UserMenu();
		BeanUtils.copyProperties(param, newUserMenu);
		if(savedRootUserMenu!=null)
			newUserMenu.setParent(savedRootUserMenu);
		else
			newUserMenu.setParent(rootUserMenu);
		newUserMenu.setMenu(menu);
		newUserMenu.setUser(user);
		UserMenu savedUserMenu = userMenuRepository.save(newUserMenu);
		log.trace("savedUserMenu {} ", savedUserMenu);

		URI uri = WebMvcLinkBuilder.linkTo(UserMenuController.class).slash(savedUserMenu.getId()).toUri();

		return uri;
	}

	public String getUserMenus(Long userId, MenuTypes menuType) {

		log.trace("userId:{}", userId);
		log.trace("menuType:{}", menuType);

		Assert.notNull("userId", userId);
		Assert.notNull("menuType", menuType);

		StoredProcedureQuery query = em.createStoredProcedureQuery(PROC_GET_USER_MENU)
				.registerStoredProcedureParameter("user_id", Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter("menu_type", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("result", String.class, ParameterMode.OUT)
				.setParameter("user_id", userId)
		        .setParameter("menu_type", menuType.toString());

		query.execute();
		String result = (String) query.getOutputParameterValue("result");

		if (result == null || result.isEmpty())
			throw new EntityNotFoundException("getUserMenu error");

		return result;

	}
	@Transactional
	public void deleteUserMenus(Long userMenuId) {
		userMenuRepository.deleteById(userMenuId);
	}
}
