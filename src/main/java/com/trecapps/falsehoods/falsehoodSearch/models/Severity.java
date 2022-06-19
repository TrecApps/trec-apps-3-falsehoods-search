package com.trecapps.falsehoods.falsehoodSearch.models;
public enum Severity
{
	LIE((byte)0),					// Falsehood Upgrade for "OBJECTIVE_FALSEHOOD" via appeals. If enough people believe the figure lied (wasn't just wrong)
	OBJECTIVE_FALSEHOOD((byte)1),	// Falsehood references information that is "objectively" misleading
	MISLEADING((byte)2),			// No Direct Lie, but Narrative is misleading and relies on information being omitted (or given in a "bone" sort of way
	HYPOCRISY((byte)3),				// Upgrade for the "SUBJECTIVE_FALSEHOOD" via appeals. If enough people feel that the falsehood rises to the level of hypocrisy
	SUBJECTIVE_FALSEHOOD((byte)4),	// If there is no objective standard for which it can be a falsehood, but a subjective standard from the author can be derived
	FAULTY_LOGIC((byte)5);			// If the Author, while not telling a falsehood, uses faulty logic to make the case
	
	Severity(byte value)
	{
		this.value = value;
	}
	
	byte value;
	
	public byte GetValue()
	{
		return value;
	}
}
