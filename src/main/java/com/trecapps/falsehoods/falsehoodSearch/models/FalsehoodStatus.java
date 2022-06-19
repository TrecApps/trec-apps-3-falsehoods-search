package com.trecapps.falsehoods.falsehoodSearch.models;

public enum FalsehoodStatus {
	
	SUBMITTED((byte)0),
	CHALLENGED((byte)1),
	MODIFIED((byte)2),
	VERIFIED((byte)3),
	REVERIFIED((byte)4),
	
	OVERTURNED((byte)5),
	REJECTED((byte)6);
	
	
	FalsehoodStatus(byte value)
	{
		this.value = value;
	}
	
	byte value;
	
	public byte GetValue()
	{
		return value;
	}
}
