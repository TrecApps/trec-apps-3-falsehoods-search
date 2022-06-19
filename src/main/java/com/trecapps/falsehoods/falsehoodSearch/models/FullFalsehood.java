package com.trecapps.falsehoods.falsehoodSearch.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FullFalsehood {

	String contents;
	
	Falsehood metadata;

	FalsehoodRecords records;


	public FullFalsehood clone()
	{
		return new FullFalsehood(contents, metadata.clone(), records);
	}

	public FullFalsehood clone(byte severity)
	{
		if(severity < 0 || severity > 7)
			throw new IllegalArgumentException("Severity Parameter out of bounds");

		Falsehood newMeta = metadata.clone();
		newMeta.setStatus(severity);
		return new FullFalsehood(contents, newMeta, records);
	}

	
}
