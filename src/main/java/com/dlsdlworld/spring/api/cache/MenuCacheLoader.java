package com.dlsdlworld.spring.api.cache;
import com.dlsdlworld.spring.api.event.HospitalMenuCstmListener;
import com.dlsdlworld.spring.api.event.HospitalMenuListener;
import com.dlsdlworld.spring.api.event.MenuListener;
import com.dlsdlworld.spring.api.model.HospitalMenu;
import com.dlsdlworld.spring.api.model.Menu;
import com.dlsdlworld.spring.api.repository.HospitalMenuRepository;
import com.dlsdlworld.spring.api.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : ricky
 * Date : 2020/04/02
 * Time : 5:43 오후
 */
@Slf4j
@Service
public class MenuCacheLoader {

	private MenuRepository menuRepository;
	private HospitalMenuRepository hospitalMenuRepository;
	private MenuListener menuListener;
	private HospitalMenuListener hospitalMenuListener;
	private HospitalMenuCstmListener hospitalMenuCstmListener;

	@Autowired
	public MenuCacheLoader(
		MenuRepository menuRepository,
		HospitalMenuRepository hospitalMenuRepository,
		MenuListener menuListener,
		HospitalMenuListener hospitalMenuListener,
		HospitalMenuCstmListener hospitalMenuCstmListener
	) {
		this.menuRepository = menuRepository;
		this.hospitalMenuRepository = hospitalMenuRepository;
		this.menuListener = menuListener;
		this.hospitalMenuListener = hospitalMenuListener;
		this.hospitalMenuCstmListener = hospitalMenuCstmListener;
	}

	/**
	 * 메뉴캐시 리로딩
	 */
	public void load() {
		Set<Menu> menus = menuRepository.findAll();
		log.info("## menus:{}", menus.size());

		for (Menu menu : menus) {
			menuListener.saveCache(menu);
		}

		Set<HospitalMenu> hospitalMenus = hospitalMenuRepository.findAll();
		log.info("## hosptialMenus:{}", hospitalMenus.size());

		for (HospitalMenu hospitalMenu : hospitalMenus) {
			HospitalMenuCstmsCache storedCache= hospitalMenuCstmListener.saveCache(hospitalMenu.getId(), hospitalMenu.getHospitalMenuCstms());
			hospitalMenuListener.saveCache(hospitalMenu, storedCache);
		}
	}

	/**
	 * 메뉴캐시 리로딩
	 */
	public void load(Long hospitalId, String prodCd) {

		if(!menuListener.getCount().equals(menuRepository.count())){
			Set<Menu> menus = menuRepository.findAll();
			log.info("## menus:{}", menus.size());

			for (Menu menu : menus) {
				menuListener.saveCache(menu);
			}
		}

		Set<HospitalMenu> hospitalMenus = hospitalMenuRepository.findByHospitalIdAndProdCd(hospitalId, prodCd);

		log.info("## hosptialMenus:{}", hospitalMenus.size());

		for (HospitalMenu hospitalMenu : hospitalMenus) {
			HospitalMenuCstmsCache storedCache= hospitalMenuCstmListener.saveCache(hospitalMenu.getId(), hospitalMenu.getHospitalMenuCstms());
			hospitalMenuListener.saveCache(hospitalMenu, storedCache);
		}
	}

	public void loadMenu(){
		menuListener.deleteCaches();
		menuListener.saveCache(menuRepository.findAll());
	}
	
}
