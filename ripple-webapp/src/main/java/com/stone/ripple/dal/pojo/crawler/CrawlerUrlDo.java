package com.stone.ripple.dal.pojo.crawler;

import java.util.Date;

import com.stone.lava.pojo.LavaDo;

public class CrawlerUrlDo extends LavaDo {
	private Long id;

	private Date cTime;

	private String cUser;

	private Date mTime;

	private String mUser;

	private String name;

	private String moduleName;

	private String domainName;

	private String crawlerUrl;

	private String type;

	private String crawlerClass;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getcUser() {
		return cUser;
	}

	public void setcUser(String cUser) {
		this.cUser = cUser;
	}

	public Date getmTime() {
		return mTime;
	}

	public void setmTime(Date mTime) {
		this.mTime = mTime;
	}

	public String getmUser() {
		return mUser;
	}

	public void setmUser(String mUser) {
		this.mUser = mUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getCrawlerUrl() {
		return crawlerUrl;
	}

	public void setCrawlerUrl(String crawlerUrl) {
		this.crawlerUrl = crawlerUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCrawlerClass() {
		return crawlerClass;
	}

	public void setCrawlerClass(String crawlerClass) {
		this.crawlerClass = crawlerClass;
	}
}