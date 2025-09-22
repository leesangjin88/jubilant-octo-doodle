package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "typeCode")
public class TypeVO {
	private String typeCode;
	private String typeName;
	private String isActive;
	private String typeGroup;
}
