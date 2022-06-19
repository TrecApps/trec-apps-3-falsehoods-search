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
public class FullPublicFalsehood {

    String contents;

    PublicFalsehood metadata;

    PublicFalsehoodRecords records;



	public FullPublicFalsehood clone()
    {
    	return new FullPublicFalsehood(contents, metadata.clone(), records);
    }

	public FullPublicFalsehood clone(byte severity)
	{
		if(severity < 0 || severity > 7)
			throw new IllegalArgumentException("Severity Parameter out of bounds");

		PublicFalsehood newMeta = metadata.clone();
		newMeta.setStatus(severity);
		return new FullPublicFalsehood(contents, newMeta, records);
	}

}
