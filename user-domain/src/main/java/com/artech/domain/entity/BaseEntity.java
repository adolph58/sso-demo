package com.artech.domain.entity;

import javax.persistence.MappedSuperclass;
import java.util.Calendar;

/**
 * 表必备三字段：id, gmt_create, gmt_modified。
 * 说明： 其中 id 必为主键，类型为 unsigned bigint、单表时自增步长为 1。
 * gmt_create, gmt_modified 的类型均为 date_time 类型，
 * 前者现在时表示主动创建，后者过去分词被动更新。
 *
 * @MappedSuperclass
 *  1.标注为 @MappedSuperclass 的类将不是一个完整的实体类，他将不会映射到数据库表，但是他的属性都将映射到其子类的数据库字段中。
 *  2.标注为 @MappedSuperclass 的类不能再标注 @Entity 或 @Table 注解，也无需实现序列化接口。
 */
@MappedSuperclass
public class BaseEntity extends IdEntity {

	private Calendar gmtCreate = Calendar.getInstance();

	private Calendar gmtModified;

	public Calendar getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Calendar gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Calendar getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Calendar gmtModified) {
		this.gmtModified = gmtModified;
	}

}
