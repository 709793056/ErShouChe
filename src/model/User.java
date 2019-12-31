package model;

import java.io.Serializable;

public class User implements Serializable {


	private static final long serialVersionUID = 1L;


	private Integer userid;


	private String xingming;


	private String xingbie;


	private Integer nianling;


	private Integer dianhuahaoma;

	
	private String dianziyoujian;

	private String zhaopian;

	
	public User() {
	}

	
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	
	public Integer getUserid() {
		return this.userid;
	}

	
	public void setxingming(String xingming) {
		this.xingming = xingming;
	}

	
	public String getXingming() {
		return this.xingming;
	}

	
	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}

	
	public String getXingbie() {
		return this.xingbie;
	}

	
	public void setNianling(Integer nianling) {
		this.nianling = nianling;
	}

	
	public Integer getNianling() {
		return this.nianling;
	}

	public void setDianhuahaoma(Integer dianhuahaoma) {
		this.dianhuahaoma = dianhuahaoma;
	}

	
	public Integer getDianhuahaoma() {
		return this.dianhuahaoma;
	}

	
	public void setDianziyoujian(String dianziyoujian) {
		this.dianziyoujian = dianziyoujian;
	}

	public String getDianziyoujian() {
		return this.dianziyoujian;
	}
	public void setZhaopian(String zhaopian) {
		this.zhaopian = zhaopian;
	}

	/**
	 * zhaopian ���擾���܂�.
	 * 
	 * @return zhaopian
	 */
	public String getZhaopian() {
		return this.zhaopian;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());

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
		User other = (User) obj;
		if (userid == null) {
			if (other.userid != null) {
				return false;
			}
		} else if (!userid.equals(other.userid)) {
			return false;
		}

		return true;
	}

}
