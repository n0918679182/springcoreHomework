package com.study.springgithub.homework3_0109.entity;



import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class Timee {
	private String theTime;

	public Timee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Timee(String theTime) {
		super();
		this.theTime = theTime;
	}

	public String getTheTime() {
		return theTime;
	}

	public void setTheTime(String theTime) {
		this.theTime = theTime;
	}

	@Override
	public String toString() {
		return "timee [theTime=" + theTime + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(theTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timee other = (Timee) obj;
		return Objects.equals(theTime, other.theTime);
	}
	

}
