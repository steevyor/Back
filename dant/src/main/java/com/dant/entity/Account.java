package com.dant.entity;
import java.io.Serializable;

public class Account implements Serializable {

    private String email;
	private long updated;
    private boolean position;


	public Account()
    {
		updated = System.currentTimeMillis();
	}

	public Account(String email, boolean partage)
	{
		this();
		this.email = email;
		this.position = partage;
	}

    public boolean isPosition() {
        return position;
    }

    public void setPosition(boolean position) {
        this.position = position;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getUpdated() {
		return updated;
	}

	public void setUpdated(long updated) {
		this.updated = updated;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Account account = (Account) o;

		return email.equals(account.email);
	}

	@Override
	public int hashCode() {
		return email.hashCode();
	}

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", updated=" + updated + '\'' +
                ", partage_de_position=" + position
                + " " + '}';
    }
}
