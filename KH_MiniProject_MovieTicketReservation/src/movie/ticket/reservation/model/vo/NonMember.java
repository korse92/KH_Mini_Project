package movie.ticket.reservation.model.vo;

import java.util.Calendar;

public class NonMember extends Person{
	
	
	private String password;
	
	public NonMember() {
		super();
	}

	public NonMember(Calendar birth, String phoneNum, String password) {
		super(null, birth, phoneNum);
		
		this.password = password;
	
		String[] pNumArr = super.getPhoneNum().split("-");	
		String lastPhoneNum = pNumArr[2];
		String birthStr = ""+ birth.get(Calendar.YEAR) + (birth.get(Calendar.MONTH)+1) + birth.get(Calendar.DATE);
		
		super.setName(birthStr + lastPhoneNum);
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NonMember other = (NonMember) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NonMember [getName()=" + getName() + ", getBirth()=" + getBirth()
				+ ", getPhoneNum()=" + getPhoneNum() + ", password=" + password + "]";
	}
	
	


	
	
}
