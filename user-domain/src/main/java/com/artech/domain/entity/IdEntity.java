package com.artech.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 不需要创建时间和修改时间的表可以继承此类
 *
 * id 必为主键，类型为 unsigned bigint、单表时自增步长为 1。
 *
 * @MappedSuperclass
 *  1.标注为 @MappedSuperclass 的类将不是一个完整的实体类，他将不会映射到数据库表，但是他的属性都将映射到其子类的数据库字段中。
 *  2.标注为 @MappedSuperclass 的类不能再标注 @Entity 或 @Table 注解，也无需实现序列化接口。
 */
@MappedSuperclass
public class IdEntity implements Serializable {

	/**
     * @GenericGenerator 的 strategy 有 14 种：
	 * uuid2, guid, uuid, uuid.hex, assigned, identity, select, sequence,
	 * seqhilo, increment, foreign, sequence-identity, enhanced-sequence, enhanced-table
	 * 具体可以查看 org.hibernate.id.factory.internal.DefaultIdentifierGeneratorFactory 类
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(generator = "idGenerator")
	//@GenericGenerator(name = "idGenerator", strategy = "assigned")
	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		IdEntity other = (IdEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
