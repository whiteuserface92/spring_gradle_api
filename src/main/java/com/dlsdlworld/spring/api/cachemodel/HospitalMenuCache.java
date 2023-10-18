package com.dlsdlworld.spring.api.cachemodel;

import com.dlsdlworld.spring.api.types.MenuTypes;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 병원 메뉴 정보를 redis에 저장하기 위한 클래스
 */
@Data
@NoArgsConstructor
@RedisHash("HospitalMenuCache")
public class HospitalMenuCache implements Serializable {

	/**
	 * 식별자 menu_id 인데 hospitalMenuId는 어디에 있나?
	 */
	@Id
	private Long id;

	/**
	 * 제품 구분
	 */
	@Indexed
	private String prodCd;

	/**
	 * 병원 코드
	 */
	@Indexed
	private String hospitalCd;

	/**
	 * 부모 아이디
	 */
	@Indexed
	private Long parentId;

	/**
	 * 메뉴 타입
	 */
	private MenuTypes menuType;

	/**
	 * 노드 레벨
	 */
	private Short level;

	/**
	 * 표시 순서
	 */
	private String dispOrd;

	/**
	 * 메뉴명 재정의
	 */
	private String ovrdNameCd;

	/**
	 * 서비스 주소 재정의
	 */
	private String ovrdServiceUrl;

	/**
	 * 이미지 주소 재정의
	 */
	private String ovrdImgUrl;

	/**
	 * 사용여부
	 */
	private Boolean enabled;

	/**
	 * 병원메뉴 속성 정보
	 * Long hospitalMenuId
	 * Set<HospitalMenuCstm> hospitalMenuCstms
	 */
	@Reference
	private HospitalMenuCstmsCache hospitalMenuCstmsCache;

	/**
	 * 이름 재정의 리스트
	 */
	@Reference
	private Set<MessageCache> ovrdNameCds;

	/**
	 * 메뉴 캐시 참조
	 */
	@Reference
	private MenuCache menu;

	/**
	 * 자식노드 리스트
	 */
	public List<HospitalMenuCache> children;// = new ArrayList<>();

	public Set<String>  roles;

	public Set<String> privileges;

	/**
	 * 메뉴 접근 권한이 부서 리스트를 ,로 분류해서 넣은값
	 * 개인별 메뉴 조회시 deptWhiteList 공백이면 모든 사용자 접근 가능하며
	 * 입력되어 있으면 해당 부서 사용자만 접근 가능하다
	 */
	public String deptWhiteList;
	//	/**
//	 * 자식노드를 추가한다
//	 * 노드의 레벨이 최상위이면 에러를 던진다
//	 *
//	 * @param child
//	 */
//	@JsonIgnore
//	public void addChild(HospitalMenuCache child) {
//		if (child.getLevel() == 1)
//			throw new IllegalArgumentException("not a child node(level:" + child.getLevel() + ")");
//
//		this.children.add(child);
//	}

	@Builder
	public HospitalMenuCache(Long id,
	                         String prodCd,
	                         String hospitalCd,
	                         Long parentId,
	                         MenuTypes menuType,
	                         Short level,
	                         String dispOrd,
	                         String ovrdNameCd,
	                         String ovrdServiceUrl,
	                         String ovrdImgUrl,
	                         Boolean enabled,
	                         Set<MessageCache> ovrdNameCds,
	                         MenuCache menu,
							 Set<String> roles,
							 Set<String> privileges,
 							 List<HospitalMenuCache> children) {
		this.id = id;
		this.prodCd = prodCd;
		this.hospitalCd = hospitalCd;
		this.parentId = parentId;
		this.menuType = menuType;
		this.level = level;
		this.dispOrd = dispOrd;
		this.ovrdNameCd = ovrdNameCd;
		this.ovrdServiceUrl = ovrdServiceUrl;
		this.ovrdImgUrl = ovrdImgUrl;
		this.enabled = enabled;
		this.ovrdNameCds = ovrdNameCds;
		this.menu = menu;
		this.children = children;
		this.roles = roles;
		this.privileges = privileges;
	}
}
